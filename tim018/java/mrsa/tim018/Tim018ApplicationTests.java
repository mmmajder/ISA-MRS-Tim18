package mrsa.tim018;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.service.DeletationRequestService;

@RunWith(SpringRunner.class)
@SpringBootTest
class Tim018ApplicationTests {

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
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {	

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
		        DeletationRequest deletationRequest = deletationRequestService.findOne(1L);// ocitan objekat sa id 1
//		        deletationRequest.set(800L);// izmenjen ucitan objekat
//				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
		        deletationRequestService.acceptDeclineRegistrationRequest(deletationRequest.getId(), "comment", true);// bacice ObjectOptimisticLockingFailureException
				
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        DeletationRequest deletationRequest = deletationRequestService.findOne(1L);// ocitan objekat sa id 1
//		        deletationRequest.set(800L);// izmenjen ucitan objekat
//				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
		        deletationRequestService.acceptDeclineRegistrationRequest(deletationRequest.getId(), "comment", true);// bacice ObjectOptimisticLockingFailureException
				
//				Product productToUpdate = productService.findById(1L);// ocitan isti objekat sa id 1 kao i iz prvog threada
//				productToUpdate.setPrice(900L);// izmenjen ucitan objekat
//				/*
//				 * prvi ce izvrsiti izmenu i izvrsi upit:
//				 * Hibernate: 
//				 *     update
//				 *         product
//				 *     set
//				 *         name=?,
//        		 *         origin=?,
//                 *         price=?,
//                 *         version=? 
//                 *     where
//                 *         id=? 
//                 *         and version=?
//                 *         
//                 * Moze se primetiti da automatski dodaje na upit i proveru o verziji
//				 */
//				productService.save(productToUpdate);
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

	}

}
