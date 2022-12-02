package springboot.jpa.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class ItemForm {

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    private int price;
    private int stockQuantity;
}
