package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.FinancesAdminDTO;
import mrsa.tim018.dto.LoyaltyCategoryInfoDTO;
import mrsa.tim018.dto.LoyaltyElementDTO;
import mrsa.tim018.model.LoyaltyProgram;
import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.model.UserDiscountType;
import mrsa.tim018.service.LoyaltyProgramService;
import mrsa.tim018.service.ReservationFinancesService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/loyaltyProgram")
public class LoyaltyProgramController {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@Autowired 
	private ReservationFinancesService reservationFinancesService;
	
	@GetMapping(value = "/all")
	public List<LoyaltyElementDTO> getLoyaltyClientData() {
		List<LoyaltyElementDTO> list = new ArrayList<>();
		for (LoyaltyProgram loyaltyProgram : loyaltyProgramService.getAll()) {
			if (!loyaltyProgram.isDeleted())
				list.add(new LoyaltyElementDTO(loyaltyProgram));
		}
		return list;
	}
	
	@PutMapping(value = "/update")
	public FinancesAdminDTO updateFinancesData(@RequestBody FinancesAdminDTO data) {
		List<LoyaltyElementDTO> requestLoyaltyProgram = data.getLoyaltyProgram();
		List<LoyaltyProgram> loyaltyProgram = new ArrayList<>();
		for (LoyaltyElementDTO loyaltyElementDTO : requestLoyaltyProgram) {
			List<LoyaltyProgram> loyaltyElements = loyaltyProgramService.findBylevel(loyaltyElementDTO.getLevel(), loyaltyElementDTO.getUserDiscountType());
			if (loyaltyElements.size()==1) {			//for already existing elements
				LoyaltyProgram loyaltyElement = loyaltyElements.get(0);
				loyaltyElement.setDiscount(loyaltyElementDTO.getDiscount());
				loyaltyElement.setLevel(loyaltyElementDTO.getLevel());
				loyaltyElement.setPoints(loyaltyElementDTO.getPoints());
				loyaltyProgram.add(loyaltyElement);
			} else if (loyaltyElements.isEmpty()) {		//for new elements
				LoyaltyProgram newElem = new LoyaltyProgram();
				newElem.setDiscount(loyaltyElementDTO.getDiscount());
				newElem.setLevel(loyaltyElementDTO.getLevel());
				newElem.setPoints(loyaltyElementDTO.getPoints());
				newElem.setUserDiscountType(loyaltyElementDTO.getUserDiscountType());
				loyaltyProgram.add(newElem);
			}
		}
		loyaltyProgramService.saveAll(loyaltyProgram);
		
		//for deleted rows
		for (LoyaltyProgram loyaltyProgram2 : loyaltyProgramService.getDeleted(requestLoyaltyProgram)) {
			loyaltyProgram2.setDeleted(true);
			loyaltyProgramService.save(loyaltyProgram2); 
		}
		loyaltyProgramService.saveAll(loyaltyProgram); 
		 
		 
		try {
			ReservationFinances reservationFinances = reservationFinancesService.findAll().get(0);
			reservationFinances.setPointsPerReservation(data.getReservationFinancesDTO().getPointsPerReservation());
			reservationFinances.setReservationTax(data.getReservationFinancesDTO().getReservationTax());
			reservationFinancesService.save(reservationFinances);
		} catch (Exception e) {
			return null;
		}
		return data;
	}
	

	@GetMapping(value = "/getCategory/{loyaltyPoints}/{userType}")
	public LoyaltyCategoryInfoDTO getCategory(@PathVariable int loyaltyPoints, @PathVariable UserDiscountType userType) {
		List<LoyaltyProgram> loyaltyPrograms = loyaltyProgramService.findByUserDiscountType(userType);
		loyaltyPrograms.sort(Comparator.comparing(LoyaltyProgram::getPoints));
		
		LoyaltyCategoryInfoDTO dto = new LoyaltyCategoryInfoDTO();
		// od 0 do 10 => regular
		if(loyaltyPoints < loyaltyPrograms.get(0).getPoints()) {
			dto.setCategory("Regular");
			dto.setPointsToUpgrade(loyaltyPrograms.get(0).getPoints() - loyaltyPoints);
			return dto;
		}
		// od 10 do 20 => bronze
		else if(loyaltyPoints < loyaltyPrograms.get(1).getPoints()) {
			dto.setCategory(loyaltyPrograms.get(0).getLevel());
			dto.setPointsToUpgrade(loyaltyPrograms.get(1).getPoints() - loyaltyPoints);
			return dto;
		}
		// od 20 do 40 => silver
		else if(loyaltyPoints < loyaltyPrograms.get(2).getPoints()) {
			dto.setCategory(loyaltyPrograms.get(1).getLevel());
			dto.setPointsToUpgrade(loyaltyPrograms.get(2).getPoints() - loyaltyPoints);
			return dto;
		}
		// >40 => gold
		else {
			dto.setCategory(loyaltyPrograms.get(2).getLevel());
			dto.setPointsToUpgrade(0);
			return dto;
		}
	}
}
