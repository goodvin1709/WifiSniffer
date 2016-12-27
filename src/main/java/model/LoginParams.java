package model;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class LoginParams {

    private ConnectionParams connectionParams;
    private String activePageStr;
    private String mimicButtonField;
    private String buttonValue;
    private String passwordFieldName;
    private String md5Pass;
    private String authKey;


    public LoginParams(ConnectionParams connectionParams, String passwordFieldName, String md5Pass, String authKey) {
        this.connectionParams = connectionParams;
        this.passwordFieldName = passwordFieldName;
        this.md5Pass = md5Pass;
        this.authKey = authKey;
        this.activePageStr = "9146";
        this.activePageStr = "bt_login";
        this.buttonValue = "";
        this.mimicButtonField = "submit_button_login_submit%3A+..";
    }

    public ConnectionParams getConnectionParams() {
        return connectionParams;
    }

    public void setConnectionParams(ConnectionParams connectionParams) {
        this.connectionParams = connectionParams;
    }

    public String getActivePageStr() {
        return activePageStr;
    }

    public void setActivePageStr(String activePageStr) {
        this.activePageStr = activePageStr;
    }

    public String getMimicButtonField() {
        return mimicButtonField;
    }

    public void setMimicButtonField(String mimicButtonField) {
        this.mimicButtonField = mimicButtonField;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getPasswordFieldName() {
        return passwordFieldName;
    }

    public void setPasswordFieldName(String passwordFieldName) {
        this.passwordFieldName = passwordFieldName;
    }

    public String getMd5Pass() {
        return md5Pass;
    }

    public void setMd5Pass(String md5Pass) {
        this.md5Pass = md5Pass;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
