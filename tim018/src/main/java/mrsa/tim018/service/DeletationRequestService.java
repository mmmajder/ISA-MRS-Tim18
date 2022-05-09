package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.repository.DeletationRequestRepository;

@Service
public class DeletationRequestService {
	
	@Autowired
	private DeletationRequestRepository deleteRequestRepository;	

	public DeletationRequest findOne(Long id) {
		return deleteRequestRepository.findById(id).orElse(null);
	}
	
	public List<DeletationRequest> findPending() {
		return deleteRequestRepository.findAllByStatus(RequestStatus.Pending);
	}

	public List<DeletationRequest> findAll() {
		return deleteRequestRepository.findAll();
	}
	
	public Page<DeletationRequest> findAll(Pageable page) {
		return deleteRequestRepository.findAll(page);
	}

	public DeletationRequest save(DeletationRequest deleteRequest) {
		return deleteRequestRepository.save(deleteRequest);
	}

	public void remove(Long id) {
		deleteRequestRepository.deleteById(id);
	}
	
	public DeletationRequest create(Client client, String reason) {
		DeletationRequest deleteRequest = new DeletationRequest(client, reason);
		save(deleteRequest);
		return deleteRequest;
	}
	
	public DeletationRequest create(Renter renter, String reason) {
		DeletationRequest deleteRequest = new DeletationRequest(renter, reason);
		save(deleteRequest);
		return deleteRequest;
	}

}
