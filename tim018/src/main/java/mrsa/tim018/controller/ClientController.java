package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import mrsa.tim018.mapper.ClientMapper;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
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

	// GET /api/clients?page=0&size=5&sort=firstName,DESC
	@GetMapping
	public ResponseEntity<List<ClientDTO>> getclientsPage(Pageable page) {

		// page object holds data about pagination and sorting
		// the object is created based on the url parameters "page", "size" and "sort"
		Page<Client> clients = clientService.findAll(page);

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

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {

		Client client = clientService.findOne(id);

		if (client != null) {
			clientService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<DeletationRequest> createDeletionRequest(@PathVariable Long id, @RequestBody String reason) {
		System.out.println(reason);
		Client client = clientService.findOne(id);
		System.out.println(client);
		DeletationRequest deletRequest = deleteRequestService.create(client, reason);
		System.out.println(deletRequest);
		if (deletRequest != null) {
			return new ResponseEntity<DeletationRequest>(deletRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
