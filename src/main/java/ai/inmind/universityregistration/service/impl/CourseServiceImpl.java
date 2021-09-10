package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.ResourceNotFoundException;
import ai.inmind.universityregistration.model.Course;
import ai.inmind.universityregistration.repository.CourseRepository;
import ai.inmind.universityregistration.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CRUDService<Course, Long> {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveElement(Course element) {
        return courseRepository.save(element);
    }

    @Override
    public List<Course> getAllElements() {
        return courseRepository.findAll();
    }

    @Override
    public Course getElementById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("course", "id", id));
    }

    @Override
    public Course updateElement(Long id, Course element) {
        Course validCourse = getElementById(id);
        validCourse.setName(element.getName());
        courseRepository.save(validCourse);
        return validCourse;
    }

    @Override
    public void deleteElement(Long id) {
        courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("course", "id", id));
        courseRepository.deleteById(id);
    }
}
