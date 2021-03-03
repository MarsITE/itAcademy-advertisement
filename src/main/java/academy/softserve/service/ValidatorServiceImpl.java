package academy.softserve.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.*;
import java.util.Set;

public class ValidatorServiceImpl<E> {
    private final Logger logger = LogManager.getLogger(ValidatorServiceImpl.class);

    public boolean validate(E e) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(e);
        for (ConstraintViolation<E> violation : violations) {
            logger.error(violation.getMessage());
            return false;
        }
        return true;
    }
}
