package ru.levelp.java.junior.haw;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries(
        @NamedQuery(
                name = User.FindByLogin,
                query = "select u from User u where u.login = :login"
        )
)
@Table(name = "users")
public class User {
    public static final String RootUserName = "root";
    public static final String FindByLogin = "Users.findByLogin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    @Column(unique = true)
    private String login;

    @Column
    private double balance;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public User() {
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
