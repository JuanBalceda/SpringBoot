package com.balceda.apps.platziteachers.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balceda.apps.platziteachers.dao.exception.DAOException;
import com.balceda.apps.platziteachers.dao.interfaces.CourseDAO;
import com.balceda.apps.platziteachers.model.Course;
import com.balceda.apps.platziteachers.service.exception.ServiceException;
import com.balceda.apps.platziteachers.service.interfaces.CourseService;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;

	public CourseServiceImpl() {
	}

	@Override
	public void save(Course t) throws ServiceException {
		try {
			courseDAO.save(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Course> findAll() throws ServiceException {
		try {
			return courseDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void deleteById(Long id) throws ServiceException {
		try {
			courseDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public void update(Course t) throws ServiceException {
		try {
			courseDAO.update(t);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Course findById(long id) throws ServiceException {
		try {
			return courseDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Course findByName(String name) throws ServiceException {
		try {
			return courseDAO.findByName(name);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Course> findByIdTeacher(long id) throws ServiceException {
		try {
			return courseDAO.findByIdTeacher(id);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}
}
