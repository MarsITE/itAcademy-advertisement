package academy.softserve.service;

import academy.softserve.model.Advert;
import academy.softserve.repository.AdvertRepositoryImpl;
import academy.softserve.repository.AdvertRepository;

import javax.validation.ValidationException;
import java.util.List;

public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository advertRepository= new AdvertRepositoryImpl();
    private final ValidatorServiceImpl<Advert> validatorService = new ValidatorServiceImpl<>();

    @Override
    public Advert save(Advert advert) throws ValidationException {
        if (validatorService.validate(advert).isEmpty()) {
            return advertRepository.save(advert);
        } else {return advert;}
    }

    @Override
    public Advert update(Advert advert) throws ValidationException {
        if (validatorService.validate(advert).isEmpty()) {
            return advertRepository.update(advert);
        } else {return advert;}
    }

    @Override
    public boolean delete(long id) {
        return advertRepository.delete(id);
    }

    @Override
    public Advert findById(long id) {
        return advertRepository.findById(id);
    }

    @Override
    public List<Advert> findAll() {
        return advertRepository.findAll();
    }
}
