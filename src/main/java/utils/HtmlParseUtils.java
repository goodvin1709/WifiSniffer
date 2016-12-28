package utils;

import model.ConnectionParams;
import model.LoginParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class HtmlParseUtils {
    private static final String PARAMS_TAG_NAME = "input";

    public static ConnectionParams getConnectionParams(String htmlBody) {
        Document html = Jsoup.parse(htmlBody);
        Element requestIdElement = html.getElementsByTag(PARAMS_TAG_NAME).first();
        return new ConnectionParams(Integer.valueOf(requestIdElement.val()));
    }

    public static LoginParams getLoginParams(String htmlBody, String password) {
        Document html = Jsoup.parse(htmlBody);
        String passwordFieldName = html.getElementsByTag(PARAMS_TAG_NAME).get(6).attributes().get("name");
        String authKey = html.getElementsByTag(PARAMS_TAG_NAME).get(8).val();
        String md5Path = md5Utils.encryptPassword(authKey, password);
        return new LoginParams(passwordFieldName, md5Path, authKey);
    }
}
