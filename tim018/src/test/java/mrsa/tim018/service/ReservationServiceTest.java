package mrsa.tim018.service;

import static mrsa.tim018.constants.DeletationRequestConstants.DB_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.constants.AssetConstants;
import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.LoyaltyState;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserType;
import mrsa.tim018.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepositoryMock;
	
	@Mock
	private Reservation reservationMock;
	
	@InjectMocks
	private ReservationService reservationService;
	
	@Test()
    public void makeReservationTest() throws Exception {
		Asset asset = new Asset();
		asset.setId(1L);
		asset.setAddress("address");
		asset.setAssetType(AssetType.BOAT);
		asset.setAverageRating(0);
		asset.setCalendar(new AssetCalendar());
		asset.setCancelationConditions(0);
		asset.setDeleted(false);
		asset.setDescription("description");
		asset.setName("name");
		asset.setPrice(0);
		asset.setRenter(new Renter());
		asset.setRules("rules");
		asset.setSubscriptions(new ArrayList<>());
		
		Client client = new Client();
		client.setId(1L); 
		client.setDeleted(false);
		client.setFirstName("firstName");
		client.setLastName("lastName"); 
		client.setAddress("address"); 
		client.setCity("city"); 
		client.setState("state"); 
		client.setPhoneNum("phoneNum"); 
		client.setUserType(UserType.Client); 
		client.setLoyaltyPoints(10);
		client.setEmail("email");
		client.setPassword("password");
		client.setEnabled(true);
		client.setProfilePhotoId("profilePhotoId");
		
		TimeRange timeRange = new TimeRange();
		timeRange.setFromDateTime(LocalDateTime.now());
		timeRange.setToDateTime(LocalDateTime.now());
		
		Reservation newReservation = new Reservation(asset, client, timeRange);
		
		when(reservationRepositoryMock.save(newReservation)).thenReturn(newReservation);
		
		newReservation = reservationService.create(asset, client, timeRange);
		
		Optional<Reservation> optionalReservation = Optional.of(newReservation);
		when(reservationRepositoryMock.findById(1L)).thenReturn(optionalReservation);
		
		Reservation dbReservation = reservationService.findOne(1L);
		
        assertThat(dbReservation.getAsset().getName()).isEqualTo(newReservation.getAsset().getName()); 
        assertThat(dbReservation.getClient().getID()).isEqualTo(newReservation.getClient().getID());
        assertThat(dbReservation.getTimeRange().getId()).isEqualTo(newReservation.getTimeRange().getId());
    }
	
	@Test()
    public void getPastRenterReservationsTest() throws Exception {
		
		TimeRange timeRangeFuture = new TimeRange();
		timeRangeFuture.setFromDateTime(LocalDateTime.of(3000, 1, 1, 1, 1));
		timeRangeFuture.setToDateTime(LocalDateTime.of(3000, 11, 1, 1, 1));
		
		Reservation r = new Reservation();
		r.setID(1L);
        r.setAsset(new Asset());
        r.setAssetReviewId(null);
		r.setDeleted(false);
		r.setClient(new Client()); 
		r.setTimeRange(timeRangeFuture);
		r.setStatus(ReservationStatus.Future);
		r.setTotalPrice(100);
		r.setCancelationFee(5);
		r.setLoyaltyState(new LoyaltyState());
		
		TimeRange timeRangePast = new TimeRange();
		timeRangePast.setFromDateTime(LocalDateTime.of(2000, 1, 1, 1, 1));
		timeRangePast.setToDateTime(LocalDateTime.of(2000, 11, 1, 1, 1));
		
		Reservation r2 = new Reservation();
		r.setID(2L);
        r2.setAsset(new Asset());
        r2.setAssetReviewId(null);
		r2.setDeleted(false);
		r2.setClient(new Client()); 
		r2.setTimeRange(timeRangePast);
		r2.setStatus(ReservationStatus.Finished);
		r2.setTotalPrice(200);
		r2.setCancelationFee(5);
		r2.setLoyaltyState(new LoyaltyState());
		
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(r);
		reservations.add(r2);
		
		when(reservationRepositoryMock.getRenterReservations(1L)).thenReturn(reservations);
		
		List<ReservationDTO> pastReservations = reservationService.getPastRenterReservations(1L);
		

        assertThat(pastReservations.size()).isEqualTo(1); 
		
        assertThat(pastReservations.get(0).getTotalPrice()).isEqualTo(200); 
    }
	
	@Test
	public void findOneTest() {
		TimeRange timeRangeFuture = new TimeRange();
		timeRangeFuture.setFromDateTime(LocalDateTime.of(3000, 1, 1, 1, 1));
		timeRangeFuture.setToDateTime(LocalDateTime.of(3000, 11, 1, 1, 1));
		
		Reservation r = new Reservation();
		r.setID(1L);
        r.setAsset(new Asset());
        r.setAssetReviewId(null);
		r.setDeleted(false);
		r.setClient(new Client()); 
		r.setTimeRange(timeRangeFuture);
		r.setStatus(ReservationStatus.Future);
		r.setTotalPrice(100);
		r.setCancelationFee(5);
		r.setLoyaltyState(new LoyaltyState());
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(r);
		
		when(reservationRepositoryMock.findById(1L)).thenReturn(Optional.of(reservationMock));
		
		// 2. Akcija
		Reservation dbReservation = reservationService.findOne(DB_ID);
		
		// 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
		assertEquals(reservationMock, dbReservation);
		
        verify(reservationRepositoryMock, times(1)).findById(DB_ID);
        verifyNoMoreInteractions(reservationRepositoryMock);
	}
}
