package com.balceda.teachers.service.interfaces;

import java.util.List;

import com.balceda.teachers.service.exception.ServiceException;


public interface Service<T> {
	void save(T t) throws ServiceException;

	List<T> findAll() throws ServiceException;

	void deleteById(Long id) throws ServiceException;

	void update(T t) throws ServiceException;

	T findById(long id) throws ServiceException;

	T findByName(String name) throws ServiceException;
}
