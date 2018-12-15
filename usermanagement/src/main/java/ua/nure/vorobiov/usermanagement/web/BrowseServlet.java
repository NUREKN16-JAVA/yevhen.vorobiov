package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class BrowseServlet extends HttpServlet {

    private static final String BROWSE_JSP = "/browse.jsp";
    private static final String ERROR_ATTRIBUTE = "error";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("addButton") != null) {
            add(req, resp);
        } else if (req.getParameter("editButton") != null) {
            edit(req, resp);
        } else if (req.getParameter("deleteButton") != null) {
            delete(req, resp);
        } else if (req.getParameter("detailsButton") != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        if (Objects.isNull(idString) || idString.trim().isEmpty()) {
            redirectBackWithError(req, resp, "You should choose user");
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(Long.parseLong(idString));
            req.getSession(true).setAttribute("user", user);
        } catch (Exception e) {
            redirectBackWithError(req, resp, "Error :" + e.toString());
            return;
        }
        req.getRequestDispatcher("/edit").forward(req, resp);
    }

    private void redirectBackWithError(HttpServletRequest req, HttpServletResponse resp, String s) throws ServletException, IOException {
        req.setAttribute(ERROR_ATTRIBUTE, s);
        req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession(true).setAttribute("users", users);
            req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }
    }
}
