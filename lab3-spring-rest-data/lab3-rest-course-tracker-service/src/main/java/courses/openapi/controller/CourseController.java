package courses.openapi.controller;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import courses.openapi.service.CourseService;
import jakarta.validation.Valid;
import courses.openapi.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/courses/")
@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
public class CourseController {

	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all courses available in the Course Tracker application")
	public Iterable<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course id from the Course Tracker application")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") long courseId) {
		Optional<Course> courseOptional =  courseService.getCourseById(courseId);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			course.add(
					linkTo(methodOn(CourseController.class).getCourseById(courseId)).withSelfRel(),
					linkTo(methodOn(CourseController.class).getCourseByCategory(course.getCategory())).withRel("get courses by category " + course.getCategory()),
					linkTo(methodOn(CourseController.class).createCourse(course)).withRel("create course with course id " + courseId),
					linkTo(methodOn(CourseController.class).updateCourse(courseId, course)).withRel("update course with course id " + courseId),
					linkTo(methodOn(CourseController.class).deleteCourseById(courseId)).withRel("delete course with course id " + courseId),
					linkTo(methodOn(CourseController.class).deleteCourses()).withRel("delete all courses")
			);
			return ResponseEntity.ok(course);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("category/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course category from the Course Tracker application")
	public ResponseEntity<Iterable<Course>> getCourseByCategory(@PathVariable("name") String category) {
		Iterable<Course> courses = courseService.getCoursesByCategory(category);

		for (Course course : courses) {
			course.add(
					linkTo(methodOn(CourseController.class).getCourseByCategory(course.getCategory())).withSelfRel(),
					linkTo(methodOn(CourseController.class).getCourseById(course.getId())).withRel("get course by course id " + course.getId()),
					linkTo(methodOn(CourseController.class).createCourse(course)).withRel("create course with course id " + course.getId()),
					linkTo(methodOn(CourseController.class).updateCourse(course.getId(), course)).withRel("update course with course id " + course.getId()),
					linkTo(methodOn(CourseController.class).deleteCourseById(course.getId())).withRel("delete course with course id " + course.getId()),
					linkTo(methodOn(CourseController.class).deleteCourses()).withRel("delete all courses")
			);
		}

		return ResponseEntity.ok(courses);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new course in the Course Tracker application")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
		return ResponseEntity.ok(courseService.createCourse(course));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Updates the course details in the Course Tracker application for the supplied course id")
	public ResponseEntity<String> updateCourse(@PathVariable("id") long courseId, @Valid @RequestBody Course course) {
		courseService.updateCourse(courseId, course);
		return ResponseEntity.ok("Course with id " + courseId +  " updated!");
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes the course details for the supplied course id from the Course Tracker application")
	public ResponseEntity<String> deleteCourseById(@PathVariable("id") long courseId) {
		courseService.deleteCourseById(courseId);
		return ResponseEntity.ok("Course with id " + courseId +  " deleted!");
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes all courses from the Course Tracker application")
	public ResponseEntity<String> deleteCourses() {
		courseService.deleteCourses();
		return ResponseEntity.ok("All courses deleted!");
	}

}
