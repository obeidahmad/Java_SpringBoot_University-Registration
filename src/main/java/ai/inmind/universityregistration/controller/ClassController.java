package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.service.impl.ClassServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Class> getAllClass() {
        return classService.getAllElements();
    }

    @GetMapping("{id}")
    public ResponseEntity<Class> getClassById(@PathVariable("id") long id) {
        return new ResponseEntity<>(classService.getElementById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Class> updateClass(@PathVariable("id") long id, @RequestBody Class classroom) {
        return new ResponseEntity<>(classService.updateElement(id, classroom), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClass(@PathVariable("id") long id) {
        classService.deleteElement(id);
        return new ResponseEntity<>("Class Deleted Successfully.", HttpStatus.OK);
    }
}
