package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.DeletationRequest;
import mrsa.tim018.model.RequestStatus;

public interface DeletationRequestRepository extends JpaRepository<DeletationRequest, Long>{

	public List<DeletationRequest> findAllByStatus(RequestStatus status);
}
