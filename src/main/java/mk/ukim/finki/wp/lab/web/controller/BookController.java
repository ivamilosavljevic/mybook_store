package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/" , "/books"})
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("books", bookService.listBooks());
        return "listBooks";
    }

    @GetMapping("/add-form")
    public String getAddBookForm(Model model) {
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("bookStores", bookStores);
        return "add-book";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        if (this.bookService.findBookById(id).isPresent()) {
            Book book = this.bookService.findBookById(id).get();
            List<BookStore> bookStores = bookStoreService.findAll();
            model.addAttribute("book", book);
            model.addAttribute("bookStores", bookStores);
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }


    @PostMapping("/add")
    public String saveBook(@RequestParam String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        bookService.saveBook(isbn, title, genre, year, bookStoreId);
        return "redirect:/books";
    }

    @PostMapping("/select")
    public String selectBook(@RequestParam String bookIsbn, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookService.findBookByIsbn(bookIsbn);
        if (optionalBook.isPresent()) {
            Book selectedBook = optionalBook.get();
            request.getSession().setAttribute("selectedBook", selectedBook);
            return "redirect:/authors"; // Redirect to the author page
        } else {
            redirectAttributes.addFlashAttribute("error", "Book with ISBN " + bookIsbn + " does not exist");
            return "redirect:/books";
        }
    }


    @PutMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        bookService.editBook(bookId, isbn, title, genre, year, bookStoreId);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books";
    }

    /*@DeleteMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books";
    }*/
}
