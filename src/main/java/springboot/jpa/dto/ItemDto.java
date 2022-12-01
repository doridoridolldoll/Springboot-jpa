package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class ItemDto {

    private Long id;
    private String name;
    private int price;
    private int stock;
}
