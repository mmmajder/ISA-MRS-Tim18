package mrsa.tim018.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Reservation;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.AssetRepository;

@Service
@Transactional
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssetCalendarSevice assetCalendarService;

	public Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
	
	public Asset findOne(Long id) {
		return assetRepository.findById(id).orElse(null);
	}

	public List<Asset> findAll() {
		return assetRepository.findAll();
	}
	
	public Asset findById(long id) {
		return assetRepository.findById(id);
	}
	
	public List<Asset> findAllByRenterId(long id) {
		return assetRepository.findAllByRenterId(id);
	}

	public void addRegularReservation(Reservation reservation) {
		Asset asset = reservation.getAsset();
		TimeRange timeRange = reservation.getTimeRange();
		
		AssetCalendar calendar= asset.getCalendar();
		calendar.getReserved().add(reservation);
		List<TimeRange> ranges = assetCalendarService.removeAvailable(calendar.getAvailable(), timeRange.getFromDateTime(), timeRange.getFromDateTime());
		calendar.setAvailable(ranges);
		save(asset);
	}
}
