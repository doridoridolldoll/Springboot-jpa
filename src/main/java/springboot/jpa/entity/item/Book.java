package springboot.jpa.entity.item;

import lombok.*;
import lombok.experimental.SuperBuilder;
import springboot.jpa.entity.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Item {

    private String author;
    private String ISBN;
}
