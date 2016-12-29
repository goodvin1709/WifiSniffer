package model;

/**
 * Created by [Goodvin1709] on 29.12.2016.
 */
public class Authorization {

    private static final String USER_LOGIN_FIELD_NAME = "User ID";
    private static final String USER_PASSWORD_FIELD_NAME = "Password";
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

    public static String getUserLoginFieldName() {
        return USER_LOGIN_FIELD_NAME;
    }

    public static String getUserPasswordFieldName() {
        return USER_PASSWORD_FIELD_NAME;
    }
}
