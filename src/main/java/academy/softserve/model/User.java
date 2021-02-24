package academy.softserve.model;

import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.service.ValidatorServiceImpl;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

public class User {

    private long id;

    @Size(min = 2, max = 15, message = "FirstName must be between 2 and 15 characters")
    private String firstName;

    @Size(min = 2, max = 15, message = "LastName must be between 2 and 15 characters")
    private String lastName;

    @Size(min = 4, max = 64, message = "Password must be between 4 and 64 characters")
    private String password;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Email(message = "Wrong email format")
    private String email;

    private UserRole userRole;

    private UserStatus userStatus;

    private List<Advert> adverts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User newUser;

        private List<Advert> newAdverts = new ArrayList<>();

        private UserBuilder() {
            newUser = new User();
        }

        public UserBuilder id(long id) {
            newUser.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public UserBuilder password(String password) {
            newUser.password = password;
            return this;
        }

        public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
            newUser.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder email(String email) {
            newUser.email = email;
            return this;
        }

        public UserBuilder userRole(UserRole userRole) {
            newUser.userRole = userRole;
            return this;
        }

        public UserBuilder userStatus(UserStatus userStatus) {
            newUser.userStatus = userStatus;
            return this;
        }

        public UserBuilder adverts(List<Advert> adverts) {
            newAdverts.addAll(adverts);
            return this;
        }

        public User build() {
            newUser.setAdverts(newAdverts);
            new ValidatorServiceImpl<User>().validate(newUser);
            return newUser;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(email, user.email) && userRole == user.userRole && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, email, userRole, userStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                ", adverts=" + adverts +
                '}';
    }
}
