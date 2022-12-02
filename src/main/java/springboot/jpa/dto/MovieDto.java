package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class MovieDto extends ItemDto {

    private String director;
    private String actor;
}
