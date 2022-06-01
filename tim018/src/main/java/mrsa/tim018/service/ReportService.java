package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Reservation;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReservationService reservationService;
	
	public void generateWeeklyReport(Long assetId) {
		
	}
	
	public void generateMonthlyReport(Long assetId) {
		List<Reservation> reservations = reservationService.getAssetReservations(assetId);
	}
	
	public void generateYearlyReport(Long assetId) {
		
	}
}
