package academy.softserve.main;

import academy.softserve.model.*;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.repository.AdvertRepositoryImpl;
import academy.softserve.configuration.tables.TablesRepository;
import academy.softserve.repository.UserRepositoryImpl;
import academy.softserve.service.AdvertService;
import academy.softserve.service.AdvertServiceImpl;
import academy.softserve.service.UserService;
import academy.softserve.service.UserServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

    //    TablesRepository br = new TablesRepository();
    //     br.createTables();
   //      br.dropTables();

/*        User user1 = User.builder().id(1).firstName("Andrii").lastName("Prybyla").password("1234")
                .dateOfBirth(LocalDate.of(1985, 8, 6)).email("mars@ukr.net")
                .userRole(UserRole.USER).userStatus(UserStatus.NEWCOMER).build();

        User user2 = User.builder().id(2).firstName("Olga").lastName("Prybyla").password("1234")
                .dateOfBirth(LocalDate.of(1987, 12, 24)).email("olga@ukr.net")
                .userRole(UserRole.USER).userStatus(UserStatus.NEWCOMER).build();

        User user3 = User.builder().id(3).firstName("Maria").lastName("Prybyla").password("1234")
                .dateOfBirth(LocalDate.of(2013, 11, 8)).email("maria@ukr.net")
                .userRole(UserRole.USER).userStatus(UserStatus.NEWCOMER).build();*/


 //       UserRepositoryImpl ur = new UserRepositoryImpl();
/*        ur.save(user1);
        ur.save(user2);
        ur.save(user3);*/

/*        ur.update(user1);
        ur.update(user2);
        ur.update(user3);*/

        //   ur.delete(2);

        //     System.out.println(ur.findById(1));
        //    System.out.println(ur.findAll());

/*        Advert advert1 = Advert.builder().id(1).title("Advert1").description("Some description")
                .publishingDate(LocalDate.of(1990, 1, 1))
                .endingDate(LocalDate.of(2022, 1, 1))
                .advertGenre(AdvertGenre.LIFE).author(user1).build();
        Advert advert2 = Advert.builder().id(2).title("Advert2").description("Some description")
                .publishingDate(LocalDate.of(1995, 1, 1))
                .endingDate(LocalDate.of(2022, 1, 1))
                .advertGenre(AdvertGenre.LIFE).author(user2).build();
        Advert advert3 = Advert.builder().id(3).title("Advert3").description("Some description")
                .publishingDate(LocalDate.of(1999, 1, 1))
                .endingDate(LocalDate.of(2022, 1, 1))
                .advertGenre(AdvertGenre.LIFE).author(user3).build();*/

        AdvertRepositoryImpl ar = new AdvertRepositoryImpl();
/*        ar.save(advert1);
        ar.save(advert2);
        ar.save(advert3);*/
/*        ar.update(advert1);
        ar.update(advert2);
        ar.update(advert3);*/

        //   ar.delete(2);

        //     System.out.println(ar.findById(1));
        //     System.out.println(ar.findAll());

  //      UserService userService = new UserServiceImpl();
   //    System.out.println(userService.findByLogin("mars@ukr.net"));
 //       System.out.println(userService.findAll());

        System.out.println(ar.findByGenre(AdvertGenre.DESIGN));

    }
}

