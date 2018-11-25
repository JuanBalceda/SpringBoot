package com.balceda.teachers.controller;

import com.balceda.teachers.model.Course;
import com.balceda.teachers.service.exception.ServiceException;
import com.balceda.teachers.service.interfaces.CourseService;
import com.balceda.teachers.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET
    @RequestMapping(value = "/courses", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(value = "name", required = false) String name) throws ServiceException {

        List<Course> courses = new ArrayList<>();
        if (name == null) {
            courses = courseService.findAll();
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            Course course = courseService.findByName(name);
            if (course == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            courses.add(course);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
    }

    // GET BY ID
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Course> getSocialMediaById(@PathVariable("id") Long idCourse) {

        Course course = new Course();
        if (idCourse == null || idCourse <= 0) {
            return new ResponseEntity(new CustomErrorType("Invalid course id"), HttpStatus.CONFLICT);
        }

        try {
            course = courseService.findById(idCourse);
            if (course == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // POST
    @RequestMapping(value = "/courses", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> createSocialMedia(@RequestBody Course course,
                                               UriComponentsBuilder uriComponentsBuilder) throws ServiceException {

        if (course.getName().equals(null) || course.getName().isEmpty()) {
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
        } else if (courseService.findByName(course.getName()) != null) {
            return new ResponseEntity<List<Course>>(HttpStatus.CONFLICT);
        }
        courseService.save(course);

        Course c = courseService.findByName(course.getName());

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    // UPDATE
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
    public ResponseEntity<?> updateSocialMedia(@PathVariable("id") Long idCourse,
                                               @RequestBody Course course) {
        Course currentCourse = new Course();

        if (idCourse == null || idCourse <= 0) {
            return new ResponseEntity<>(currentCourse, HttpStatus.CONFLICT);
        }

        try {
            currentCourse = courseService.findById(idCourse);
            if (currentCourse == null) {
                return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
            }
            currentCourse.setName(course.getName());
            currentCourse.setThemes(course.getThemes());
            currentCourse.setProject(course.getProject());

            courseService.update(currentCourse);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(currentCourse, HttpStatus.OK);
    }

	// DELETE
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteSocialMedia(@PathVariable("id") Long idCourse) {
        Course currentCourse = new Course();

		if (idCourse == null || idCourse <= 0) {
			return new ResponseEntity<>(currentCourse, HttpStatus.CONFLICT);
		}

		try {
			currentCourse = courseService.findById(idCourse);
			if (currentCourse == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			courseService.deleteById(idCourse);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
