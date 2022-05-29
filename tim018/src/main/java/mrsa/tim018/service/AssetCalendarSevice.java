package mrsa.tim018.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mrsa.tim018.dto.AppointmentCreationDTO;
import mrsa.tim018.dto.calendar.TimeRangeMergeElement;
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
				List<TimeRange> ranges = calendar.getAvailable();
				ranges = addAvailablePattern(ranges, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime()));
				calendar.setAvailable(ranges);
			} else {
				List<TimeRange> ranges = calendar.getAvailable();
				ranges = addAvailable(ranges, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime()));
				calendar.setAvailable(ranges);
			}
			return calendar;
		case SpecialOffer:
			List<SpecialOffer> offers = calendar.getSpecialPrice();
			Asset asset = assetRepository.getById(appointment.getAssetId());
			TimeRange timeRange = new TimeRange(false, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime()));
			offers.add(new SpecialOffer(false, asset, null, timeRange, "", appointment.getDiscount()));
			calendar.setSpecialPrice(offers);
			return calendar;
		default:
			return null;
		}
	}
	
	public List<Asset> availableInRange(List<Asset> assets, LocalDate startDate, LocalDate endDate) {
		if (startDate==null && endDate==null) {
			return assets;
		}
		List<Asset> availableAssets = new ArrayList<Asset>();
		for (Asset asset : assets) { 
			for (TimeRange timeRange : asset.getCalendar().getAvailable()) {
				if ((timeRange.getFromDateTime().isBefore(startDate.atTime(0, 0)) || startDate==null)&& (timeRange.getToDateTime().isAfter(endDate.atTime(0, 0))|| endDate==null) ) {
					availableAssets.add(asset);
					break;
				}
			}
		}
		return availableAssets;
	}

	public AssetCalendar removeAppointment(AssetCalendar calendar, AppointmentCreationDTO appointment) {
		List<TimeRange> ranges = calendar.getAvailable();
		ranges = removeAvailable(ranges, StringUtils.getDatetime(appointment.getFromDateTime()), StringUtils.getDatetime(appointment.getToDateTime()));
		calendar.setAvailable(ranges);
		return calendar;
	}
	
	public List<TimeRange> removeAvailable(List<TimeRange> ranges, LocalDateTime fromDateTime,
			LocalDateTime toDateTime) {
		List<TimeRange> retData = new ArrayList<TimeRange>();
		for (TimeRange timeRange : ranges) {
			if (fromDateTime.isBefore(timeRange.getFromDateTime())) {
				if (toDateTime.isBefore(timeRange.getFromDateTime())) {
					retData.add(timeRange);
					continue;
				}
				else if (toDateTime.isBefore(timeRange.getToDateTime())) {
					timeRange.setFromDateTime(toDateTime);
					retData.add(timeRange);
				} //else whole range is deleted
			}
			else if (fromDateTime.isBefore(timeRange.getToDateTime())) {
				if (toDateTime.isAfter(timeRange.getToDateTime())) {
					timeRange.setToDateTime(fromDateTime);
					retData.add(timeRange);
				}
				else if (toDateTime.isBefore(timeRange.getToDateTime())) {
					retData.add(new TimeRange(false, timeRange.getFromDateTime(), fromDateTime));
					retData.add(new TimeRange(false, toDateTime, timeRange.getToDateTime()));
				} 
			}
			else {
				retData.add(timeRange);
			}
		}
		return retData;
	}

	

	public List<TimeRange> addAvailable(List<TimeRange> ranges, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		List<TimeRangeMergeElement> elems = new ArrayList<TimeRangeMergeElement>();
		for (TimeRange timeRange : ranges) {
			elems.add(new TimeRangeMergeElement(timeRange, false));
		}
		elems.add(new TimeRangeMergeElement(new TimeRange(false, startDateTime, endDateTime), false));
		return getNewListAvailable(elems);
	}

	private List<TimeRange> getNewListAvailable(List<TimeRangeMergeElement> elems) {
		for (TimeRangeMergeElement elem1 : elems) {
			if (elem1.isReduced()) {
				continue;
			}
			for (TimeRangeMergeElement elem2 : elems) {
				if (elem1.equals(elem2)) {
					continue;
				}
				if (elem2.isReduced()) {
					continue;  
				} 
				if (elem2.getTimeRange().getFromDateTime().isBefore(elem1.getTimeRange().getFromDateTime())) {
					if (elem2.getTimeRange().getToDateTime().isAfter(elem1.getTimeRange().getFromDateTime())) {
						elem2.setReduced(true);
						elem1.getTimeRange().setFromDateTime(elem2.getTimeRange().getFromDateTime());
						if (elem2.getTimeRange().getToDateTime().isAfter(elem1.getTimeRange().getToDateTime())) {
							elem1.getTimeRange().setToDateTime(elem2.getTimeRange().getToDateTime());
						}
					}
				}
				else if(elem2.getTimeRange().getFromDateTime().isBefore(elem1.getTimeRange().getToDateTime())) {
					elem2.setReduced(true);
					if (elem2.getTimeRange().getToDateTime().isAfter(elem1.getTimeRange().getToDateTime())) {
						elem1.getTimeRange().setToDateTime(elem2.getTimeRange().getToDateTime());
					}
				}
			}
		}
		List<TimeRange> retData = new ArrayList<TimeRange>();
		for (TimeRangeMergeElement timeRangeMergeElement : elems) {
			if (!timeRangeMergeElement.isReduced()) {
				retData.add(timeRangeMergeElement.getTimeRange());
			}
		}   
		return retData; 
	}
	
	private List<TimeRange> addAvailablePattern(List<TimeRange> ranges, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		List<TimeRangeMergeElement> elems = new ArrayList<TimeRangeMergeElement>();
		for (TimeRange timeRange : ranges) {
			elems.add(new TimeRangeMergeElement(timeRange, false));
		}
		
		for (int i=0; i<52; i++) {
			LocalDateTime fromDateTime = startDateTime.plusDays(i*7);
			LocalDateTime toDateTime = endDateTime.plusDays(i*7);
			elems.add(new TimeRangeMergeElement(new TimeRange(false, fromDateTime, toDateTime), false));
		}
		
		return getNewListAvailable(elems);
	}

	
}
