package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.exception.CannotEnrollException;
import ai.inmind.universityregistration.model.DTO.StudentDTO;
import ai.inmind.universityregistration.model.Student;
import ai.inmind.universityregistration.service.impl.StudentEnrollmentService;
import ai.inmind.universityregistration.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final StudentEnrollmentService studentEnrollmentService;

    public StudentController(StudentServiceImpl studentService, StudentEnrollmentService studentEnrollmentService) {
        this.studentService = studentService;
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @ExceptionHandler(value = CannotEnrollException.class)
    public ResponseEntity<String> handleCannotEnrollException(CannotEnrollException cannotEnrollException) {
        return new ResponseEntity<>(cannotEnrollException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveElement(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllElements().stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new StudentDTO(studentService.getElementById(id)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return new ResponseEntity<>(new StudentDTO(studentService.updateElement(id, student)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        studentService.deleteElement(id);
        return new ResponseEntity<>("Student Deleted Successfully.", HttpStatus.OK);
    }

    @PutMapping("enroll")
    public ResponseEntity<StudentDTO> enrollStudent(@RequestParam(name = "studentId") long studentId, @RequestParam(name = "classId") long classId) {
        return new ResponseEntity<>(new StudentDTO(studentEnrollmentService.enrollInClass(studentId, classId)), HttpStatus.OK);
    }
}
