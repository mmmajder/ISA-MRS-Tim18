package mrsa.tim018.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	public Page<Subscription> findAll(Pageable pageable);

	public Optional<Subscription> findOneByAssetIdAndClientId(long id1, long id2);

	public Subscription findById(long id);
}