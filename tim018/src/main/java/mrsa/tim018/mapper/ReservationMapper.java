package mrsa.tim018.mapper;

import java.util.ArrayList;
import java.util.List;

import mrsa.tim018.dto.ReservationDTO;
import mrsa.tim018.model.Reservation;

public class ReservationMapper {
	
	public static List<ReservationDTO> map(List<Reservation> reservations){
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		for (Reservation s : reservations) {
			reservationsDTO.add(new ReservationDTO(s));
		}
		return reservationsDTO;
	}
}
