package mrsa.tim018.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("SELECT r "
			+ "FROM Reservation r "
			+ "WHERE r.asset.renter.id = :renterId AND r.isDeleted = FALSE")
	Collection<Reservation> getRenterReservations(@Param("renterId") Long renterId);
	
	@Query("SELECT r "
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.isDeleted = FALSE")
	Collection<Reservation> getAssetReservations(@Param("assetId") Long assetId);
	
//	@Query("SELECT r "
//			+ "FROM Reservation r "
//			+ "WHERE r.asset.id = :assetId AND r.isDeleted = FALSE")
//	Collection<Reservation> getAssetYearlyReservations(@Param("assetId") Long assetId);
	
	@Query("SELECT new mrsa.tim018.model.Report(DATE_FORMAT(r.timeRange.fromDateTime,'%Y-%m'), SUM(r.totalPrice), SUM(1))"
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.isDeleted = FALSE"
			+ " GROUP BY DATE_FORMAT(r.timeRange.fromDateTime,'%Y-%m')")
	Collection<Report> getAssetMonthlyReservations(@Param("assetId") Long assetId);
}