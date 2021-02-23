package academy.softserve.service;

import academy.softserve.model.User;
import academy.softserve.repository.UserRepository;
import academy.softserve.repository.UserRepositoryImpl;

import javax.validation.ValidationException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository repository= new UserRepositoryImpl();
    private final ValidatorServiceImpl<User> validatorService = new ValidatorServiceImpl<>();

    @Override
    public User save(User user) throws ValidationException {
        if (validatorService.validate(user).isEmpty() || validatorService.isEmailUnique(user.getEmail()) == null) {
            return repository.save(user);
        } else {return user;}
    }

    @Override
    public User update(User user) throws ValidationException {
        if (validatorService.validate(user).isEmpty() || validatorService.isEmailUnique(user.getEmail()) == null) {
            return repository.update(user);
        } else {return user;}
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
