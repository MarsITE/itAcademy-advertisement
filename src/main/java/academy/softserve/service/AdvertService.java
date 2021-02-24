package academy.softserve.service;

import academy.softserve.model.Advert;

import java.util.List;

public interface AdvertService {
    Advert save(Advert advert);

    Advert update(Advert advert);

    boolean delete(long id);

    Advert findById(long id);

    List<Advert> findAll();

}
