package springboot.jpa.dto;

import lombok.*;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private String email;
    private String password;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
