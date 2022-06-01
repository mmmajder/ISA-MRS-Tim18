package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.ClientsComplaint;
import mrsa.tim018.repository.ClientsComplaintRepository;

@Service
public class ClientsComplaintService {
	@Autowired
	private ClientsComplaintRepository clientsComplaintRepository;
	
	public List<ClientsComplaint> findAll() {
		return clientsComplaintRepository.findAll();
	}

}
