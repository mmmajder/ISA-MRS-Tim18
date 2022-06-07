package mrsa.tim018.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Report;
import mrsa.tim018.model.ReportReservationStatus;
import mrsa.tim018.repository.ReportAdminRepository;
import mrsa.tim018.repository.ReportRepository;
import mrsa.tim018.utils.TimeUtils;

@Service
@Transactional
public class AdminReportsService {
	
	@Autowired
	private ReportAdminRepository adminReportRepository;

	public List<Report> getReports(String period, String assetType, String fromDate, String toDate) {
		List<Report> reports = (List<Report>) adminReportRepository.getReports(period, assetType, fromDate, toDate);
		setGroups(reports, period);
		sortReports(reports);
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
	
	private void sortReports(List<Report> reports) {
		reports.sort(
				(Report r1, Report r2) -> r1.getGroup().compareTo(r2.getGroup())
				);
	}

}
