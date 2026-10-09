package model;

/** Administrator: manages users, transactions and system settings. */
public class Admin extends User {

    public Admin(int userId, String fullName, String email, String username, String passwordHash) {
        super(userId, fullName, email, username, passwordHash);
    }

    @Override
    public String getRole() { return "ADMIN"; }

    @Override
    public String getDashboardName() { return "Admin Dashboard"; }
}
