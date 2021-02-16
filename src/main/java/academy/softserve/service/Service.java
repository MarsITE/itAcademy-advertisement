package academy.softserve.service;

import java.util.List;

public interface Service <E> {
    E save(E e);

    E update(E e);

    boolean delete(long id);

    E findById(long id);

    List<E> findAll();
}
