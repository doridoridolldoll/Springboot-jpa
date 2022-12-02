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

    @Transactional
    public void updateBook(Long itemId, @Valid BookDto dto) {
        Book book = itemRepository.findBook(itemId);
        book.updateBook(dto.getAuthor(), dto.getIsbn());

        Item item = itemRepository.findOne(itemId);
        item.updateItem(dto.getName(), dto.getPrice(), dto.getStockQuantity());
    }
}
