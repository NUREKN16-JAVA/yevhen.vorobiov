package ua.nure.vorobiov.usermanagement.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DetailsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull((req.getParameter("backButton")))) {
            req.getSession(true).removeAttribute("user");
            redirect(req, resp, "/browse");
        } else {
            redirect(req, resp, "/details.jsp");
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
