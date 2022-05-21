package mrsa.tim018.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.ReservationStatus;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	public List<Reservation> findAllByStatus(ReservationStatus status);
	
	@Query(value = "select * from reservation "
			+ "left join timeRange on timeRange.id = reservation.timeRange.id "
			+ "where timeRange.endDate <= :endDate" , nativeQuery = true)
    public List<Reservation> findAllByEndDate(@Param("endDate") Date endDate);
	 
//	@Query(value = "SELECT * nextval('userSeqGen')", nativeQuery = true)
//	public List<Reservation> findAllByEndDate(LocalDateTime endDate);
}