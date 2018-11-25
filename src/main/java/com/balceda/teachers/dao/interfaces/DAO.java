package com.balceda.teachers.dao.interfaces;

import java.util.List;

import com.balceda.teachers.dao.exception.DAOException;


public interface DAO<T> {

	void save(T t) throws DAOException;

	List<T> findAll() throws DAOException;

	void deleteById(Long id) throws DAOException;

	void update(T t) throws DAOException;

	T findById(long id) throws DAOException;

	T findByName(String name) throws DAOException;
}
