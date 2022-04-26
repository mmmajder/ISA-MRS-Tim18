package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.TimeRange;
import mrsa.tim018.repository.AssetCalendarRepository;
import mrsa.tim018.utils.StringUtils;

@Service
public class AssetCalendarSevice {
	@Autowired
	private AssetCalendarRepository assetCalendarRepository;
	
	public AssetCalendar save(AssetCalendar calendar) {
		return assetCalendarRepository.save(calendar);
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
			//TODO
			break;
		default:
			return null;
		}
		return null;
	}
}
