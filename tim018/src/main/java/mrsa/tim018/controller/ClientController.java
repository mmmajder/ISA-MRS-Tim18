package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.mapper.ClientMapper;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;
import mrsa.tim018.service.ClientService;
import mrsa.tim018.service.DeletationRequestService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DeletationRequestService deleteRequestService;	

	@GetMapping(value = "/all")
	public ResponseEntity<List<ClientDTO>> getAllClients() {

		List<Client> clients = clientService.findAll();

		// convert clients to DTOs
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for (Client s : clients) {
			clientsDTO.add(new ClientDTO(s));
		}

		return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> getclient(@PathVariable Long id) {
		Client client = clientService.findOne(id);

		if (client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {

		Client client = new Client();
		client = ClientMapper.mapToClient(client, clientDTO);

		client = clientService.save(client);
		return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('CLIENT')")
	@PutMapping(consumes = "application/json")
	public ResponseEntity<ClientDTO> updateclient(@RequestBody ClientDTO clientDTO) {

		// a client must exist
		Client client = clientService.findOne(clientDTO.getId());

		if (client == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		client = ClientMapper.mapToClient(client, clientDTO);
		client = clientService.save(client);
		return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
	}

	@PostMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<DeletationRequest> createDeletionRequest(@PathVariable Long id, @RequestBody String reason) {
		Client client = clientService.findOne(id);
		DeletationRequest deletRequest = deleteRequestService.create(client, reason);
		if (deletRequest != null) {
			return new ResponseEntity<>(deletRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
