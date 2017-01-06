package bsr.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 2017-01-06.
 */
public class Account {

    private long id;
    private String login;
    private String password;
    private List<Bill> bills = new ArrayList<>();

    public Account() {

    }

    public Account(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", bills=" + bills;
    }
}
