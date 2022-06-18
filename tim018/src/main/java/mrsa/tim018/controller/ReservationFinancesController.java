package mrsa.tim018.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrsa.tim018.dto.ReservationFinancesDTO;
import mrsa.tim018.model.ReservationFinances;
import mrsa.tim018.service.ReservationFinancesService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/reservationFinances")
public class ReservationFinancesController {
	
	@Autowired
	private ReservationFinancesService reservationFinancesService;
	
	@GetMapping(value = "/all")
	public List<ReservationFinancesDTO> getLoyaltyClientData() {
		List<ReservationFinancesDTO> list = new ArrayList<>();
		for (ReservationFinances reservationFinances : reservationFinancesService.findAll()) {
			list.add(new ReservationFinancesDTO(reservationFinances));
		}
		return list;
	}

}
