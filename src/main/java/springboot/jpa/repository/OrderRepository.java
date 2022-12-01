package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import springboot.jpa.entity.Order;
import springboot.jpa.entity.OrderSearch;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id =:id")
    Order findOne(@Param("id") Long id);

    @Query(value = "select o " +
            "from Order o left join Member m on m.id = o.member_id " +
            "where o.status = status " +
            "and m.name like name", nativeQuery = true)
    List<Order> findALl(@Param("orderSearch") OrderSearch orderSearch);
}
