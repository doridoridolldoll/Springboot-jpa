package springboot.jpa.dto;

import lombok.*;
import springboot.jpa.entity.Delivery;
import springboot.jpa.entity.Member;
import springboot.jpa.entity.OrderItem;
import springboot.jpa.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {

    private Member member;
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();
    private Delivery delivery;
    private LocalDateTime orderDate;
    private OrderStatus status;


}
