package springboot.jpa.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class ItemDto {

    @NotEmpty(message = "이름은 필수 입력 값 입니다.")
    private String name;
    private int price;
    private int stockQuantity;
}
