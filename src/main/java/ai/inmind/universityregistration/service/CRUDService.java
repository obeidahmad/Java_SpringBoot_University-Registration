package ai.inmind.universityregistration.service;

import java.util.List;

public interface CRUDService <T, ID>{
    T saveElement(T element);
    List<T> getAllElements();
    T getElementById(ID id);
    T updateElement(ID id, T element);
    void deleteElement(ID id);
}
