package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.ReportReservationStatus;
import mrsa.tim018.repository.ReportRepository;

@Service
@Transactional
public class AdminReportsService {
	
	/*@Autowired
	private ReportRepository reportRepository;

	public List<Report> getReports(boolean completed, boolean canceled, boolean potential, String period, Long assetType, String fromDate, String toDate) {
		if (completed && canceled && potential) 
			return getCombinedReports(period, assetType, fromDate, toDate);
		
		if (!completed && !canceled && !potential)
			return new ArrayList<>();
		
		List<Report> reports = getReportsFromRepo(renterId, completed, canceled, potential,
				period, assetType, fromDate, toDate); 
		setGroups(reports, period);
		reports = formReports(reports);
		
		return reports;
	}

	private List<Report> getCombinedReports(String period, Long assetType, String fromDate, String toDate) {
		List<Report> reports = (List<Report>) reportRepository.getReports(renterId, ReportReservationStatus.ALL, period, assetId, fromDate, toDate);
		setGroups(reports, period);
		sortReports(reports);
		return reports;
	}*/

}
