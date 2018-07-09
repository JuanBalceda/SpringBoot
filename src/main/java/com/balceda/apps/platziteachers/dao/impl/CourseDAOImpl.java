package com.balceda.apps.platziteachers.dao.impl;

import java.util.List;

import com.balceda.apps.platziteachers.dao.AbstractSession;
import com.balceda.apps.platziteachers.dao.exception.DAOException;
import com.balceda.apps.platziteachers.dao.interfaces.CourseDAO;
import com.balceda.apps.platziteachers.model.Course;

public class CourseDAOImpl extends AbstractSession implements CourseDAO {

	public CourseDAOImpl() {
	}

	@Override
	public void save(Course t) throws DAOException {
		getSession().persist(t);
	}

	@Override
	public List<Course> findAll() throws DAOException {
		return (List<Course>) getSession().createQuery("from Course").list();
	}

	@Override
	public void deleteById(Long id) throws DAOException {
		Course course = findById(id);
		if (course != null) {
			getSession().delete(course);
		}
	}

	@Override
	public void update(Course t) throws DAOException {
		getSession().update(t);
	}

	@Override
	public Course findById(long id) throws DAOException {
		return (Course) getSession().get(Course.class, id);
	}

	@Override
	public Course findByName(String name) throws DAOException {
		return (Course) getSession().createQuery("from Course where name = :name")
				.setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public List<Course> findByIdTeacher(long id) throws DAOException {
		return (List<Course>) getSession().createQuery(
				"from Course c join c.teacher t where t.idTeacher = :id")
				.setParameter("id", id).list();
	}

}
