package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlbumDto extends ItemDto {

    private String artist;
    private String etc;
}
