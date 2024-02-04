package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "author", urlPatterns = "/author")
@AllArgsConstructor
public class AuthorServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        Book selectedBook = (Book) req.getSession().getAttribute("selectedBook");
        if (selectedBook == null) {
            resp.sendRedirect(req.getContextPath() + "/listBooks"); // No book selected; redirect to list
            return;
        }

        List<Author> availableAuthors = authorService.listAuthors(); // Fetch the list of all authors.

        WebContext context = new WebContext(webExchange);
        context.setVariable("selectedBook", selectedBook);
        context.setVariable("authors", availableAuthors);

        springTemplateEngine.process(
                "authorList.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorIdString = req.getParameter("authorId");

        Long authorId = null;
        try {
            authorId = Long.valueOf(authorIdString);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid author ID.");
            req.getRequestDispatcher("/authorList").forward(req, resp);
            return;
        }

        Book selectedBook = (Book) req.getSession().getAttribute("selectedBook");
        if (selectedBook == null) {
            req.setAttribute("error", "No book was selected or session has expired.");
            req.getRequestDispatcher("/listBooks").forward(req, resp);
            return;
        }

        Optional<Author> optionalAuthor = authorService.findById(authorId);
        if (optionalAuthor.isEmpty()) {
            req.setAttribute("error", "Selected author not found.");
            req.getRequestDispatcher("/authorList").forward(req, resp);
            return;
        }
        Author selectedAuthor = optionalAuthor.get();

        this.bookService.addAuthorToBook(selectedAuthor.getId(), selectedBook.getIsbn());
        req.getSession().setAttribute("selectedAuthor", selectedAuthor);
        resp.sendRedirect(req.getContextPath() + "/bookDetails");
    }

}
