package model;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class LoginParams {

    private String activePageStr;
    private String mimicButtonField;
    private String buttonValue;
    private String passwordFieldName;
    private String md5Pass;
    private String authKey;


    public LoginParams(String passwordFieldName, String md5Pass, String authKey) {
        this.passwordFieldName = passwordFieldName;
        this.md5Pass = md5Pass;
        this.authKey = authKey;
        this.activePageStr = "9146";
        this.activePageStr = "bt_login";
        this.buttonValue = "";
        this.mimicButtonField = "submit_button_login_submit: ..";
    }

    public String getActivePageStr() {
        return activePageStr;
    }

    public String getMimicButtonField() {
        return mimicButtonField;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public String getPasswordFieldName() {
        return passwordFieldName;
    }

    public String getMd5Pass() {
        return md5Pass;
    }

    public String getAuthKey() {
        return authKey;
    }
}
