package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BookDto extends ItemDto {

    private Long id;
    private String author;
    private String isbn;
}
