package academy.softserve.model;

import academy.softserve.model.library.AdvertGenre;
import academy.softserve.service.ValidatorServiceImpl;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Advert {

    private long id;

    @Size(min = 2, max = 25, message = "Title must be between 2 and 25 characters")
    private String title;

    @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 characters")
    private String description;

    private LocalDate publishingDate;

    @FutureOrPresent
    private LocalDate endingDate;

    private AdvertGenre advertGenre;

    private User author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public AdvertGenre getAdvertGenre() {
        return advertGenre;
    }

    public void setAdvertGenre(AdvertGenre advertGenre) {
        this.advertGenre = advertGenre;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public static AdvertBuilder builder(){
        return new AdvertBuilder();
    }

    public static class AdvertBuilder {
        private Advert newAdvert;

        private AdvertBuilder() {
            newAdvert = new Advert();
        }

        public AdvertBuilder id(long id) {
            newAdvert.id = id;
            return this;
        }

        public AdvertBuilder title(String title) {
            newAdvert.title = title;
            return this;
        }

        public AdvertBuilder description(String description){
            newAdvert.description = description;
            return this;
        }

        public AdvertBuilder publishingDate(LocalDate publishingDate){
            newAdvert.publishingDate = publishingDate;
            return this;
        }

        public AdvertBuilder endingDate(LocalDate endingDate){
            newAdvert.endingDate = endingDate;
            return this;
        }

        public AdvertBuilder advertGenre(AdvertGenre advertGenre){
            newAdvert.advertGenre = advertGenre;
            return this;
        }

        public AdvertBuilder author(User author){
            newAdvert.author = author;
            return this;
        }

        public Advert build(){
            new ValidatorServiceImpl<Advert>().validate(newAdvert);
            return newAdvert;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return id == advert.id && Objects.equals(title, advert.title) && Objects.equals(description, advert.description) && Objects.equals(publishingDate, advert.publishingDate) && Objects.equals(endingDate, advert.endingDate) && advertGenre == advert.advertGenre && Objects.equals(author, advert.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, publishingDate, endingDate, advertGenre, author);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishingDate=" + publishingDate +
                ", endingDate=" + endingDate +
                ", advertGenre=" + advertGenre +
                ", author=" + author +
                '}';
    }
}
