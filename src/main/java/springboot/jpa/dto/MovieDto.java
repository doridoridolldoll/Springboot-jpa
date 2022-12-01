package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto extends ItemDto {

    private String director;
    private String actor;
}
