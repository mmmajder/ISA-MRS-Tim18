package mrsa.tim018.mapper;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.model.Asset;

public class AssetMapper {
	
	public static Asset mapToAsset(AssetDTO assetDto) {
		Asset asset = new Asset();
		asset.setDeleted(assetDto.isDeleted());
		asset.setName(assetDto.getName());
		asset.setRenter(assetDto.getRenter());
		asset.setAddress(assetDto.getAddress());
		asset.setDescription(assetDto.getDescription());
		asset.setRules(assetDto.getRules());
		asset.setNumOfPeople(assetDto.getNumOfPeople());
		asset.setCancelationConditions(assetDto.getCancelationConditions());
		asset.setAverageRating(assetDto.getAverageRating());
		
		return asset;
	}
}
