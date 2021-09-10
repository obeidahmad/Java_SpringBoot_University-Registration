package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.ResourceNotFoundException;
import ai.inmind.universityregistration.model.Instructor;
import ai.inmind.universityregistration.repository.InstructorRepository;
import ai.inmind.universityregistration.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements CRUDService<Instructor, Long> {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor saveElement(Instructor element) {
        return instructorRepository.save(element);
    }

    @Override
    public List<Instructor> getAllElements() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getElementById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("instructor", "id", id));
    }

    @Override
    public Instructor updateElement(Long id, Instructor element) {
        Instructor validInstructor = getElementById(id);
        validInstructor.setFirstName(element.getFirstName());
        validInstructor.setLastName(element.getLastName());
        instructorRepository.save(validInstructor);
        return validInstructor;
    }

    @Override
    public void deleteElement(Long id) {
        instructorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("instructor", "id", id));
        instructorRepository.deleteById(id);
    }
}
