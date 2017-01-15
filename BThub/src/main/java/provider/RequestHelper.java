package provider;

import model.ChangeWirelessConfiguration;
import model.ConnectionParams;
import model.LoginParams;
import okhttp3.*;
import org.apache.log4j.Logger;

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

    public void navigateToAdvanceSettings(ConnectionParams params, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("active_page", "9099");
        urlBuilder.addQueryParameter("request_id", String.valueOf(params.getRequestId()));
        urlBuilder.addQueryParameter("nav_clear", "1");
        Request settingRequest = new Request.Builder().url(urlBuilder.build().toString()).build();
        client.newCall(settingRequest).enqueue(callback);
    }


    public void authorization(ConnectionParams connectionParams, LoginParams loginParams, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + INDEX_SUFFIX).newBuilder();
        RequestBody requestLoginParams = new FormBody.Builder()
                .add("request_id", String.valueOf(connectionParams.getRequestId()))
                .add("active_page", "9146")
                .add("active_page_str", loginParams.getActivePageStr())
                .add("mimic_button_field", loginParams.getMimicButtonField())
                .add("button_value", loginParams.getButtonValue())
                .add("post_id", String.valueOf(connectionParams.getPostId()))
                .add(loginParams.getPasswordFieldName(), "")
                .add("md5_pass", loginParams.getMd5Pass())
                .add("auth_key", loginParams.getAuthKey()).build();

        Request loginRequest = new Request.Builder().url(urlBuilder.toString()).post(requestLoginParams).build();
        client.newCall(loginRequest).enqueue(callback);
    }


    public void navigateToWirelessConfiguration(ConnectionParams params, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + INDEX_SUFFIX).newBuilder();
        urlBuilder.addQueryParameter("active_page", "9102");
        urlBuilder.addQueryParameter("request_id", String.valueOf(params.getRequestId()));
        urlBuilder.addQueryParameter("nav_clear", "1");
        Request settingRequest = new Request.Builder().url(urlBuilder.build().toString()).build();
        client.newCall(settingRequest).enqueue(callback);
    }


    public void setChanel(ConnectionParams params, ChangeWirelessConfiguration config, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + INDEX_SUFFIX).newBuilder();
        RequestBody requestChanelChange = new FormBody.Builder()
                .add("request_id", String.valueOf(params.getRequestId()))
                .add("active_page", config.getActivePage())
                .add("active_page_str", config.getActivePageStr())
                .add("mimic_button_field", config.getMimicButtonField())
                .add("button_value", config.getButtonValue())
                .add("post_id", String.valueOf(params.getPostId()))
                .add("wireless_enabled_defval", config.getWirelessEnabledDefVal())
                .add("wireless_enabled", config.getWirelessEnabled())
                .add("wireless_ssid_defval", config.getWirelessSsidDefVal())
                .add("wireless_ssid", config.getWirelessSsid())
                .add("_wireless_mode", config.get_wirelessMode())
                .add("wireless_mode", config.getWirelessMode())
                .add("wireless_mode_defval", config.getWirelessModeDefVal())
                .add("_wireless_channel_dropdown", config.get_wirelessChanelDropDown())
                .add("wireless_channel_dropdown", config.getWirelessChanelDropDown())
                .add("wireless_security_defval", config.getWirelessSecurityDefVal())
                .add("wireless_security", config.getWirelessSecurity())
                .add("wpa_shared_key_defval", config.getWpaSharedKeyDefVal())
                .add("wpa_shared_key", config.getWpaSharedKey())
                .add("strength", config.getStrength()).build();
        Request configChangeRequest = new Request.Builder().url(urlBuilder.toString()).post(requestChanelChange).build();
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