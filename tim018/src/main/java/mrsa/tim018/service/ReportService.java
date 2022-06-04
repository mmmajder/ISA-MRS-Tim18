package mrsa.tim018.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.ReportFilters;
import mrsa.tim018.model.ReportPeriod;
import mrsa.tim018.model.ReportReservationStatus;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.repository.ReportRepository;
import mrsa.tim018.repository.ReservationRepository;
import mrsa.tim018.utils.TimeUtils;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	public List<Report> getReports(Long renterId, boolean completed, boolean canceled, boolean potential, String period) {
		if (completed && canceled && potential) 
			return getCombinedReports(renterId, period);
		
		if (!completed && !canceled && !potential)
			return new ArrayList<>();
		
		List<Report> reports = getReportsFromRepo(renterId, completed, canceled, potential, period); 
		setGroups(reports, period);
		reports = formReports(reports);
		
		return reports;
	}
	
	private List<Report> getCombinedReports(Long renterId, String period){
		List<Report> reports = (List<Report>) reportRepository.getReports(renterId, ReportReservationStatus.ALL, period);
		setGroups(reports, period);
		sortReports(reports);
		return reports;
	}
	
	private List<Report> getReportsFromRepo(Long renterId, boolean completed, boolean canceled, boolean potential, String period){
		List<Report> reports = new ArrayList<Report>(); 
		
		if (completed)
			reports.addAll((List<Report>) reportRepository.getReports(renterId, ReportReservationStatus.COMPLETED, period));
			
		if (canceled)
			reports.addAll((List<Report>) reportRepository.getReports(renterId, ReportReservationStatus.CANCELED, period));
		
		if (potential)
			reports.addAll((List<Report>) reportRepository.getReports(renterId, ReportReservationStatus.POTENTIAL, period));
		
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
