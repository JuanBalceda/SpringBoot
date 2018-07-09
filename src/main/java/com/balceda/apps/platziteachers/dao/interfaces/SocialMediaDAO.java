package com.balceda.apps.platziteachers.dao.interfaces;

import com.balceda.apps.platziteachers.model.SocialMedia;
import com.balceda.apps.platziteachers.model.TeacherSocialMedia;

public interface SocialMediaDAO extends DAO<SocialMedia> {
	TeacherSocialMedia findSocialMediaByIdAndName(long id, String name);
}
