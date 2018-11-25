package com.balceda.teachers.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.balceda.teachers.dao.exception.DAOException;
import com.balceda.teachers.dao.interfaces.TeacherDAO;
import com.balceda.teachers.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balceda.teachers.service.exception.ServiceException;
import com.balceda.teachers.service.interfaces.TeacherService;

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
