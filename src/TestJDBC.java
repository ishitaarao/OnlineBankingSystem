import dao.UserDAO;
import model.User;

import java.util.List;

/** Quick test for the Review 1 screenshot: saves a user, reads it back, tests login. */
public class TestJDBC {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        try {
            String username = "test" + System.currentTimeMillis() % 100000;
            int id = dao.register("Test Customer", username + "@mail.com", username, "pass123", "CUSTOMER");
            System.out.println("Saved user with id: " + id);

            System.out.println("\nAll users in database:");
            List<User> users = dao.getAllUsers();
            for (User u : users) System.out.println("  " + u);

            User loggedIn = dao.login(username, "pass123");
            System.out.println("\nLogin result: "
                    + (loggedIn != null ? "SUCCESS -> opens " + loggedIn.getDashboardName() : "FAILED"));
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
