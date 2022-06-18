package mrsa.tim018.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.AssetPrice;
import mrsa.tim018.repository.AssetPriceRepository;

@Service
@Transactional
public class AssetPriceService {

	@Autowired
	private AssetPriceRepository priceRepository;
	

	public AssetPrice save(AssetPrice assetPrice){
		return priceRepository.save(assetPrice);
	}
	 
	public Optional<AssetPrice> getPriceById(String id) {
		return priceRepository.findById(id);
	}
	
	public List<AssetPrice> getAssetPrices(Long assetId){ 
		return (List<AssetPrice>) priceRepository.getAssetPrices(assetId);
	}
	
	public AssetPrice getTodayAssetPrice(Long assetId) {
		return findPriceByDate(assetId, LocalDate.now());
	}
	
	public AssetPrice getAssetPriceNullEndDate(Long assetId) {
		List<AssetPrice> prices = getAssetPrices(assetId);
		AssetPrice price = prices.stream()
				.filter(p -> p.getEndDate() == null)
				.findFirst().get();
		
		return price;
	}
	
	public AssetPrice findPriceByDate(Long assetId, LocalDate date) {
		List<AssetPrice> prices = getAssetPrices(assetId);
		
		for (AssetPrice price : prices) {
			if ((price.getStartDate().isEqual(date) || price.getStartDate().isBefore(date))
					&& (price.getEndDate() == null || price.getEndDate().isEqual(date) || price.getEndDate().isAfter(date))) {
				return price;
			}
		}
		
		return null;
	}
	
	public boolean doesPriceWithStartDateExist(Long assetId, LocalDate date) {
		List<AssetPrice> prices = getAssetPrices(assetId);
		
		for (AssetPrice price : prices) {
			if (price.getStartDate().equals(date))
				return true;
		}
		
		return false;
	}
}
