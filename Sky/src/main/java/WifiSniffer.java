import model.Authorization;
import model.WirelessUpdate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.apache.log4j.Logger;
import provider.RequestHelper;

import java.io.IOException;

/**
 * Created by [Goodvin1709] on 28.12.2016.
 */
public class WifiSniffer implements Callback {

    private static final Logger log = Logger.getLogger(WifiSniffer.class);
    private static final String NEW_CHANEL = "3";
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "sky";
    private WirelessUpdate chanel;
    private Authorization authorization;
    private RequestHelper requestHelper;

    public static WifiSniffer getInstance() {
        return SingletonHolder._instance;
    }

    protected WifiSniffer() {
        chanel = new WirelessUpdate(NEW_CHANEL);
        authorization = new Authorization(ADMIN_LOGIN, ADMIN_PASSWORD);
        requestHelper = RequestHelper.getInstance();
        requestHelper.changeChanel(chanel, authorization, this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        log.info("Connection failed. Try again latter.");
        System.exit(0);

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
