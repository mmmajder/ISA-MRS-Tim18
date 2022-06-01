package mrsa.tim018.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("SELECT r "
			+ "FROM Reservation r "
			+ "WHERE r.asset.renter.id = :renterId AND r.isDeleted = FALSE")
	Collection<Reservation> getRenterReservations(@Param("renterId") Long renterId);
}