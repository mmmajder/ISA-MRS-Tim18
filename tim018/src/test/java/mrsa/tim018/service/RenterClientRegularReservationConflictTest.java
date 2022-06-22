package mrsa.tim018.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.ReservationRequestDTO;
import mrsa.tim018.model.AppointmentType;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.LoyaltyState;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.Review;
import mrsa.tim018.model.TimeRange;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RenterClientRegularReservationConflictTest {

	@Autowired
	private AssetService assetService;
	
	@Test(expected = PessimisticLockingFailureException.class)
	public void testPessimisticLock() throws Throwable {	

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
		        Asset asset = assetService.findOneLock(1000005L);
	        	asset.getCalendar().setAvailable(new ArrayList<TimeRange>());
				try { Thread.sleep(3000); } catch (InterruptedException e) {}
				assetService.save(asset);
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        Asset asset = assetService.findOneLock(1000005L);
	        	asset.getCalendar().setAvailable(new ArrayList<TimeRange>());
				assetService.save(asset);
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