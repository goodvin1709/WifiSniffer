import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.apache.log4j.Logger;
import provider.RequestHelper;

import java.io.IOException;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class WifiSniffer implements Callback{
    private static final Logger log = Logger.getLogger(WifiSniffer.class);
    private RequestHelper requestHelper;

    public static WifiSniffer getInstance() {
        return SingletonHolder._instance;
    }

    protected WifiSniffer() {
        requestHelper = RequestHelper.getInstance();
        log.info("loaded.");
        connectToRouter();
    }

    private void connectToRouter() {
        requestHelper.connect(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        log.info("WOW");
    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
