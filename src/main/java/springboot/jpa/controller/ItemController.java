package springboot.jpa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.jpa.dto.BookDto;
import springboot.jpa.entity.item.Book;
import springboot.jpa.entity.item.Item;
import springboot.jpa.repository.ItemRepository;
import springboot.jpa.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        BookDto book = new BookDto();
        model.addAttribute("dto", book);
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(BookDto dto) {
        log.info("createItem dto ={}", dto);
        itemService.createBook(dto);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String List(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book findItem = (Book) itemRepository.findOne(itemId);
        log.info("findItem = {}", findItem);

        Book dto = Book.builder()
                .name(findItem.getName())
                .price(findItem.getPrice())
                .stockQuantity(findItem.getStockQuantity())
                .author(findItem.getAuthor())
                .isbn(findItem.getIsbn())
                .build();
        log.info("dto = {}", dto.getAuthor());
        model.addAttribute("dto", dto);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,
                             @Valid @ModelAttribute("dto") BookDto dto) {
        itemService.updateBook(itemId, dto);

        return "redirect:/";
    }
}
