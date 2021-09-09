package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.model.Instructor;
import ai.inmind.universityregistration.service.impl.InstructorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorServiceImpl instructorService;

    public InstructorController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.saveElement(instructor), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllElements();
    }

    @GetMapping("{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") long id) {
        return new ResponseEntity<>(instructorService.getElementById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") long id, @RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.updateElement(id, instructor), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") long id) {
        instructorService.deleteElement(id);
        return new ResponseEntity<>("Instructor Deleted Successfully.", HttpStatus.OK);
    }
}
