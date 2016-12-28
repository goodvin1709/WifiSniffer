package model;

/**
 * Created by [Goodvin1709] on 28.12.2016.
 */
public class ChangeWirelessConfiguration {

    private String activePage;
    private String activePageStr;
    private String mimicButtonField;
    private String buttonValue;
    private String wirelessEnabledDefVal;
    private String wirelessEnabled;
    private String wirelessSsidDefVal;
    private String wirelessSsid;
    private String _wirelessMode;
    private String wirelessMode;
    private String wirelessModeDefVal;
    private String _wirelessChanelDropDown;
    private String wirelessChanelDropDown;
    private String wirelessSecurity;
    private String wirelessSecurityDefVal;
    private String wpaSharedKeyDefVal;
    private String wpaSharedKey;
    private String strength;

    public ChangeWirelessConfiguration() {
        this.activePage = "9102";
        this.activePageStr = "page_settings_a_wireless_2_4_ghz";
        this.mimicButtonField = "submit_button_apply: ..";
        this.buttonValue = "wireless_channel_dropdown";
        this.wirelessEnabledDefVal = "1";
        this.wirelessEnabled = "1";
        this.wirelessSsid = "BTHub5-3F38";
        this.wirelessSsidDefVal = "BTHub5-3F38";
        this._wirelessMode = "802.11 b/g/n (up to 144 Mb/s)";
        this.wirelessMode = "2304";
        this.wirelessModeDefVal = "2304";
        this.wirelessSecurity = "3";
        this.wirelessSecurityDefVal = "3";
        this.wpaSharedKey = "dc56d6bace";
        this.wpaSharedKeyDefVal = "dc56d6bace";
        this.strength = "medium";
    }

    public void set_wirelessChanelDropDown(String _wirelessChanelDropDown) {
        this._wirelessChanelDropDown = _wirelessChanelDropDown;
    }

    public String getWirelessSecurityDefVal() {
        return wirelessSecurityDefVal;
    }

    public void setWirelessChanelDropDown(String wirelessChanelDropDown) {
        this.wirelessChanelDropDown = wirelessChanelDropDown;
    }

    public String getActivePage() {
        return activePage;
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

    public String getWirelessEnabledDefVal() {
        return wirelessEnabledDefVal;
    }

    public String getWirelessEnabled() {
        return wirelessEnabled;
    }

    public String getWirelessSsidDefVal() {
        return wirelessSsidDefVal;
    }

    public String getWirelessSsid() {
        return wirelessSsid;
    }

    public String get_wirelessMode() {
        return _wirelessMode;
    }

    public String getWirelessMode() {
        return wirelessMode;
    }

    public String getWirelessModeDefVal() {
        return wirelessModeDefVal;
    }

    public String get_wirelessChanelDropDown() {
        return _wirelessChanelDropDown;
    }

    public String getWirelessChanelDropDown() {
        return wirelessChanelDropDown;
    }

    public String getWirelessSecurity() {
        return wirelessSecurity;
    }

    public String getWpaSharedKeyDefVal() {
        return wpaSharedKeyDefVal;
    }

    public String getWpaSharedKey() {
        return wpaSharedKey;
    }

    public String getStrength() {
        return strength;
    }
}
