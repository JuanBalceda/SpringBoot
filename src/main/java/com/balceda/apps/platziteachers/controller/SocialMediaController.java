package com.balceda.apps.platziteachers.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.balceda.apps.platziteachers.model.SocialMedia;
import com.balceda.apps.platziteachers.service.exception.ServiceException;
import com.balceda.apps.platziteachers.service.interfaces.SocialMediaService;
import com.balceda.apps.platziteachers.util.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class SocialMediaController {

	@Autowired
	private SocialMediaService socialMediaService;

	// GET
	@RequestMapping(value = "/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(
			@RequestParam(value = "name", required = false) String name) throws ServiceException {

		List<SocialMedia> socialMedias = new ArrayList<>();
		if (name == null) {
			socialMedias = socialMediaService.findAll();
			if (socialMedias.isEmpty()) {
				return new ResponseEntity<List<SocialMedia>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
		} else {
			SocialMedia socialMedia = socialMediaService.findByName(name);
			if (socialMedia == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			socialMedias.add(socialMedia);			
			return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
		}
	}

	// GET BY ID
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaById(@PathVariable("id") Long idSocialMedia) {

		SocialMedia socialMedia = new SocialMedia();
		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity(new CustomErrorType("Invalid ID"), HttpStatus.CONFLICT);
		}

		try {
			socialMedia = socialMediaService.findById(idSocialMedia);
			if (socialMedia == null) {
				return new ResponseEntity<SocialMedia>(HttpStatus.NO_CONTENT);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
	}

	// POST
	@RequestMapping(value = "/socialMedias", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createSocialMedia(@RequestBody SocialMedia socialMedia,
			UriComponentsBuilder uriComponentsBuilder) throws ServiceException {

		if (socialMedia.getName().equals(null) || socialMedia.getName().isEmpty()) {
			return new ResponseEntity<List<SocialMedia>>(HttpStatus.NO_CONTENT);
		}
		if (socialMediaService.findByName(socialMedia.getName()) != null) {
			return new ResponseEntity<List<SocialMedia>>(HttpStatus.NO_CONTENT);
		}

		socialMediaService.save(socialMedia);
		SocialMedia sm = socialMediaService.findByName(socialMedia.getName());
		HttpHeaders header = new HttpHeaders();
		header.setLocation(
				uriComponentsBuilder.path("/v1/socialMedias/{id}").buildAndExpand(sm.getIdSocialMedia()).toUri());

		return new ResponseEntity<String>(header, HttpStatus.CREATED);
	}

	// UPDATE
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> updateSocialMedia(@PathVariable("id") Long idSocialMedia,
			@RequestBody SocialMedia socialMedia) {
		SocialMedia currentSocialMedia = new SocialMedia();

		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity<SocialMedia>(currentSocialMedia, HttpStatus.OK);
		}

		try {
			currentSocialMedia = socialMediaService.findById(idSocialMedia);
			if (currentSocialMedia == null) {
				return new ResponseEntity<SocialMedia>(HttpStatus.NO_CONTENT);
			}
			currentSocialMedia.setName(socialMedia.getName());
			currentSocialMedia.setIcon(socialMedia.getIcon());

			socialMediaService.update(currentSocialMedia);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<SocialMedia>(currentSocialMedia, HttpStatus.OK);
	}

	// DELETE
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteSocialMedia(@PathVariable("id") Long idSocialMedia) {
		SocialMedia currentSocialMedia = new SocialMedia();

		if (idSocialMedia == null || idSocialMedia <= 0) {
			return new ResponseEntity<SocialMedia>(currentSocialMedia, HttpStatus.OK);
		}

		try {
			currentSocialMedia = socialMediaService.findById(idSocialMedia);
			if (currentSocialMedia == null) {
				return new ResponseEntity<SocialMedia>(HttpStatus.NO_CONTENT);
			}
			socialMediaService.deleteById(idSocialMedia);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<SocialMedia>(HttpStatus.OK);
	}
}
