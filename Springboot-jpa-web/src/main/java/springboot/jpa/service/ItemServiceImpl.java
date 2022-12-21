package springboot.jpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.dto.BookDto;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;
import springboot.jpa.repository.ItemRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItems () {
        return itemRepository.findAll();
    }

    @Override
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public void createBook(BookDto dto) {
        Book entity = bookDtoToEntity(dto);

        itemRepository.save(entity);
    }

    public Book updateBookForm(Long itemId) {
        Book item = (Book) itemRepository.findOne(itemId);

        Book book = Book.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .build();
        return book;
    }

    @Transactional
    public void updateBook(Long itemId, BookDto dto) {
        log.info("updateBook dto = {}", dto);

        Book entity = bookDtoToEntity(dto);

        Book book = (Book) itemRepository.findOne(itemId);
        book.updateBook(entity.getAuthor(), entity.getIsbn());
        log.info("updateBook book = {}," + entity.getAuthor(), entity.getIsbn());

        Item item = itemRepository.findOne(itemId);
        item.updateItem(entity.getName(), entity.getPrice(), entity.getStockQuantity());
        log.info("updateBook item = {},{}" + entity.getName(), entity.getPrice(), entity.getStockQuantity());
    }
}
