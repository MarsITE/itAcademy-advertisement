package academy.softserve.repository;

import academy.softserve.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User update(User user);

    boolean delete(long id);

    User findById(long id);

    List<User> findAll();

    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);
}
