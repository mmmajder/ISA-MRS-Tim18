package mrsa.tim018.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.model.Admin;
import mrsa.tim018.model.Asset;
import mrsa.tim018.repository.AdminRepository;
import mrsa.tim018.repository.AssetRepository;
import mrsa.tim018.repository.ResortRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	
	@Mock
	private AdminRepository adminRepositoryMock;
	
	@Mock
	private Admin adminMock;
	
	@InjectMocks
	private AdminService adminService;
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullId() {
		Admin admin = new Admin();
		admin.setFirstName("Pera");
		admin.setLastName("Peric");
		// ne navodi se index koji je po modelu obavezan (nullable = false) -> Not null constraint violation
		
		// 1. Definisanje ponašanja mock objekata     
		when(adminRepositoryMock.save(admin)).thenThrow(DataIntegrityViolationException.class);
		
		// 2. Akcija 
		adminService.save(admin);
		
		// 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima  
		verify(adminRepositoryMock, times(1)).save(admin);
        verifyNoMoreInteractions(adminRepositoryMock);
	}
}
