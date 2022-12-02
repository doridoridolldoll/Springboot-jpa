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
import springboot.jpa.form.BookForm;
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
        model.addAttribute("form", new BookDto());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Valid BookDto dto) {
        Book item = Book.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .build();
        log.info("item ={}", item);

        itemRepository.save(item);

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

        Book dto = Book.builder()
                .name(findItem.getName())
                .price(findItem.getPrice())
                .stockQuantity(findItem.getStockQuantity())
                .author(findItem.getAuthor())
                .isbn(findItem.getIsbn())
                .build();

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
