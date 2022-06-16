package mrsa.tim018.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	public List<SpecialOffer> findAllByAssetId(long id);
	public Optional<SpecialOffer> findById(long id);

}
