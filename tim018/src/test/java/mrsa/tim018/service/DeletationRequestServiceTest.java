package mrsa.tim018.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
  
import javax.transaction.Transactional;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.repository.DeletationRequestRepository;

import static mrsa.tim018.constants.DeletationRequestConstants.DB_ID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletationRequestServiceTest {
	
	@Mock
	private DeletationRequestRepository deletationRequestRepositoryMock;
	
	@Mock
	private DeletationRequest deletationRequestMock;
	
	@InjectMocks
	private DeletationRequestService deletationRequestService;
	
	
	@Test
    @Transactional
    @Rollback(true) // uključeno po default-u, ne mora se navesti
	public void testCreate() throws Exception{
		Client client = new Client();
		client.setAddress("address");
		client.setBiography("biography");
		client.setCity("city");
		client.setDeleted(false);
		client.setEmail("email");
		client.setEnabled(true);
		client.setFirstName("firstName");
		client.setId(100L);
		client.setLastName("lastName");
		client.setUserType(UserType.Client);
		client.setState("state");
		client.setProfilePhotoId("profilePhotoId");
		client.setPhoneNum("phoneNum");
		client.setPenaltyPoints(0);
		client.setLoyaltyPoints(0);
		
		String reason = "reason";
		

		DeletationRequest deletationRequest = new DeletationRequest(client, reason);
		
		when(deletationRequestRepositoryMock.findAll()).thenReturn(Arrays.asList(new DeletationRequest(1L, false, RequestStatus.Pending, new User(), "reason")));
		when(deletationRequestRepositoryMock.save(deletationRequest)).thenReturn(deletationRequest);
		
		int dbSizeBeforeAdd = deletationRequestService.findAll().size();
		
		DeletationRequest dbDeletationRequest = deletationRequestService.create(client, reason);
		
		when(deletationRequestRepositoryMock.findAll()).thenReturn(Arrays.asList(new DeletationRequest(1L, false, RequestStatus.Pending, new User(), "reason"), deletationRequest));
		
		
		assertThat(dbDeletationRequest).isNotNull();
		
        List<DeletationRequest> deletationRequests = deletationRequestService.findAll();
        assertThat(deletationRequests).hasSize(dbSizeBeforeAdd + 1); //verifikacija da je novi student upisan u bazu
        
        dbDeletationRequest = deletationRequests.get(deletationRequests.size() - 1); // preuzimanje poslednjeg studenta
        
        assertThat(dbDeletationRequest.getReason()).isEqualTo("reason");
        
        verify(deletationRequestRepositoryMock, times(2)).findAll();
        verify(deletationRequestRepositoryMock, times(1)).save(deletationRequest);
        verifyNoMoreInteractions(deletationRequestRepositoryMock);
	}
	 
	@Test 
	public void testFindOne() {
		// 1. Definisanje ponašanja mock objekata
		when(deletationRequestRepositoryMock.findById(DB_ID)).thenReturn(Optional.of(deletationRequestMock));
		
		// 2. Akcija
		DeletationRequest dbDeletationRequest = deletationRequestService.findOne(DB_ID);
		
		// 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
		assertEquals(deletationRequestMock, dbDeletationRequest);
		
        verify(deletationRequestRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(deletationRequestRepositoryMock);
	}
	
	@Test
	public void testFindAll() {
		/*
		Kako za testove koristimo mokovane repository objekte moramo da definišemo šta će se desiti kada se
		pozove određena metoda kombinacijom "when"-"then" Mockito metoda.
		 */
		
		// 1. Definisanje ponašanja mock objekata
		when(deletationRequestRepositoryMock.findAll()).thenReturn(Arrays.asList(new DeletationRequest(DB_ID, false, RequestStatus.Pending, new User(), "reason")));
		
		// 2. Akcija
		List<DeletationRequest> deletationRequests = deletationRequestService.findAll();
		
		// 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
		assertThat(deletationRequests).hasSize(1);
		assertEquals(deletationRequests.get(0).getId(), DB_ID);
		
		/*
		Možemo verifikovati ponašanje mokovanih objekata pozivanjem verify* metoda.
		 */
		verify(deletationRequestRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(deletationRequestRepositoryMock);
	}
	
	
	
	
	

}
