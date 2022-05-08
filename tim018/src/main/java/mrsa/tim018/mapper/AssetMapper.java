package mrsa.tim018.mapper;

import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.dto.BoatDTO;
import mrsa.tim018.dto.ResortDTO;
import mrsa.tim018.model.Adventure;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Boat;
import mrsa.tim018.model.Resort;

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
		asset.setAssetType(assetDto.getAssetType());
		
		return asset;
	}
	
	public static Resort mapToResort(AssetDTO resortDto) {
		Resort resort = new Resort();
		resort.setDeleted(resortDto.isDeleted());
		resort.setName(resortDto.getName());
		resort.setRenter(resortDto.getRenter());
		resort.setAddress(resortDto.getAddress());
		resort.setDescription(resortDto.getDescription());
		resort.setRules(resortDto.getRules());
		resort.setNumOfPeople(resortDto.getNumOfPeople());
		resort.setCancelationConditions(resortDto.getCancelationConditions());
		resort.setAverageRating(resortDto.getAverageRating());
		resort.setNumberOfBeds(resortDto.getNumberOfBeds());
		resort.setNumberOfRooms(resortDto.getNumberOfRooms());
		resort.setAssetType(resortDto.getAssetType());
		
		return resort;
	}
	
	public static Boat mapToBoat(AssetDTO boatDto) {
		Boat boat = new Boat();
		boat.setDeleted(boatDto.isDeleted());
		boat.setName(boatDto.getName());
		boat.setRenter(boatDto.getRenter());
		boat.setAddress(boatDto.getAddress());
		boat.setDescription(boatDto.getDescription());
		boat.setRules(boatDto.getRules());
		boat.setNumOfPeople(boatDto.getNumOfPeople());
		boat.setCancelationConditions(boatDto.getCancelationConditions());
		boat.setAverageRating(boatDto.getAverageRating());
		boat.setBoatType(boatDto.getBoatType());
		boat.setLength(boatDto.getLength());
		boat.setNumOfMotor(boatDto.getNumOfMotor());
		boat.setMotorPower(boatDto.getMotorPower());
		boat.setMaxSpeed(boatDto.getMaxSpeed());
		boat.setNavigationEquipment(boatDto.getNavigationEquipment());
		boat.setFishingEquipment(boatDto.getFishingEquipment());
		boat.setAssetType(boatDto.getAssetType());
		

		return boat;
	}

	public static Adventure mapToAdventure(AssetDTO adventureDto) {
		Adventure adventure = new Adventure();
		adventure.setDeleted(adventureDto.isDeleted());
		adventure.setName(adventureDto.getName());
		adventure.setRenter(adventureDto.getRenter());
		adventure.setAddress(adventureDto.getAddress());
		adventure.setDescription(adventureDto.getDescription());
		adventure.setRules(adventureDto.getRules());
		adventure.setNumOfPeople(adventureDto.getNumOfPeople());
		adventure.setCancelationConditions(adventureDto.getCancelationConditions());
		adventure.setAverageRating(adventureDto.getAverageRating());
		adventure.setFishingEquipment(adventureDto.getFishingEquipment());
		adventure.setAssetType(adventureDto.getAssetType());

		return adventure;
	}
}
