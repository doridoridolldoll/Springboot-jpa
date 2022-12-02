package springboot.jpa.entity.item;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.jpa.repository.ItemRepository;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Item {

    private String author;
    private String isbn;

    public void updateBook(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
