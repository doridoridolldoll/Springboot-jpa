package springboot.jpa.service;

import springboot.jpa.dto.BookDto;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;

import javax.validation.Valid;
import java.util.List;

public interface ItemService {

    void saveItem(Item item);
    List<Item> findItems ();
    Item findOne(Long itemId);
    void createBook(BookDto dto);
    void updateBook(Long itemId, @Valid BookDto dto);

    default BookDto bookEntityToDto(Book book) {
        BookDto dto = BookDto.builder()
                .name(book.getName())
                .price(book.getPrice())
                .stockQuantity(book.getStockQuantity())
                .build();
        return dto;
    }

    default Book bookDtoToEntity(BookDto dto) {
        Book entity = Book.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .build();
        return entity;
    }
}
