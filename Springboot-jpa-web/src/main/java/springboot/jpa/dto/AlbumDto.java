package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class AlbumDto extends ItemDto {

    private String artist;
    private String etc;
}
