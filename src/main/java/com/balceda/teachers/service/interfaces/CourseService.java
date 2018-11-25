package com.balceda.teachers.service.interfaces;

import java.util.List;

import com.balceda.teachers.model.Course;
import com.balceda.teachers.service.exception.ServiceException;

public interface CourseService extends Service<Course> {
	
	List<Course> findByIdTeacher(long id) throws ServiceException;

}
