package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showAuthors(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Book selectedBook = (Book) request.getSession().getAttribute("selectedBook");
        if (selectedBook == null) {
            return "redirect:/listBooks"; // Redirect if no book is selected
        }

        List<Author> availableAuthors = authorService.listAuthors();
        model.addAttribute("selectedBook", selectedBook);
        model.addAttribute("authors", availableAuthors);
        return "authorList";
    }

    @PostMapping
    public String selectAuthor(@RequestParam Long authorId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Book selectedBook = (Book) request.getSession().getAttribute("selectedBook");
        if (selectedBook == null) {
            return "redirect:/listBooks"; // Redirect if no book is selected
        }

        Optional<Author> optionalAuthor = authorService.findById(authorId);
        if (optionalAuthor.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Selected author not found.");
            return "redirect:/authorList";
        }

        Author selectedAuthor = optionalAuthor.get();
        bookService.addAuthorToBook(selectedAuthor.getId(), selectedBook.getIsbn());
        request.getSession().setAttribute("selectedAuthor", selectedAuthor);
        return "redirect:/bookDetails";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String biography) {
        authorService.saveAuthor(name, surname, biography);
        return "redirect:/authors";
    }

    @GetMapping("/add-form")
    public String getAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author";
    }

    @GetMapping("/edit-form/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            model.addAttribute("author", author);
            return "add-author";
        }
        return "redirect:/authors?error=AuthorNotFound";
    }
}
