package mrsa.tim018.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.ReportReservationStatus;
import mrsa.tim018.repository.ReportRepository;
import mrsa.tim018.utils.TimeUtils;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	public List<Report> getReports(Long renterId, boolean completed, boolean canceled, boolean potential,
			String period, Long assetId, String fromDate, String toDate) {
		if (completed && canceled && potential) 
			return getCombinedReports(renterId, period, assetId, fromDate, toDate);
		
		if (!completed && !canceled && !potential)
			return new ArrayList<>();
		
		List<Report> reports = getReportsFromRepo(renterId, completed, canceled, potential,
				period, assetId, fromDate, toDate); 
		setGroups(reports, period);
		reports = formReports(reports);
		
		return reports;
	}
	
	private List<Report> getCombinedReports(Long renterId, String period, Long assetId, String fromDate, String toDate){
		List<Report> reports = reportRepository.getReports(renterId, ReportReservationStatus.ALL, period, assetId, fromDate, toDate);
		setGroups(reports, period);
		sortReports(reports);
		return reports;
	}
	
	private List<Report> getReportsFromRepo(Long renterId, boolean completed, boolean canceled, boolean potential,
			String period, Long assetId, String fromDate, String toDate){
		List<Report> reports = new ArrayList<>(); 
		
		if (completed)
			reports.addAll(reportRepository.getReports(renterId, ReportReservationStatus.COMPLETED, period, assetId, fromDate, toDate));
			
		if (canceled)
			reports.addAll(reportRepository.getReports(renterId, ReportReservationStatus.CANCELED, period, assetId, fromDate, toDate));
		
		if (potential)
			reports.addAll(reportRepository.getReports(renterId, ReportReservationStatus.POTENTIAL, period, assetId, fromDate, toDate));
		
		return reports;
	}
	
	private void setGroups(List<Report> reports, String period) {
		for (Report r : reports) {
			LocalDate date = TimeUtils.getLocalDate(r.getTimestamp());
			
			switch(period) {
			case "month" : r.setGroup(TimeUtils.formatYearMonth(date)); break;
			case "year" : r.setGroup(TimeUtils.formatYear(date)); break;
			case "week" : r.setGroup(TimeUtils.formatYearMonthDay(date)); break;
			default: r.setGroup(null);
			}
			
		}
	}
	
	private List<Report> formReports(List<Report> reports){
		Map<String, Report> groupedReports = new HashMap<>();
		
		for (Report r : reports) {
			String group = r.getGroup();
			
			if (groupedReports.containsKey(group)) 
				incrementReportValues(groupedReports, r);
			else 
				addNewReport(groupedReports, r);
		}
		
		List<Report> finalizedReports = new ArrayList<>(groupedReports.values());
		sortReports(finalizedReports);
		
		return finalizedReports;
	}
	
	private void sortReports(List<Report> reports) {
		reports.sort(
				(Report r1, Report r2) -> r1.getGroup().compareTo(r2.getGroup())
				);
	}
	
	private void incrementReportValues(Map<String, Report> groupedReports, Report r) {
		Report report = groupedReports.get(r.getGroup());
		report.incIncome(r.getIncome());
		report.incNumberOfReservations(r.getNumberOfReservations());
	}
	
	private void addNewReport(Map<String, Report> groupedReports, Report r) {
		Report newReport = new Report(r.getGroup());
		newReport.setIncome(r.getIncome());
		newReport.setNumberOfReservations(r.getNumberOfReservations());
		groupedReports.put(r.getGroup(), newReport);
	}
}
