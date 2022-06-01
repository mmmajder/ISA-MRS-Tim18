package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ClientsComplaintDTO;
import mrsa.tim018.model.ClientsComplaint;
import mrsa.tim018.service.ClientsComplaintService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/clientsComplaint")
public class ClientsComplaintController {

	@Autowired
	private ClientsComplaintService clientsComplaintService;	

	/*@GetMapping(value = "/all")
	public ResponseEntity<List<ClientsComplaintDTO>> getAllClientComplaints() {

		List<ClientsComplaint> clientsComplaints = clientsComplaintService.findAll();

		// convert clients to DTOs
		List<ClientsComplaintDTO> clientsComplaintDTOs = new ArrayList<ClientsComplaintDTO>();
		for (ClientsComplaint s : clientsComplaints) {
			clientsComplaintDTOs.add(new ClientsComplaintDTO(s));
		}

		return new ResponseEntity<>(clientsComplaintDTOs, HttpStatus.OK);
	}*/
}
