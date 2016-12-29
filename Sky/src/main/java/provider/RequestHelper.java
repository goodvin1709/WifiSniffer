package provider;

import model.Authorization;
import model.WirelessUpdate;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by [Goodvin1709] on 29.12.2016.
 */
public class RequestHelper {

    private static final String CONFIGURATION_CHANGE_URL = "http://192.168.0.1/sky_wireless_update.cmd";
    private CookieHandler cookieHandler;
    private OkHttpClient client;


    public static RequestHelper getInstance() {
        return SingletonHolder._instance;
    }

    protected RequestHelper() {
        cookieHandler = new CookieHandler();
        client = new OkHttpClient.Builder().cookieJar(cookieHandler).build();
    }

    public void changeChanel(WirelessUpdate updateChanel, Authorization authorization, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(CONFIGURATION_CHANGE_URL).newBuilder();

        RequestBody requestPostParams = new FormBody.Builder()
                .add("w_channel", updateChanel.getwChanel())
                .add("h_w_channel", updateChanel.gethWChanel())
                .add("todo", updateChanel.getTodo())
                .add("this_file", updateChanel.getThisFile())
                .add("next_file", updateChanel.getNextFile()).build();
        Request configChangeRequest = new Request.Builder().url(urlBuilder.toString())
                .header("Authorization", Credentials.basic(authorization.getUser(), authorization
                        .getPassword())).post(requestPostParams).build();
        client.newCall(configChangeRequest).enqueue(callback);
    }

    private static class SingletonHolder {
        protected static final RequestHelper _instance = new RequestHelper();
    }

    private class CookieHandler implements CookieJar {
        private List<Cookie> cookies;

        public CookieHandler() {
            cookies = new ArrayList<>();
        }

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            this.cookies.addAll(cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return cookies;
        }
    }
}
