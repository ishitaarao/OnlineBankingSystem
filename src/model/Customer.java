package model;

/** Customer: manages own accounts, makes transactions, uses banking services. */
public class Customer extends User {

    public Customer(int userId, String fullName, String email, String username, String passwordHash) {
        super(userId, fullName, email, username, passwordHash);
    }

    @Override
    public String getRole() { return "CUSTOMER"; }

    @Override
    public String getDashboardName() { return "Customer Dashboard"; }
}
