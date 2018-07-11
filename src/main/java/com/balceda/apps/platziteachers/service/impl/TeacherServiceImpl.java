package com.balceda.apps.platziteachers.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balceda.apps.platziteachers.dao.exception.DAOException;
import com.balceda.apps.platziteachers.dao.interfaces.TeacherDAO;
import com.balceda.apps.platziteachers.model.Teacher;
import com.balceda.apps.platziteachers.service.exception.ServiceException;
import com.balceda.apps.platziteachers.service.interfaces.TeacherService;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDAO teacherDAO;
	
	public TeacherServiceImpl() {
	}

	@Override
	public void save(Teacher t) throws ServiceException {
		try {
			teacherDAO.save(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Teacher> findAll() throws ServiceException {
		try {
			return teacherDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void deleteById(Long id) throws ServiceException {
		try {
			teacherDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void update(Teacher t) throws ServiceException {
		try {
			teacherDAO.update(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Teacher findById(long id) throws ServiceException {
		try {
			return teacherDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Teacher findByName(String name) throws ServiceException {
		try {
			return teacherDAO.findByName(name);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

}
