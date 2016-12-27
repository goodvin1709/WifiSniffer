package utils;

import model.ConnectionParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class HtmlParseUtils {
    private static final String PARAMS_TAG_NAME = "input";

    public static ConnectionParams getConnectionParams(String htmlBody) {
        Document html = Jsoup.parse(htmlBody);
        Element requestIdElelment = html.getElementsByTag(PARAMS_TAG_NAME).first();
        return new ConnectionParams(Integer.valueOf(requestIdElelment.val()));
    }
}
