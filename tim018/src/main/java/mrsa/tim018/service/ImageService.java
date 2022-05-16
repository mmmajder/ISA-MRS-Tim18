package mrsa.tim018.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mrsa.tim018.model.Image;
import mrsa.tim018.repository.ImageRepository;

@Service
@Transactional
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public Image store(MultipartFile file) throws IOException{
		Image image = new Image(file.getBytes(), file.getContentType());
		return imageRepository.save(image);
	}
	 
	public Optional<Image> getImage(String id) {
		return imageRepository.findById(id);
	}
}
