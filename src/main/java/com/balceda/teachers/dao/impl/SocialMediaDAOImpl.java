package com.balceda.teachers.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.balceda.teachers.dao.AbstractSession;
import com.balceda.teachers.dao.exception.DAOException;
import com.balceda.teachers.dao.interfaces.SocialMediaDAO;
import com.balceda.teachers.model.TeacherSocialMedia;
import org.springframework.stereotype.Repository;

import com.balceda.teachers.model.SocialMedia;

@Repository
@Transactional
public class SocialMediaDAOImpl extends AbstractSession implements SocialMediaDAO {

	public SocialMediaDAOImpl() {
	}

	@Override
	public void save(SocialMedia t) throws DAOException {
		getSession().persist(t);
	}

	@Override
	public List<SocialMedia> findAll() throws DAOException {
		return (List<SocialMedia>) getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public void deleteById(Long id) throws DAOException {
		SocialMedia socialMedia = findById(id);
		if (socialMedia != null) {
			getSession().delete(socialMedia);
		}
	}

	@Override
	public void update(SocialMedia t) throws DAOException {
		getSession().update(t);
	}

	@Override
	public SocialMedia findById(long id) throws DAOException {
		return (SocialMedia) getSession().get(SocialMedia.class, id);
	}

	@Override
	public SocialMedia findByName(String name) throws DAOException {
		return (SocialMedia) getSession().createQuery("from SocialMedia where name = :name").setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdAndName(long id, String name) throws DAOException {
		List<Object[]> objects = getSession().createQuery("from TeacherSocialMedia tsm "
				+ "join tsm.socialMedia sm where sm.idSocialMedia = :id" + "and tsm.nickname = :nickname")
				.setParameter("id", id).setParameter("nickname", name).list();
		if (objects.size() > 0) {
			for (Object[] object : objects) {
				for (Object o : object) {
					if (o instanceof TeacherSocialMedia) {
						return (TeacherSocialMedia) o;
					}
				}
			}
		}
		return null;

	}

}
