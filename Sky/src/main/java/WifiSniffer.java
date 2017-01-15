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

    public static final String BASE_URL = "http://192.168.0.1/sky_wireless_channel.html";
    private static final String CONFIGURATION_CHANGE_URL = "http://192.168.0.1/sky_wireless_update.cmd";

    private static final Logger log = Logger.getLogger(WifiSniffer.class);
    private static final String NEW_CHANEL = "4";
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
        requestHelper.connect(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        log.info("Connection failed. Try again latter.");
        exit();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (isLoginFail(response)) {
            requestHelper.connect(this);
        }
        if (isConnectResponse(response)) {
            sendChannelChangeRequest();
        } else if (isChanelChangedResponse(response)) {
            log.info("Chanel changed to ".concat(NEW_CHANEL));
            exit();
        }
    }

    private boolean isLoginFail(Response response) {
        return response.code() == 401;
    }

    private boolean isConnectResponse(Response response) {
        if (response.request().method().equals("GET") && response.code() == 200
                && response.request().url().toString().equals(BASE_URL)) {
            return true;
        } else {
            return false;
        }
    }

    private void sendChannelChangeRequest() {
        authorization = new Authorization(ADMIN_LOGIN, ADMIN_PASSWORD);
        requestHelper.changeChanel(chanel, authorization, this);
    }

    private boolean isChanelChangedResponse(Response response) {
        if (response.request().method().equals("POST") && response.code() == 200
                && response.request().url().toString().equals(CONFIGURATION_CHANGE_URL)) {
            return true;
        } else {
            return false;
        }
    }

    private void exit() {
        System.exit(0);
    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
