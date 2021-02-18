package academy.softserve.service;

import academy.softserve.model.User;
import academy.softserve.repository.UserRepository;
import academy.softserve.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean delete(long id) {
        return userRepository.delete(id);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
