package mrsa.tim018.service;

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

import mrsa.tim018.model.Client;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Review;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccpetReviewConflictTest {
	
	@Autowired
	private ReviewService reviewService;
	
	@Before
	public void setUp() throws Exception {
		reviewService.save(new Review(8L, false, "text", 5, false, false, true, 2L, 3L, 1L, RequestStatus.Pending, 1L));
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {	

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
				Review review = reviewService.findOnePending(8L);
				review.setStatus(RequestStatus.Accepted);
				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
				reviewService.save(review);// bacice ObjectOptimisticLockingFailureException
				
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        Review review = reviewService.findOnePending(8L);
				review.setStatus(RequestStatus.Declined);
				reviewService.save(review);
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
