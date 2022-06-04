package mrsa.tim018.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.ReportReservationStatus;

@Service
@Transactional
public class ReportRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private final String queryBegining = " SELECT new mrsa.tim018.model.Report(date_trunc('%s', r.timeRange.fromDateTime), SUM(r.totalPrice), COUNT(r.id)) "
			+ " FROM Reservation r "
			+ " WHERE r.isDeleted = FALSE ";
	private final String completedPart = " AND r.timeRange.fromDateTime < now() AND r.status != 1 ";
	private final String canceledPart = " AND r.status = 1 ";
	private final String potentialPart = " AND r.timeRange.fromDateTime > now() AND r.status != 1 ";
	private final String groupByPart = " GROUP BY date_trunc('%s', r.timeRange.fromDateTime) ";
	
	@SuppressWarnings("unchecked")
	public List<Report> getReports(Long id, ReportReservationStatus status, String period) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(queryBegining, period))
		.append(getReportReservationStatusPart(status))
		.append(" AND r.asset.renter.id = " + id)
		.append(String.format(groupByPart, period))
		;
		
		System.out.println();
    	Query query = entityManager.createQuery(sb.toString());
    	List<Report> reports = query.getResultList();
    	
    	return reports;
    }
	
	private String getReportReservationStatusPart(ReportReservationStatus status) {
		switch(status) {
		case ALL : return "";
		case COMPLETED : return completedPart;
		case CANCELED : return canceledPart;
		case POTENTIAL : return potentialPart;
		default: return "";
		}
	}
}
