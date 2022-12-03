package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.id =:id")
    Item findOne(@Param("id") Long id);

//    @Query(value = "select b, i from Book b " +
//            "join b.item.id " +
//            "where b.item.id =:id")
//    Book findBook(@Param("id") Long id);

    @Query("select b from Book b")
    List<Book> findAllBook();

    @Query("select i from Item i")
    List<Item> findAllItem();
}
