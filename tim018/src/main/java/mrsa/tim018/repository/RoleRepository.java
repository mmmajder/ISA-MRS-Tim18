package mrsa.tim018.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public ArrayList<Role> findAll();

	public Role findByName(String name);
}
