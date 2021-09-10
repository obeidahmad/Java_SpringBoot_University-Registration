package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.configuration.SwaggerConfig;
import ai.inmind.universityregistration.model.DTO.InstructorDTO;
import ai.inmind.universityregistration.model.Instructor;
import ai.inmind.universityregistration.service.impl.InstructorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.Instructor_Tag})
@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorServiceImpl instructorService;

    public InstructorController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }

    @ApiOperation(value = "Add a new Instructor")
    @PostMapping
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.saveElement(instructor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Return all Instructors")
    @GetMapping
    public List<InstructorDTO> getAllInstructors() {
        return instructorService.getAllElements().stream().map(InstructorDTO::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Return a specific Instructor")
    @GetMapping("{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new InstructorDTO(instructorService.getElementById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a specific Instructor information")
    @PutMapping("{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable("id") long id, @RequestBody Instructor instructor) {
        return new ResponseEntity<>(new InstructorDTO(instructorService.updateElement(id, instructor)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific Instructor")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") long id) {
        instructorService.deleteElement(id);
        return new ResponseEntity<>("Instructor Deleted Successfully.", HttpStatus.OK);
    }
}
