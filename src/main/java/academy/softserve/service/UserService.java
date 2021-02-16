package academy.softserve.service;

import academy.softserve.model.User;
import academy.softserve.repository.Repository;
import academy.softserve.repository.UserRepository;

import java.util.List;

public class UserService implements Service<User> {

    private Repository<User> repository;

    public UserService() {
        this.repository = new UserRepository();
    }

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
}
