package mrsa.tim018.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mrsa.tim018.model.Image;
import mrsa.tim018.model.User;
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
	
	@PostMapping("profilePhoto/{id}")
	  public ResponseEntity<byte[]> uploadProfilePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	    try {
	    	Image image = imageService.store(file);
	    	User user = userService.findOne(id);
	    	user.setProfilePhotoId(image.getId());
	    	userService.updateUser(user);
	    	return new ResponseEntity<>(image.getData(), HttpStatus.OK);
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
}
