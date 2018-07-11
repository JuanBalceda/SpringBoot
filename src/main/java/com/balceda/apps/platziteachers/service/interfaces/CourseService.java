package com.balceda.apps.platziteachers.service.interfaces;

import java.util.List;

import com.balceda.apps.platziteachers.model.Course;
import com.balceda.apps.platziteachers.service.exception.ServiceException;

public interface CourseService extends Service<Course> {
	List<Course> findByIdTeacher(long id) throws ServiceException;

}
