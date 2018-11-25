package com.balceda.teachers.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.balceda.teachers.dao.exception.DAOException;
import com.balceda.teachers.dao.interfaces.SocialMediaDAO;
import com.balceda.teachers.model.TeacherSocialMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balceda.teachers.model.SocialMedia;
import com.balceda.teachers.service.exception.ServiceException;
import com.balceda.teachers.service.interfaces.SocialMediaService;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDAO socialMediaDAO;
	
	public SocialMediaServiceImpl() {
	}

	@Override
	public void save(SocialMedia t) throws ServiceException {
		try {
			socialMediaDAO.save(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<SocialMedia> findAll() throws ServiceException {
		try {
			return socialMediaDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void deleteById(Long id) throws ServiceException {
		try {
			socialMediaDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void update(SocialMedia t) throws ServiceException {
		try {
			socialMediaDAO.update(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public SocialMedia findById(long id) throws ServiceException {
		try {
			return socialMediaDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public SocialMedia findByName(String name) throws ServiceException {
		try {
			return socialMediaDAO.findByName(name);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(long id, String name) throws ServiceException {
		try {
			return socialMediaDAO.findSocialMediaByIdAndName(id, name);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

}
