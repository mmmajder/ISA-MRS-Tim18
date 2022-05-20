package mrsa.tim018.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mrsa.tim018.model.Asset;
import mrsa.tim018.model.Image;
import mrsa.tim018.model.User;
import mrsa.tim018.service.AssetService;
import mrsa.tim018.service.ImageService;
import mrsa.tim018.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/photos")
public class ImageController {
	
	// https://www.bezkoder.com/spring-boot-upload-file-database/
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AssetService assetService;
	
	@PostMapping("profilePhoto/{id}")
	  public ResponseEntity<String> uploadProfilePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	    try {
	    	Image image = imageService.store(file);
	    	User user = userService.findOne(id);
	    	user.setProfilePhotoId(image.getId());
	    	userService.updateUser(user);
	    	return new ResponseEntity<>(image.getId(), HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	    }
	  }
	  
	  @PostMapping("assetPhoto/{id}")
	  public ResponseEntity<String> uploadAssetPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	    try {
	    	Asset a = assetService.findOne(id);
	    	
	    	if (a != null) {
	    		Image image = imageService.store(id, file);
		    	return new ResponseEntity<>(image.getId(), HttpStatus.OK);
	    	} else {
	    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	    	}
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	    }
	  }
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<byte[]> deletePhoto(@PathVariable String id) {
	    try {
	    	Optional<Image> i = imageService.getImage(id);
	    	
	    	if (i.isPresent()) {
	    		Image image = i.get();
	    		image.setDeleted(true);
	    		imageService.save(image);
		    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    	} else {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	    }
	  }
	
	  @GetMapping("/{id}")
	  public ResponseEntity<byte[]> getPhoto(@PathVariable String id) {
	    Optional<Image> image = imageService.getImage(id);
	    if (image.isPresent()) {
	    	byte[] encoded = Base64.getEncoder().encode(image.get().getData());
	    	return new ResponseEntity<>(encoded, HttpStatus.OK);
	    }
	    else
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	  
	  @GetMapping("assetPhotoIds/{id}")
	  public ResponseEntity<List<String>> getAssetPhotoIds(@PathVariable Long id) {
	    Asset asset = assetService.findById(id);
	    if (asset != null) {
	    	List<String> imageIds = imageService.getAssetImageIds(id);
	    	return new ResponseEntity<>(imageIds, HttpStatus.OK);
	    }
	    else
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
}
