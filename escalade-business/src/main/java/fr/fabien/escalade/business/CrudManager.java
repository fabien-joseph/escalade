package fr.fabien.escalade.business;

import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

public class CrudManager<T, C extends CrudRepository<T, Long>> {
    C repository;

    public CrudManager(C repository) {
        this.repository = repository;
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public void save(T object) {
        Method method = null;
        Date date = new Date(System.currentTimeMillis());
        try {
            method = object.getClass().getMethod("setDate", Date.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            assert method != null;
            method.invoke(object, date);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        repository.save(object);
    }
}
