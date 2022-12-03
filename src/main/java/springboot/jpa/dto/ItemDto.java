package springboot.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

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
