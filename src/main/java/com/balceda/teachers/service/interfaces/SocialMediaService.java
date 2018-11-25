package com.balceda.teachers.service.interfaces;

import com.balceda.teachers.model.SocialMedia;
import com.balceda.teachers.model.TeacherSocialMedia;
import com.balceda.teachers.service.exception.ServiceException;

public interface SocialMediaService extends Service<SocialMedia> {
	
	TeacherSocialMedia findSocialMediaByIdAndName(long id, String name) throws ServiceException;
	
}
