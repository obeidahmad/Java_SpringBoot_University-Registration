package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.ResourceNotFoundException;
import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.repository.ClassRepository;
import ai.inmind.universityregistration.service.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements CRUDService<Class, Long> {
    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Class saveElement(Class element) {
        return classRepository.save(element);
    }

    @Override
    public List<Class> getAllElements() {
        return classRepository.findAll();
    }

    @Override
    public Class getElementById(Long id) {
        return classRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Class", "Id", id));
    }

    @Override
    public Class updateElement(Long id, Class element) {
        Class validClass = getElementById(id);
        validClass.setSemester(element.getSemester());
        validClass.setDay(element.getDay());
        validClass.setSession(element.getSession());
        validClass.setInstructor(element.getInstructor());
        classRepository.save(validClass);
        return validClass;
    }

    @Override
    public void deleteElement(Long id) {
        classRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Class", "Id", id));
        classRepository.deleteById(id);
    }
}
