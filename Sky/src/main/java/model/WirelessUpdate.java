package model;

/**
 * Created by [Goodvin1709] on 29.12.2016.
 */
public class WirelessUpdate {

    private static final String SKY_WIRELESS_TODO = "save";
    private static final String SKY_FILE = "sky_wireless_channel.html";

    String wChanel;
    String hWChanel;
    String todo;
    String thisFile;
    String nextFile;

    public WirelessUpdate(String wChanel) {
        this.wChanel = wChanel;
        this.hWChanel = wChanel;
        this.todo = SKY_WIRELESS_TODO;
        this.thisFile = SKY_FILE;
        this.nextFile = SKY_FILE;
    }

    public String getwChanel() {
        return wChanel;
    }

    public String gethWChanel() {
        return hWChanel;
    }

    public String getTodo() {
        return todo;
    }

    public String getThisFile() {
        return thisFile;
    }

    public String getNextFile() {
        return nextFile;
    }
}
