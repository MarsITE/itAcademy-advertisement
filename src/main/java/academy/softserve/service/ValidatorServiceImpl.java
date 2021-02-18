package academy.softserve.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorServiceImpl<E> implements ValidatorService<E> {
    private static final Logger logger = LogManager.getLogger(ValidatorServiceImpl.class);

    @Override
    public void validate(E obj){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(obj);
        for (ConstraintViolation<E> violation : violations) {
            logger.error(violation.getMessage());
        }
    }
}
