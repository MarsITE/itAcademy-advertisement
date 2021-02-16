package academy.softserve.repository;

import academy.softserve.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class UserRepositoryTest {
    UserRepository ur = new UserRepository();
    User user1;
    User user2;
    User user3;

/*    @BeforeSuite
    public void setUp() {
        user1 = new User(1,"Olga", "Prybyla", "123", LocalDate.of(1987, 12, 24),
                "olga@ukr.net", UserRole.USER, UserStatus.NEWCOMER);
        user2 = new User(2,"Andrii", "Prybyla", "123", LocalDate.of(1985, 8, 6),
                "mars@ukr.net", UserRole.USER, UserStatus.NEWCOMER);
        user3 = new User(3,"Maria", "Prybyla", "123", LocalDate.of(2013, 11, 8),
                "maria@ukr.net", UserRole.USER, UserStatus.NEWCOMER);
    }*/
/*
    @AfterTest
    public void afterTest() {
        ur.delete(1);
        ur.delete(2);
        ur.delete(3);
    }*/

    @DataProvider(name = "testData1")
    public Object[] getTestData1() {
        return new Object[] { user1, user2, user3 };
    }

    @DataProvider(name = "testData2")
    public Object[] getTestData3() {
        user1.setFirstName("Olia");
        user2.setFirstName("Andrew");
        user3.setFirstName("Masha");
        return new Object[] { user1, user2, user3 };
    }

    @Test(dataProvider = "testData1")
    public void testSave(User user) {
        assertEquals(user, ur.save(user));
    }
/*
    @Test(dataProvider = "testData1")
    public void testFindUserById(User user) {
        assertEquals(user, ur.findById(user.getId()));
    }*/

    @Test(dataProvider = "testData2")
    public void testUpdate(User user) {
        assertEquals(user, ur.update(user));
    }

    @Test(dataProvider = "testData1")
    public void testDelete(User user) {
        assertTrue(ur.delete(user.getId()));
    }

/*    @Test
    public void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        assertEquals(userList, ur.findAll());
    }*/
}