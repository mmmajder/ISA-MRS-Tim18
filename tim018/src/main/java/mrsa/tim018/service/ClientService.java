package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Client;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationFinances;
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
}
