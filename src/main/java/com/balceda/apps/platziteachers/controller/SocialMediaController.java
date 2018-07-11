package com.balceda.apps.platziteachers.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.balceda.apps.platziteachers.model.SocialMedia;
import com.balceda.apps.platziteachers.service.exception.ServiceException;
import com.balceda.apps.platziteachers.service.interfaces.SocialMediaService;

@Controller
@RequestMapping("/v1")
public class SocialMediaController {

	@Autowired
	private SocialMediaService socialMediaService;

	// GET
	@RequestMapping(value = "/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias() {

		List<SocialMedia> socialMedias = new ArrayList<>();
		try {
			socialMedias = socialMediaService.findAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (socialMedias.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
	}

	// POST
	@RequestMapping(value = "/socialMedias", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createSocialMedia(@RequestBody SocialMedia socialMedia,
			UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
		if (socialMedia.getName().equals(null) || socialMedia.getName().isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		if (socialMediaService.findByName(socialMedia.getName()) != null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		socialMediaService.save(socialMedia);
		SocialMedia sm = socialMediaService.findByName(socialMedia.getName());
		HttpHeaders header = new HttpHeaders();
		header.setLocation(
				uriComponentsBuilder.path("/v1/socialMedias/{id}").buildAndExpand(sm.getIdSocialMedia()).toUri());
		
		return new ResponseEntity<String>(header, HttpStatus.CREATED);
	}
}
