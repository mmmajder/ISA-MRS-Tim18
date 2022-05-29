package mrsa.tim018.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.ReservationFinances;

public interface ReservationFinancesRepository extends JpaRepository<ReservationFinances, Long>{
	public List<ReservationFinances> findAll();

}
