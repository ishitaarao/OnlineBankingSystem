package model;

/** Abstract base class for every user of the bank (inheritance + abstraction). */
public abstract class User {
    private int userId;
    private String fullName;
    private String email;
    private String username;
    private String passwordHash;

    protected User(int userId, String fullName, String email, String username, String passwordHash) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /** Each subclass returns its own role name (polymorphism). */
    public abstract String getRole();

    /** Each subclass opens its own dashboard (polymorphism). */
    public abstract String getDashboardName();

    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return userId + " | " + fullName + " | " + email + " | " + username + " | " + getRole();
    }
}
