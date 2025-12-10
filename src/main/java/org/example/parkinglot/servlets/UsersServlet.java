package org.example.parkinglot.servlets;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.ejb.UserBean;

import java.io.IOException;
import java.util.List;

@DeclareRoles({"READ_USERS", "WRITE_USERS"})
@WebServlet(name = "UsersServlet", value = "/UsersServlet")
@ServletSecurity(
        // This applies the READ_USERS constraint to all unlisted methods (like GET).
        value = @HttpConstraint(rolesAllowed = "READ_USERS"),

        // Use @HttpMethodConstraint for specific method constraints (like POST).
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = "WRITE_USERS")
        }
)
public class UsersServlet extends HttpServlet {

    @Inject
    UserBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementation for handling POST requests (e.g., creating or deleting users)
    }
}