package mrsa.tim018.dto;

import java.util.List;

public class FinancesAdminDTO {
	private List<LoyaltyElementDTO> loyaltyProgram;
	private ReservationFinancesDTO reservationFinancesDTO;
	private List<LoyaltyElementDTO> deletedRows;

	public FinancesAdminDTO(List<LoyaltyElementDTO> loyaltyProgram, ReservationFinancesDTO reservationFinancesDTO,
			List<LoyaltyElementDTO> deletedRows) {
		super();
		this.loyaltyProgram = loyaltyProgram;
		this.reservationFinancesDTO = reservationFinancesDTO;
		this.deletedRows = deletedRows;
	}

	public FinancesAdminDTO() {
		super();
	}

	public List<LoyaltyElementDTO> getDeletedRows() {
		return deletedRows;
	}

	public void setDeletedRows(List<LoyaltyElementDTO> deletedRows) {
		this.deletedRows = deletedRows;
	}

	public List<LoyaltyElementDTO> getLoyaltyProgram() {
		return loyaltyProgram;
	}

	public void setLoyaltyProgram(List<LoyaltyElementDTO> loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}

	public ReservationFinancesDTO getReservationFinancesDTO() {
		return reservationFinancesDTO;
	}

	public void setReservationFinancesDTO(ReservationFinancesDTO reservationFinancesDTO) {
		this.reservationFinancesDTO = reservationFinancesDTO;
	}

}
