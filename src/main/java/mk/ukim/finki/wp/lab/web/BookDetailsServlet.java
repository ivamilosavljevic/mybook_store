package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookDetails", urlPatterns = "/bookDetails")
@AllArgsConstructor
public class BookDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book selectedBook = (Book) req.getSession().getAttribute("selectedBook");
        Author selectedAuthor = (Author) req.getSession().getAttribute("selectedAuthor");

        if (selectedBook == null || selectedAuthor == null) {
            resp.sendRedirect(req.getContextPath() + "/listBooks");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("selectedBook", selectedBook);
        context.setVariable("selectedAuthor", selectedAuthor);

        springTemplateEngine.process(
                "bookDetails.html",
                context,
                resp.getWriter()
        );
    }
}
