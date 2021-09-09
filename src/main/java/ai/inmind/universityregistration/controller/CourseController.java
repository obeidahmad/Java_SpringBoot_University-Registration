package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.model.Course;
import ai.inmind.universityregistration.service.impl.CourseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveElement(course), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllElements();
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
        return new ResponseEntity<>(courseService.getElementById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
        return new ResponseEntity<>(courseService.updateElement(id, course), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") long id) {
        courseService.deleteElement(id);
        return new ResponseEntity<>("Course Deleted Successfully.", HttpStatus.OK);
    }
}
