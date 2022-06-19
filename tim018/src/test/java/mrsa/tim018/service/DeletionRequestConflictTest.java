package mrsa.tim018.service;

import static mrsa.tim018.constants.DeletationRequestConstants.DB_ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.repository.DeletationRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletionRequestConflictTest {
	
	@Autowired
	private DeletationRequestService deletationRequestService; 
	
	@Before
	public void setUp() throws Exception {
		User user1 = new User((long) 1, "Pera1", "Peric", "blbabl", "ns", "srb", "313213", UserType.Client, "dasdasd", "password", "profilePhotoId"); 
		User user2 = new User((long) 2, "Pera2", "Peric", "blbabl", "ns", "srb", "313213", UserType.Client, "dasdasd", "password", "profilePhotoId"); 
		User user3 = new User((long) 3, "Pera3", "Peric", "blbabl", "ns", "srb", "313213", UserType.Client, "dasdasd", "password", "profilePhotoId"); 
		User user4 = new User((long) 4, "Pera4", "Peric", "blbabl", "ns", "srb", "313213", UserType.Client, "dasdasd", "password", "profilePhotoId"); 
		User user5 = new User((long) 5, "Pera5", "Peric", "blbabl", "ns", "srb", "313213", UserType.Client, "dasdasd", "password", "profilePhotoId"); 
		deletationRequestService.save(new DeletationRequest((long) 1, false, RequestStatus.Pending, user1, "reason"));
		deletationRequestService.save(new DeletationRequest((long) 1, false, RequestStatus.Pending, user2, "reason"));
		deletationRequestService.save(new DeletationRequest((long) 1, false, RequestStatus.Pending, user3, "reason"));
		deletationRequestService.save(new DeletationRequest((long) 1, false, RequestStatus.Pending, user4, "reason"));
		deletationRequestService.save(new DeletationRequest((long) 1, false, RequestStatus.Pending, user5, "reason"));
	}
	
	
	// doesn't pass every time, because sometimes exception doesn't trigger  
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
		        DeletationRequest deletationRequest = deletationRequestService.findOne(1L);
		        deletationRequestService.acceptDeclineRegistrationRequest(deletationRequest.getId(), "comment1", true);
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        DeletationRequest deletationRequest = deletationRequestService.findOne(1L);// ocitan isti objekat sa id 1 kao i iz prvog threada
		        deletationRequestService.acceptDeclineRegistrationRequest(deletationRequest.getId(), "comment2", true);// izmenjen ucitan objekat
			}
		});
		try {
		    future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
		} catch (ExecutionException e) {
		    System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
		    throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
//		throw new ObjectOptimisticLockingFailureException("", null);
	}
}
