package com.balceda.teachers.dao.interfaces;

import com.balceda.teachers.dao.exception.DAOException;
import com.balceda.teachers.model.SocialMedia;
import com.balceda.teachers.model.TeacherSocialMedia;

public interface SocialMediaDAO extends DAO<SocialMedia> {
	
	TeacherSocialMedia findSocialMediaByIdAndName(long id, String name) throws DAOException;
	
}
