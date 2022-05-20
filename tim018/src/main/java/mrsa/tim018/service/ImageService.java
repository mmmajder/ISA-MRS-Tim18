package mrsa.tim018.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	
	public Image store(Long assetId, MultipartFile file) throws IOException{
		Image image = new Image(file.getBytes(), file.getContentType(), assetId);
		return imageRepository.save(image);
	}
	 
	public Optional<Image> getImage(String id) {
		return imageRepository.findById(id);
	}
	
	public Image save(Image image) {
		return imageRepository.save(image);
	}
	
	public List<String> getAssetImageIds(Long assetId){ 
		return imageRepository.getAssetImages(assetId)
				.stream().map(image -> image.getId())
				.collect(Collectors.toList());
	}
}
