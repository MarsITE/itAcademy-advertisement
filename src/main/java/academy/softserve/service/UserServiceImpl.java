package academy.softserve.service;

import academy.softserve.model.User;
import academy.softserve.repository.UserRepository;
import academy.softserve.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository repository= new UserRepositoryImpl();

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public boolean delete(long id) {
        return repository.delete(id);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

}
