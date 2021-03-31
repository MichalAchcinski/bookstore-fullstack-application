package pl.achcinski.bookstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.achcinski.bookstore.logic.BookService;
import pl.achcinski.bookstore.logic.ShoppingCartService;
import pl.achcinski.bookstore.mapper.BookMapper;
import pl.achcinski.bookstore.repository.BookRepository;
import pl.achcinski.bookstore.repository.CartItemRepository;

@RequiredArgsConstructor
@RequestMapping("/books")
@Controller
@Log4j2
public class WebBookController {
    final BookRepository bookRepository;
    final BookService bookService;
    final BookMapper bookMapper;
    final CartItemRepository cartItemRepository;
    final ShoppingCartService shoppingCartService;

}
