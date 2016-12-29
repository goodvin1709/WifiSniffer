package model;

/**
 * Created by [Goodvin1709] on 29.12.2016.
 */
public class Authorization {

    private String user;
    private String password;

    public Authorization(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
