package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Admin;
import mrsa.tim018.model.ClientsComplaint;
import mrsa.tim018.model.User;

public interface ClientsComplaintRepository  extends JpaRepository<ClientsComplaint, Long> {
	public ClientsComplaint findById(long id);
	public ClientsComplaint save(ClientsComplaint clientsComplaint);
	public List<ClientsComplaint> findAll();

}
