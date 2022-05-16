package mrsa.tim018.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrsa.tim018.model.Image;

public interface ImageRepository  extends JpaRepository<Image, String> {

}
