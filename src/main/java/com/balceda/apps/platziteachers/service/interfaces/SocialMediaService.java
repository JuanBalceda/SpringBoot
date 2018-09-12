package com.balceda.apps.platziteachers.service.interfaces;

import com.balceda.apps.platziteachers.model.SocialMedia;
import com.balceda.apps.platziteachers.model.TeacherSocialMedia;
import com.balceda.apps.platziteachers.service.exception.ServiceException;

public interface SocialMediaService extends Service<SocialMedia> {
	
	TeacherSocialMedia findSocialMediaByIdAndName(long id, String name) throws ServiceException;
	
}
