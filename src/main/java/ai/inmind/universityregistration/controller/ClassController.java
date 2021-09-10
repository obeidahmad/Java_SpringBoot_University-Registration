package ai.inmind.universityregistration.controller;

import ai.inmind.universityregistration.configuration.SwaggerConfig;
import ai.inmind.universityregistration.helper.LocaleParam;
import ai.inmind.universityregistration.helper.MessageBuilder;
import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.DTO.ClassDTO;
import ai.inmind.universityregistration.service.impl.ClassServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.Class_Tag})
@RestController
@RequestMapping("/api/classes")
public class ClassController {
    private final ClassServiceImpl classService;

    public ClassController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @ApiOperation(value = "Add a new Class")
    @PostMapping
    public ResponseEntity<Class> saveClass(@RequestBody Class classroom) {
        return new ResponseEntity<>(classService.saveElement(classroom), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Return all Classes")
    @GetMapping
    public List<ClassDTO> getAllClass() {
        return classService.getAllElements().stream().map(ClassDTO::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "Return a specific Class")
    @GetMapping("{id}")
    public ResponseEntity<ClassDTO> getClassById(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new ClassDTO(classService.getElementById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a specific Class information")
    @PutMapping("{id}")
    public ResponseEntity<ClassDTO> updateClass(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id, @RequestBody Class classroom) {
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(new ClassDTO(classService.updateElement(id, classroom)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific Class")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClass(@RequestHeader("Accept-Language") String locale, @PathVariable("id") long id) {
        classService.deleteElement(id);
        LocaleParam.setLocale(locale);
        return new ResponseEntity<>(MessageBuilder.messageBuilder("class", "deleteMessage"), HttpStatus.OK);
    }
}
