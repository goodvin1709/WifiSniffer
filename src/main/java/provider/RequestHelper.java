package provider;

import model.ConnectionParams;
import model.LoginParams;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class RequestHelper {

    private static final Logger log = Logger.getLogger(RequestHelper.class);
    public static final String BASE_URL = "http://192.168.1.254/";
    private static final String INDEX_SUFFIX = "index.cgi";
    private OkHttpClient client;
    private CookieHandler cookieHandler;

    protected RequestHelper() {
        cookieHandler = new CookieHandler();
        client = new OkHttpClient.Builder().cookieJar(cookieHandler).build();
    }

    public static RequestHelper getInstance() {
        return SingletonHolder._instance;
    }


    public void connect(Callback callback) {
        Request connectRequest = new Request.Builder()
                .url(BASE_URL).build();
        client.newCall(connectRequest).enqueue(callback);
        log.info("Connect request sended.");
    }

    public void navigateToSettingPage(ConnectionParams params, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("active_page", "9098");
        urlBuilder.addQueryParameter("request_id", String.valueOf(params.getRequestId()));
        urlBuilder.addQueryParameter("nav_clear", "1");
        Request settingRequest = new Request.Builder().url(urlBuilder.build().toString()).build();
        client.newCall(settingRequest).enqueue(callback);
    }


    public void authorization(LoginParams loginParams, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + INDEX_SUFFIX).newBuilder();
        RequestBody requestLoginParams = new FormBody.Builder()
                .add("request_id", String.valueOf(loginParams.getConnectionParams().getRequestId()))
                .add("active_page", "9146")
                .add("active_page_str", loginParams.getActivePageStr())
                .add("mimic_button_field", loginParams.getMimicButtonField())
                .add("button_value", loginParams.getButtonValue())
                .add("post_id", String.valueOf(loginParams.getConnectionParams().getPostId()))
                .add(loginParams.getPasswordFieldName(), "")
                .add("md5_pass", loginParams.getMd5Pass())
                .add("auth_key", loginParams.getAuthKey()).build();

        Request loginRequest = new Request.Builder().url(urlBuilder.toString()).post(requestLoginParams).build();
        client.newCall(loginRequest).enqueue(callback);
        loginParams.getConnectionParams().setPostId(loginParams.getConnectionParams().getPostId() + 1);
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