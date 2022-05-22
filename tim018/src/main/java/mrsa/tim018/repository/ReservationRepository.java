package mrsa.tim018.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}