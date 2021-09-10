package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.configuration.SwaggerConfig;
import ai.inmind.universityregistration.exception.CannotEnrollException;
import ai.inmind.universityregistration.model.DTO.StudentDTO;
import ai.inmind.universityregistration.model.Student;
import ai.inmind.universityregistration.service.impl.StudentEnrollmentService;
import ai.inmind.universityregistration.service.impl.StudentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.Student_Tag})
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

    @ApiOperation(value = "Add a new Student")
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveElement(student), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Return all Students")
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllElements().stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Return a specific Student")
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new StudentDTO(studentService.getElementById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a specific Student information")
    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return new ResponseEntity<>(new StudentDTO(studentService.updateElement(id, student)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific Student")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        studentService.deleteElement(id);
        return new ResponseEntity<>("Student Deleted Successfully.", HttpStatus.OK);
    }

    @ApiOperation(value = "Enroll a specific Student into a specific Class")
    @PutMapping("enroll")
    public ResponseEntity<StudentDTO> enrollStudent(@RequestParam(name = "studentId") long studentId, @RequestParam(name = "classId") long classId) {
        return new ResponseEntity<>(new StudentDTO(studentEnrollmentService.enrollInClass(studentId, classId)), HttpStatus.OK);
    }
}
