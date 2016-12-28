/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class Loader {

    private static boolean isCharlesDebug = true;

    public static void main(String[] args) {

        if (isCharlesDebug) {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "8888");
        }
        WifiSniffer.getInstance();
    }
}
