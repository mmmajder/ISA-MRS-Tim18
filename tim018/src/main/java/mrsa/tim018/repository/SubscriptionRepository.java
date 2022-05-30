package mrsa.tim018.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Client;
import mrsa.tim018.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	public Page<Subscription> findAll(Pageable pageable);

	public Optional<Subscription> findOneByAssetIdAndClientId(long id1, long id2);

	public Subscription findById(long id);
	
	@Query("SELECT s FROM Subscription AS s LEFT JOIN FETCH s.asset LEFT JOIN FETCH s.client WHERE s.isDeleted = false and s.client.id=(:clientId)")
	public  List<Subscription> findClientsActiveSubscriptions(@Param("clientId") long clientId);
	
//	@Query("SELECT u FROM Asset AS u LEFT JOIN FETCH u.subscriptions WHERE u.id=(:id)")
//	public Asset joinFetchAssetWithSubsById(@Param("id") long id);
	
	@Query("SELECT s FROM Subscription AS s LEFT JOIN FETCH s.asset LEFT JOIN FETCH s.client  WHERE s.isDeleted = false and s.asset.id=(:assetId)")
	public List<Subscription> findAssetsActiveSubscriptions(@Param("assetId") long assetId);
}