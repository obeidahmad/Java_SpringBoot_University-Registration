package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.configuration.SwaggerConfig;
import ai.inmind.universityregistration.exception.CannotDropException;
import ai.inmind.universityregistration.exception.CannotEnrollException;
import ai.inmind.universityregistration.helper.LocaleParam;
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

    @ExceptionHandler({CannotEnrollException.class, CannotDropException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<StudentDTO> getStudentById(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new StudentDTO(studentService.getElementById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a specific Student information")
    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id, @RequestBody Student student) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new StudentDTO(studentService.updateElement(id, student)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific Student")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id) {
        LocaleParam.setLocale(locale);
        studentService.deleteElement(id);
        return new ResponseEntity<>(LocaleParam.getMessage("student") + " " + LocaleParam.getMessage("deleteMessage"), HttpStatus.OK);
    }

    @ApiOperation(value = "Enroll a specific Student into a specific Class")
    @PutMapping("enroll")
    public ResponseEntity<StudentDTO> enrollStudent(@RequestHeader("Accept-Language") String locale, @RequestParam(name = "studentId") long studentId, @RequestParam(name = "classId") long classId) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new StudentDTO(studentEnrollmentService.enrollInClass(studentId, classId)), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove a specific Student from a specific Class")
    @PutMapping("drop")
    public ResponseEntity<StudentDTO> dropStudent(@RequestHeader("Accept-Language") String locale, @RequestParam(name = "studentId") long studentId, @RequestParam(name = "classId") long classId) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new StudentDTO(studentEnrollmentService.dropClass(studentId, classId)), HttpStatus.OK);
    }
}
