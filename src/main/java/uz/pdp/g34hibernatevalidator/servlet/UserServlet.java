package uz.pdp.g34hibernatevalidator.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import uz.pdp.g34hibernatevalidator.config.DatasourceManager;
import uz.pdp.g34hibernatevalidator.domain.User;

import java.time.LocalDate;
import java.util.Set;

@WebServlet(name = "UserServet", value = "/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String imgUrl = req.getParameter("imgUrl");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDate = req.getParameter("birthDate");
        String salary = req.getParameter("salary");
        String cardNumber = req.getParameter("cardNumber");
        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
                .imgUrl(imgUrl)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate != null ? LocalDate.parse(birthDate) : null)
                .salary(salary != null ? Double.parseDouble(salary) : null)
                .cardNumber(cardNumber)
                .build();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            DatasourceManager.ENTITY_MANAGER.getTransaction().begin();
            DatasourceManager.ENTITY_MANAGER.persist(user);
            DatasourceManager.ENTITY_MANAGER.getTransaction().commit();
        } else {
            for (ConstraintViolation<?> constraintViolation : violations) {
                System.out.println(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage());
            }
        }
    }
}
