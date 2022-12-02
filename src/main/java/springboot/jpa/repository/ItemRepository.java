package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.id =:id")
    Item findOne(@Param("id") Long id);

    @Query("select b from Book b where b.id =:id")
    Book findBook(@Param("id") Long id);
}
