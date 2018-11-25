package com.balceda.teachers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_social_media")
public class TeacherSocialMedia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_teacher_social_media")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTeacherSocialMedia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_teacher")
	private Teacher teacher;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_social_media")
	private SocialMedia socialMedia;

	@Column(name = "nickname")
	private String nickname;

	public TeacherSocialMedia() {
	}

	public TeacherSocialMedia(long idTeacherSocialMedia, Teacher teacher, SocialMedia socialMedia) {
		super();
		this.idTeacherSocialMedia = idTeacherSocialMedia;
		this.teacher = teacher;
		this.socialMedia = socialMedia;
	}

	public long getIdTeacherSocialMedia() {
		return idTeacherSocialMedia;
	}

	public void setIdTeacherSocialMedia(long idTeacherSocialMedia) {
		this.idTeacherSocialMedia = idTeacherSocialMedia;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
