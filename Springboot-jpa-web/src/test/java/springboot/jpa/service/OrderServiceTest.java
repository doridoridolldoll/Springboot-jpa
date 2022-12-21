package springboot.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.entity.Address;
import springboot.jpa.entity.Member;
import springboot.jpa.entity.Order;
import springboot.jpa.entity.OrderStatus;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;
import springboot.jpa.exception.NotEnoughStockException;
import springboot.jpa.repository.ItemRepository;
import springboot.jpa.repository.MemberRepository;
import springboot.jpa.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Test
    void 상품주문() throws Exception {

        Member member = createMember();

        Book book = createBook("부산 JPA", 1000, 10);

        int orderCount = 2;

        Long orderId = orderService.Order(member.getId(), book.getId(), orderCount);
        System.out.println("orderId = " + orderId);

        Order getOrder = orderRepository.findOne(orderId);
        System.out.println("getOrder = " + getOrder);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(1000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다");

    }

    @Test
    void 상품주문_재고수량초과() throws Exception {

        Member member = createMember();
        Item item = createBook("부산 JPA", 1000, 10);

        int orderCount = 11;

        try {
            orderService.Order(member.getId(), item.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            return;
        }

        fail("재고수량 부족 예외가 발생해야 한다.");
    }

    @Test
    void 주문취소() throws Exception {

        Member member = createMember();
        Item item = createBook("부산 JPA", 1000, 10);
        int orderCount = 2;
        Long orderId = orderService.Order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);
        System.out.println("getOrder = " + getOrder);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL이다.");
        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        itemRepository.save(book);
        return book;
    }

    private Member createMember() {
        Member member = Member.builder().build();
        member.setName("회원1");
        member.setAddress(new Address("부산", "남구", "48579"));
        memberRepository.save(member);
        return member;
    }

}