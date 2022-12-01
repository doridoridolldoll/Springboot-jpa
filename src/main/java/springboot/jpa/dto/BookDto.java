package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto extends ItemDto {

    private String author;
    private String ISBN;
}
