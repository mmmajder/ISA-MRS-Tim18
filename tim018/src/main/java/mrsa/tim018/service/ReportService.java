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
import mrsa.tim018.model.ReportFilters;
import mrsa.tim018.model.Reservation;
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
	
	public List<Report> getMonthlyReports(ReportFilters reportFilters) {
		return getMonthlyReports(reportFilters.isCompleted(), reportFilters.isCanceled(), reportFilters.isPotential());
	}
	
	public List<Report> getMonthlyReports(boolean completed, boolean canceled, boolean potential) {
		if (completed && canceled && potential) 
			return getMontlhyCombinedReports();
		
		if (!completed && !canceled && !potential)
			return new ArrayList<>();
		
		List<Report> reports = getReportsFromRepo(completed, canceled, potential); 
		setGroups(reports);
		reports = formReports(reports);
		
		return reports;
	}
	
	private List<Report> getMontlhyCombinedReports(){
		List<Report> reports = (List<Report>) reservationRepository.getMonthlyReports();
		setGroups(reports);
		sortReports(reports);
		return reports;
	}
	
	private List<Report> getReportsFromRepo(boolean completed, boolean canceled, boolean potential){
		List<Report> reports = new ArrayList<Report>(); 
		
		if (completed)
			reports.addAll((List<Report>) reservationRepository.getMonthlyCompletedReports());
			
		if (canceled)
			reports.addAll((List<Report>) reservationRepository.getMonthlyCanceledReports());
		
		if (potential)
			reports.addAll((List<Report>) reservationRepository.getMonthlyPotentialReports());
		
		return reports;
	}
	
	private void setGroups(List<Report> reports) {
		for (Report r : reports) {
			LocalDate date = TimeUtils.getLocalDate(r.getTimestamp());
			r.setGroup(TimeUtils.formatYearMonth(date));
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
