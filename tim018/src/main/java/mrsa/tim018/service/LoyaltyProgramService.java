package mrsa.tim018.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.dto.LoyaltyElementDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.LoyaltyProgram;
import mrsa.tim018.model.LoyaltyState;
import mrsa.tim018.model.User;
import mrsa.tim018.model.UserDiscountType;
import mrsa.tim018.repository.LoyaltyProgramRepository;

@Service
public class LoyaltyProgramService {
	
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Autowired
	private ReservationFinancesService reservationFinancesService;
	
	public LoyaltyProgram findById(long id) {
		return loyaltyProgramRepository.findById(id);
	}
	
	public List<LoyaltyProgram> getAll() {
		return loyaltyProgramRepository.findAll();
	}
	
	public List<LoyaltyProgram> findBylevel(String level, UserDiscountType userDiscountType) {
		return loyaltyProgramRepository.findByLevelAndUserDiscountType(level, userDiscountType);
	}
	
	public List<LoyaltyProgram> findByUserDiscountType( UserDiscountType userDiscountType) {
		return loyaltyProgramRepository.findByUserDiscountType(userDiscountType);
	}
	
	public List<LoyaltyProgram> saveAll(List<LoyaltyProgram> loyaltyProgram) {
		return loyaltyProgramRepository.saveAll(loyaltyProgram);
	}
	
	public List<LoyaltyProgram> getDeleted(List<LoyaltyElementDTO> newLoyaltyPrograms) {
		List<LoyaltyProgram> deleted = new ArrayList<>();
		for (LoyaltyProgram prog1 : getAll()) {
			boolean found = false;
			for (LoyaltyElementDTO prog2 : newLoyaltyPrograms) {
				if (prog1.getLevel().equals(prog2.getLevel()) && (prog1.getUserDiscountType().equals(prog2.getUserDiscountType()))) {
					found = true;
					break;
				}
			}
			if (!found) {
				deleted.add(prog1);
			}
		}
		return deleted;
	}
	
	public double getLoyaltyPercent(User user, UserDiscountType discountType) {
		List<LoyaltyProgram> programs = loyaltyProgramRepository.findAll();
		double discount = 0;
		for (LoyaltyProgram loyaltyProgram : programs) {
			if (loyaltyProgram.getUserDiscountType().equals(discountType)) {
				discount = setPoints(loyaltyProgram, user, discount);
			}
		}
		return discount;
	}

	private double setPoints(LoyaltyProgram loyaltyProgram, User user, Double discount) {
		if (user.getLoyaltyPoints()>loyaltyProgram.getPoints() && loyaltyProgram.getDiscount()>discount) {
			discount = loyaltyProgram.getDiscount();
		}
		return discount;
	}

	public void save(LoyaltyProgram loyaltyProgram) {
		loyaltyProgramRepository.save(loyaltyProgram);
		
	}
	
	public LoyaltyState getLoyaltyState(Client client) {
		return new LoyaltyState(reservationFinancesService.getLast().getReservationTax(), 
				getLoyaltyPercent(client, UserDiscountType.Client), 
				getLoyaltyPercent(client, UserDiscountType.Renter));
	}
}
