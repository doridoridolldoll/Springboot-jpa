package springboot.jpa.service;

import springboot.jpa.dto.BookDto;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);
    List<Item> findItems ();
    Item findOne(Long itemId);
    void createBook(BookDto dto);
    void updateBook(Long itemId, BookDto dto);
    Book updateBookForm(Long itemId);

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
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .build();
        return entity;
    }
}
