package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

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
	public ResponseEntity<List<ClientDTO>> getStudentsPage(Pageable page) {

		// page object holds data about pagination and sorting
		// the object is created based on the url parameters "page", "size" and "sort"
		Page<Client> students = clientService.findAll(page);

		// convert students to DTOs
		List<ClientDTO> studentsDTO = new ArrayList<>();
		for (Client s : students) {
			studentsDTO.add(new ClientDTO(s));
		}

		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> getStudent(@PathVariable Integer id) {

		Client student = clientService.findOne(id);

		// studen must exist
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ClientDTO(student), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {

		Client client = new Client();
		client.setFirstName(clientDTO.getFirstName());
		client.setLastName(clientDTO.getLastName());
		client.setDeleted(clientDTO.isDeleted());
		client.setAddress(clientDTO.getAddress());
		client.setCity(clientDTO.getCity());
		client.setState(clientDTO.getState());
		client.setPhoneNum(clientDTO.getPhoneNum());
		client.setLoyaltyPoints(clientDTO.getLoyaltyPoints());
		client.setPenaltyPoints(clientDTO.getPenaltyPoints());

		client = clientService.save(client);
		return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<ClientDTO> updateStudent(@RequestBody ClientDTO clientDTO) {

		// a student must exist
		Client client = clientService.findOne(Math.toIntExact(clientDTO.getId()));

		if (client == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		client.setFirstName(clientDTO.getFirstName());
		client.setLastName(clientDTO.getLastName());
		client.setDeleted(clientDTO.isDeleted());
		client.setAddress(clientDTO.getAddress());
		client.setCity(clientDTO.getCity());
		client.setState(clientDTO.getState());
		client.setPhoneNum(clientDTO.getPhoneNum());
		client.setLoyaltyPoints(clientDTO.getLoyaltyPoints());
		client.setPenaltyPoints(clientDTO.getPenaltyPoints());

		client = clientService.save(client);
		return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {

		Client client = clientService.findOne(id);

		if (client != null) {
			clientService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
