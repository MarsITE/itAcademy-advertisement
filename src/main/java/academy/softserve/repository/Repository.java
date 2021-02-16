package academy.softserve.repository;

import java.util.List;

public interface Repository <E> {

    E save(E e);

    E update(E e);

    boolean delete(long id);

    E findById(long id);

    List<E> findAll();
}
