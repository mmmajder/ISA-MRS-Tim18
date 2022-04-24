package mrsa.tim018.mapper;

import mrsa.tim018.dto.DeletationRequestDTO;
import mrsa.tim018.model.DeletationRequest;

public class DeletionRequestMapper {

	public static DeletationRequest mapToClient(DeletationRequest deletionRequest, DeletationRequestDTO deletionRequestDTO) {
		deletionRequest.setDeleted(deletionRequestDTO.isDeleted());
		deletionRequest.setStatus(deletionRequestDTO.getStatus());
		deletionRequest.setReason(deletionRequestDTO.getReason());
		deletionRequest.setUser(deletionRequestDTO.getUser());
		return deletionRequest;
	}
}
