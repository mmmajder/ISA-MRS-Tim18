package mrsa.tim018.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.AssetCalendar;

public interface AssetCalendarRepository extends JpaRepository<AssetCalendar, Long> {
	public Page<AssetCalendar> findAll(Pageable pageable);
	
	public AssetCalendar findById(long id);
}