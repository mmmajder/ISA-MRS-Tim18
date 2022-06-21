package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import mrsa.tim018.dto.DeletationRequestDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;
import mrsa.tim018.repository.DeletationRequestRepository;

@Service
public class DeletationRequestService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeletationRequestRepository deleteRequestRepository;
	 
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

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
	
	@Transactional(readOnly = false, rollbackFor = ObjectOptimisticLockingFailureException.class)
	public DeletationRequest acceptDeclineDeletionRequest(long id, String comment, boolean isAccept) {
		
		DeletationRequest deletionRequest = setDeletionRequestStatus(id, isAccept);
         
        if (isAccept) {
			setUserIsDelete(deletionRequest);
        } 
        
        sendRequestAcceptDeclineMail(comment, isAccept);
		 
		return deletionRequest;  
	}

	private void sendRequestAcceptDeclineMail(String comment, boolean isAccept) {
		try {
			if (isAccept) {
	        	emailService.sendDeleteProfileResponseAsync(RequestStatus.Accepted, comment);
			} else {
				emailService.sendDeleteProfileResponseAsync(RequestStatus.Declined, comment);
			}
		} 
		catch (Exception e) { 
		}  
	}

	private void setUserIsDelete(DeletationRequest deletionRequest) {
		User user = userService.findOne(deletionRequest.getUser().getID());
		logger.info("> update user status: id:{}", user.getID());
		user.setDeleted(true);  
		userService.saveChanges(user); 
		logger.info("< update user status: id:{}", user.getID());
	}

	private DeletationRequest setDeletionRequestStatus(long id, boolean isAccept) {
		logger.info("> update id:{}", id);
		DeletationRequest deletionRequest = findOne(id); 
		if (isAccept) {
			deletionRequest.setStatus(RequestStatus.Accepted);
		} else {
			deletionRequest.setStatus(RequestStatus.Declined);
		} 
		save(deletionRequest); 
		logger.info(deletionRequest.toString());
        logger.info("< update id:{}", id);
		return deletionRequest;
	}

	@Transactional
	public List<DeletationRequestDTO> getPendingDeletationRequests() {
		List<DeletationRequest> deletionRequests = findPending();
		// convert clients to DTOs
		List<DeletationRequestDTO> deletionRequestsDTO = new ArrayList<>();
		for (DeletationRequest s : deletionRequests) {
			deletionRequestsDTO.add(new DeletationRequestDTO(s));
		}  
		return deletionRequestsDTO;
	}
}
