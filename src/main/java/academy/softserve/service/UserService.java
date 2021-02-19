package academy.softserve.service;

import academy.softserve.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User update(User user);

    boolean delete(long id);

    User findById(long id);

    List<User> findAll();

    User findByLogin(String login);
}
