package springboot.jpa.dto;

import lombok.*;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private String name;
    private Address address;
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
