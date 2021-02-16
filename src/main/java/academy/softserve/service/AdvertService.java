package academy.softserve.service;

import academy.softserve.model.Advert;
import academy.softserve.repository.AdvertRepository;
import academy.softserve.repository.Repository;

import java.util.List;

public class AdvertService implements Service<Advert> {

    private Repository<Advert> repository;


    public AdvertService() {
        this.repository = new AdvertRepository();
    }

    @Override
    public Advert save(Advert advert) {
        return repository.save(advert);
    }

    @Override
    public Advert update(Advert advert) {
        return repository.update(advert);
    }

    @Override
    public boolean delete(long id) {
        return repository.delete(id);
    }

    @Override
    public Advert findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Advert> findAll() {
        return repository.findAll();
    }
}
