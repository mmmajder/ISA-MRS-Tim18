package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Client;
import mrsa.tim018.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;	

	public Client findOne(Integer id) {
		return clientRepository.findById(id).orElseGet(null);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Page<Client> findAll(Pageable page) {
		return clientRepository.findAll(page);
	}

	public Client save(Client student) {
		return clientRepository.save(student);
	}

	public void remove(Integer id) {
		clientRepository.deleteById(id);
	}
}
