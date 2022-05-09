package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.AssetCalendarRepository;
import mrsa.tim018.repository.AssetRepository;
import mrsa.tim018.repository.ClientRepository;
import mrsa.tim018.utils.StringUtils;

@Service
@Transactional
public class AssetCalendarSevice {
	@Autowired
	private AssetCalendarRepository assetCalendarRepository;
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public AssetCalendar save(AssetCalendar calendar) {
		return assetCalendarRepository.save(calendar);
	}
	
	public AssetCalendar createNewCalendar() {
		AssetCalendar assetCalendar = new AssetCalendar();
		return assetCalendarRepository.save(assetCalendar);
	}
	
	public AssetCalendar addAppointment(AssetCalendar calendar, AppointmentCreationDTO appointment) {
		switch (appointment.getType()) {
		case Available:
			if (appointment.isPattern()) {
				List<TimeRange> ranges = calendar.getAvailablePattern();
				ranges.add(new TimeRange(false, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime())));
				calendar.setAvailablePattern(ranges);
			} else {
				List<TimeRange> ranges = calendar.getAvailableSingle();
				ranges.add(new TimeRange(false, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime())));
				calendar.setAvailableSingle(ranges);
			}
			return calendar;
		case SpecialOffer:
			List<SpecialOffer> offers = calendar.getSpecialPriceSingle();
			Asset asset = assetRepository.getById(appointment.getAssetId());
			TimeRange timeRange = new TimeRange(false, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime()));
			offers.add(new SpecialOffer(false, asset, null, timeRange, "", appointment.getDiscount()));
			calendar.setSpecialPriceSingle(offers);
			return calendar;
		default:
			return null;
		}
	}
}
