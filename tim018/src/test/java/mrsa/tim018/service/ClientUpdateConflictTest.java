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
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.model.Client;
import mrsa.tim018.model.UserType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientUpdateConflictTest {
	@Autowired
	private ClientService clientService;

	@Before
	public void setUp() throws Exception {
		clientService.save(new Client("firstName", "lastName", "address", "city", "state", "phoneNum", UserType.Client, "email", "password", 0, "13dd"));
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {	

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
				Client client = clientService.findOne(2L);// ocitan objekat sa id 1
				client.setFirstName("Paja");// izmenjen ucitan objekat
				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
				clientService.save(client);// bacice ObjectOptimisticLockingFailureException
				
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
		        Client client = clientService.findOne(2L);// ocitan isti objekat sa id 1 kao i iz prvog threada
		        client.setFirstName("Gaja");// izmenjen ucitan objekat
		        clientService.save(client);
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
