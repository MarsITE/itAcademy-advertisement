package academy.softserve.service;

import academy.softserve.repository.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.*;
import java.util.HashSet;
import java.util.Set;

public class ValidatorServiceImpl<E> {
    private static final Logger logger = LogManager.getLogger(ValidatorServiceImpl.class);
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public Set<ValidationException> validate(E e) {
        Set<ConstraintViolation<E>> violations = validator.validate(e);
        Set<ValidationException> exceptions = new HashSet<>();
        for (ConstraintViolation<E> violation : violations) {
            exceptions.add(new ValidationException(String.format("Value invalid: %s", violation.getInvalidValue())));
        }
        return exceptions;
    }


    public ValidationException isEmailUnique(String email) {
        UserRepositoryImpl repository = new UserRepositoryImpl();
        Set<String> emails = new HashSet<>(repository.findAllEmails());
        int countOfEmails = emails.size();
        emails.add(email);
        if (countOfEmails == emails.size()) {
            return new ValidationException("This email already exists!");
        }
        return null;
    }
}
