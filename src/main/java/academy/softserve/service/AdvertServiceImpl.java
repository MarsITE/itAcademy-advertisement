package academy.softserve.service;

import academy.softserve.model.Advert;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.repository.AdvertRepository;
import academy.softserve.repository.AdvertRepositoryImpl;

import java.util.List;

public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository advertRepository = new AdvertRepositoryImpl();

    @Override
    public Advert save(Advert advert) {
        return advertRepository.save(advert);
    }

    @Override
    public Advert update(Advert advert) {
        return advertRepository.update(advert);
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

    @Override
    public List<Advert> findByAuthorId(long id) {
        return advertRepository.findByAuthorId(id);
    }

    @Override
    public List<Advert> findByGenre(AdvertGenre genre) {
        return advertRepository.findByGenre(genre);
    }
}
