package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.jpa.entity.Order;
import springboot.jpa.entity.OrderSearch;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id =:id")
    Order findOne(@Param("id") Long id);

    @Query(value = "select o From Order o join o.member m " +
            "where o.status = :#{#orderSearch.orderStatus} " +
            "and m.name like concat('%',:#{#orderSearch.memberName},'%')")
    List<Order> findAllByString(@Param("orderSearch") OrderSearch orderSearch);

}
