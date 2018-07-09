package com.balceda.apps.platziteachers.dao.interfaces;

import java.util.List;

import com.balceda.apps.platziteachers.dao.exception.DAOException;
import com.balceda.apps.platziteachers.model.Course;

public interface CourseDAO extends DAO<Course> {
	
	List<Course> findByIdTeacher(long id) throws DAOException;

}
