package springboot.jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.jpa.entity.Order;
import springboot.jpa.entity.OrderItem;
import springboot.jpa.entity.OrderSearch;
import springboot.jpa.repository.OrderRepository;
import springboot.jpa.repository.OrderRepository2;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository2 orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().map(o -> o.getItem().getName());
        }
        return all;
    }
}
