package mrsa.tim018.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrsa.tim018.model.SpecialOffer;
import mrsa.tim018.repository.SpecialOfferRepository;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepository specialOfferRepository;

	public SpecialOffer findById(Long id) {
		return specialOfferRepository.findById(id).orElse(null);
	}
	
	public SpecialOffer findByIdAndLock(Long id) {
		return specialOfferRepository.findByIdAndLock(id).orElse(null);
	}

}
