package academy.softserve.service;

import academy.softserve.model.Advert;
import academy.softserve.model.library.AdvertGenre;

import java.util.List;

public interface AdvertService {
    Advert save(Advert advert);

    Advert update(Advert advert);

    boolean delete(long id);

    Advert findById(long id);

    List<Advert> findAll();

    List<Advert> findByAuthorId(long id);

    List<Advert> findByGenre(AdvertGenre genre);
}
