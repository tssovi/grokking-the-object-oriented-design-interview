package models;

import enums.AccountStatus;

public class Account {
    private String id;
    private String password;
    private AccountStatus status;

    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    public Account(String id, String password) {
        this(id, password, AccountStatus.ACTIVE);
    }

    public boolean resetPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    // Getters
    public String getId() {
        return id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    // Setters
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
