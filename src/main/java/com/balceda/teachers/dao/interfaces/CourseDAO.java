package com.balceda.teachers.dao.interfaces;

import java.util.List;

import com.balceda.teachers.dao.exception.DAOException;
import com.balceda.teachers.model.Course;

public interface CourseDAO extends DAO<Course> {
	
	List<Course> findByIdTeacher(long id) throws DAOException;

}
