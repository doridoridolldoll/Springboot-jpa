package springboot.jpa.service;

import springboot.jpa.dto.OrderDto;
import springboot.jpa.entity.Order;
import springboot.jpa.entity.OrderSearch;

import java.util.List;

public interface OrderService {

    Long Order(Long memberId, Long itemId, int count);
    void cancelOrder(Long orderId);
    List<Order> findOrders(OrderSearch orderSearch);

    default OrderDto entityToDto(Order order) {
        OrderDto dto = OrderDto.builder()
                .orderItems(order.getOrderItems())
                .delivery(order.getDelivery())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .build();
        return dto;
    }

    default Order dtoToEntity(OrderDto dto) {
        Order entity = Order.builder()
                .member(dto.getMember())
                .orderItems(dto.getOrderItems())
                .delivery(dto.getDelivery())
                .orderDate(dto.getOrderDate())
                .status(dto.getStatus())
                .build();
        return entity;
    }

}
