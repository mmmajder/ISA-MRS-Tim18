package mrsa.tim018.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.repository.AssetRepository;

@Service
public class AssetService {
	
	@Autowired
	private AssetRepository assetRepository;

	public Asset save(Asset asset) {
		return assetRepository.save(asset);
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
}
