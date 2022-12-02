package springboot.jpa.form;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookForm extends ItemForm {

    private String author;
    private String isbn;
}
