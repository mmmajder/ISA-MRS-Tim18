package mrsa.tim018.dto;

import java.time.LocalDateTime;

import mrsa.tim018.model.Registration;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.User;

public class RegistrationDTO {
	private long id;
	private RequestStatus status;
	private User user;
	private LocalDateTime registrationDateTime;

	public RegistrationDTO(long id, RequestStatus status, User user, LocalDateTime registrationDateTime) {
		super();
		this.id = id;
		this.status = status;
		this.user = user;
		this.registrationDateTime = registrationDateTime;
	}

	public RegistrationDTO(Registration registration) {
		this(registration.getId(), registration.getStatus(), registration.getUser(),
				registration.getRegistrationDateTime());
	}

	public RegistrationDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getRegistrationDateTime() {
		return registrationDateTime;
	}

	public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
