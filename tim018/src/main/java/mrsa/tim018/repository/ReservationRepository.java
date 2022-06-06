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
	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.timeRange.fromDateTime < now() AND r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyReports();
//	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.asset.id = :assetId AND r.timeRange.fromDateTime < now() AND r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyReports(@Param("assetId") Long assetId);
	
	// monthly
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyReports(String period);
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.timeRange.fromDateTime < now() AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyCompletedReports();
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.status = 1 AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyCanceledReports();
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.timeRange.fromDateTime > now() AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyPotentialReports();
	
	// monthly assetId
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyReports(@Param("assetId") Long assetId);
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.timeRange.fromDateTime < now() AND r.status != 1 AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyCompletedReports(@Param("assetId") Long assetId);
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.status = 1 AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyCanceledReports(@Param("assetId") Long assetId);
	
	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
			+ "FROM Reservation r "
			+ "WHERE r.asset.id = :assetId AND r.timeRange.fromDateTime > now() AND r.status != 1 AND r.isDeleted = FALSE"
			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
	Collection<Report> getMonthlyPotentialReports(@Param("assetId") Long assetId);
	
//	SELECT date_trunc('month', txn_date) AS txn_month, sum(amount) as monthly_sum
//    FROM yourtable
//GROUP BY txn_month
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc(':period', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyReports(String period);
	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.timeRange.fromDateTime < now() AND r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyCompletedReports();
//	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.status = 1 AND r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyCanceledReports();
//	
//	@Query("SELECT new mrsa.tim018.model.Report(date_trunc('month', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id))"
//			+ "FROM Reservation r "
//			+ "WHERE r.timeRange.fromDateTime > now() AND r.isDeleted = FALSE"
//			+ " GROUP BY date_trunc('month', r.timeRange.fromDateTime)")
//	Collection<Report> getMonthlyPotentialReports();
}