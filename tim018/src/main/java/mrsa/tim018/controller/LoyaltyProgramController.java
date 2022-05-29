package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.FinancesAdminDTO;
import mrsa.tim018.dto.LoyaltyElementDTO;
import mrsa.tim018.dto.RegistrationDTO;
import mrsa.tim018.model.LoyaltyProgram;
import mrsa.tim018.model.Registration;
import mrsa.tim018.model.RequestStatus;
import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.service.LoyaltyProgramService;
import mrsa.tim018.service.ReservationFinancesService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/loyaltyProgram")
public class LoyaltyProgramController {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@Autowired ReservationFinancesService reservationFinancesService;
	
	@GetMapping(value = "/all")
	public List<LoyaltyElementDTO> getLoyaltyClientData() {
		List<LoyaltyElementDTO> list = new ArrayList<LoyaltyElementDTO>();
		for (LoyaltyProgram loyaltyProgram : loyaltyProgramService.getAll()) {
			if (!loyaltyProgram.isDeleted())
				list.add(new LoyaltyElementDTO(loyaltyProgram));
		}
		return list;
	}
	
	@PutMapping(value = "/update")
	public FinancesAdminDTO updateFinancesData(@RequestBody FinancesAdminDTO data) {
		List<LoyaltyElementDTO> requestLoyaltyProgram = data.getLoyaltyProgram();
		List<LoyaltyProgram> loyaltyProgram = new ArrayList<LoyaltyProgram>();
		for (LoyaltyElementDTO loyaltyElementDTO : requestLoyaltyProgram) {
			List<LoyaltyProgram> loyaltyElements = loyaltyProgramService.findBylevel(loyaltyElementDTO.getLevel(), loyaltyElementDTO.getUserDiscountType());
			if (loyaltyElements.size()==1) {			//for already existing elements
				LoyaltyProgram loyaltyElement = loyaltyElements.get(0);
				loyaltyElement.setDiscount(loyaltyElementDTO.getDiscount());
				loyaltyElement.setLevel(loyaltyElementDTO.getLevel());
				loyaltyElement.setPoints(loyaltyElementDTO.getPoints());
				loyaltyProgram.add(loyaltyElement);
			} else if (loyaltyElements.size()==0) {		//for new elements
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
			// TODO: handle exception
		}
		return data;
	}
}
