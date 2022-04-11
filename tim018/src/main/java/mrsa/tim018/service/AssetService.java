package mrsa.tim018.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.repository.AssetRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;

	public Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
}
