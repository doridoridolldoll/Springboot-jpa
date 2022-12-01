package springboot.jpa.service;

import springboot.jpa.dto.BookDto;
import springboot.jpa.dto.ItemDto;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);
    List<Item> findItems ();
    Item findOne(Long itemId);

    default BookDto bookEntityToDto(Book book) {
        BookDto dto = BookDto.builder()
                .name(book.getName())
                .price(book.getPrice())
                .stock(book.getStockQuantity())
                .build();
        return dto;
    }

    default Book bookDtoToEntity(BookDto dto) {
        Book entity = Book.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStock())
                .build();
        return entity;
    }
}
