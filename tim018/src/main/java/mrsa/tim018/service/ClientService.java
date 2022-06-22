package mrsa.tim018.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.mapper.ClientMapper;
import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.model.Subscription;
import mrsa.tim018.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ReservationFinancesService reservationFinancesService;

	public Client findOne(Long id) {
		return clientRepository.findById(id).get();
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Page<Client> findAll(Pageable page) {
		return clientRepository.findAll(page);
	}

	public Client save(Client client) {
		return clientRepository.save(client);
	}

	public void remove(Long id) {
		clientRepository.deleteById(id);
	}

	public void addRegularReservation(Reservation reservation) {
		Client client = reservation.getClient();
		client.getReservations().add(reservation);
		ReservationFinances finances = reservationFinancesService.getLast();
		client.setLoyaltyPoints(client.getLoyaltyPoints() + finances.getPointsPerReservation());
	}
	
	public void addSubscription(Subscription subscription) {
		Client client = subscription.getClient();
		client.getSubscriptions().add(subscription);
	}
	
	public void removeSubscription(Subscription subscription) {
		Client client = subscription.getClient();
		client.getSubscriptions().remove(subscription);
		save(client);
	}

	@Scheduled(cron = "0 0 0 1 1/1 *")	
	public void resetPenaltyPoint() {
		List<Client> clients = findAll();
		for (Client client : clients) {
			client.setPenaltyPoints(0);
		}
	}

	@Transactional(readOnly = false)
	public ClientDTO updateclient(ClientDTO clientDTO) {
		Client client = findOne(clientDTO.getId());

		if (client == null) {
			return null;
		}
		client = ClientMapper.mapToClient(client, clientDTO);
		client = save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public List<Reservation> getCurrentReservations(Long clientId, AssetType assetType) {
		List<Reservation> reservations = getMyReservationsByType(clientId, assetType);
		reservations = reservations.stream().filter(r -> r.getTimeRange().getFromDateTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());
		reservations = reservations.stream().filter(r -> r.getStatus() != ReservationStatus.Canceled).collect(Collectors.toList());
		return reservations;
	}

	@Transactional
	public List<Reservation> getPastReservations(Long clientId, AssetType assetType) {
		List<Reservation> reservations = getMyReservationsByType(clientId, assetType);
		reservations = reservations.stream().filter(r -> r.getTimeRange().getFromDateTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());
		return reservations;
	}
	
	@Transactional
	private List<Reservation> getMyReservationsByType(Long clientId, AssetType assetType) {
		Client client = findOne(clientId);
		List<Reservation> reservations = client.getReservations();
		if(assetType!=AssetType.ALL)
			reservations = reservations.stream().filter(r -> r.getAsset().getAssetType() == assetType).collect(Collectors.toList());
		return reservations;
	}
}
