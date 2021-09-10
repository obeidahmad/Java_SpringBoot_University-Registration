package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.ResourceNotFoundException;
import ai.inmind.universityregistration.model.Student;
import ai.inmind.universityregistration.repository.StudentRepository;
import ai.inmind.universityregistration.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements CRUDService<Student, Long> {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveElement(Student element) {
        return studentRepository.save(element);
    }

    @Override
    public List<Student> getAllElements() {
        return studentRepository.findAll();
    }

    @Override
    public Student getElementById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("student", "id", id));
    }

    @Override
    public Student updateElement(Long id, Student element) {
        Student validStudent = getElementById(id);
        validStudent.setFirstName(element.getFirstName());
        validStudent.setLastName(element.getLastName());
        validStudent.setEnrolledIn(element.getEnrolledIn());
        studentRepository.save(validStudent);
        return validStudent;
    }

    @Override
    public void deleteElement(Long id) {
        studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("student", "id", id));
        studentRepository.deleteById(id);
    }
}
