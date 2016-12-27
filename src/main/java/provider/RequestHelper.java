package provider;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.log4j.Logger;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class RequestHelper {

    private static final Logger log = Logger.getLogger(RequestHelper.class);
    private static final String BASE_URL = "http://192.168.1.254/";
    OkHttpClient client;

    protected RequestHelper() {
        client = new OkHttpClient();
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

    private static class SingletonHolder {
        protected static final RequestHelper _instance = new RequestHelper();
    }
}
