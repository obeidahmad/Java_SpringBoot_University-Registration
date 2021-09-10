package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.DTO.ClassDTO;
import ai.inmind.universityregistration.service.impl.ClassServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    private final ClassServiceImpl classService;

    public ClassController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<Class> saveClass(@RequestBody Class classroom) {
        return new ResponseEntity<>(classService.saveElement(classroom), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClassDTO> getAllClass() {
        return classService.getAllElements().stream().map(ClassDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable("id") long id) {
        return new ResponseEntity<>(new ClassDTO(classService.getElementById(id)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable("id") long id, @RequestBody Class classroom) {
        return new ResponseEntity<>(new ClassDTO(classService.updateElement(id, classroom)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClass(@PathVariable("id") long id) {
        classService.deleteElement(id);
        return new ResponseEntity<>("Class Deleted Successfully.", HttpStatus.OK);
    }
}
