import model.ChangeWirelessConfiguration;
import model.ConnectionParams;
import model.LoginParams;
import model.WirelessChanel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.apache.log4j.Logger;
import provider.RequestHelper;
import utils.HtmlParseUtils;

import java.io.IOException;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class WifiSniffer implements Callback {

    public static final String routerRootPassword = "H9FKEFK3";
    private static final String HOME_PAGE = "Page(9096)=[Home]";
    private static final String LOGIN_PAGE = "Page(9146)=[Login]";
    private static final String ADVANCE_SETTING = "Page(9099)=[Advanced Settings]";
    public static final String PAGE_WIRELESS_CONFIGURATION = "Page(9102)=[Wireless Configuration]";

    private static final WirelessChanel setChanel = WirelessChanel.Chanel_3;

    private static final Logger log = Logger.getLogger(WifiSniffer.class);
    private RequestHelper requestHelper;
    private ConnectionParams connectionParams;
    private LoginParams authParams;
    private boolean isChanged;

    public static WifiSniffer getInstance() {
        return SingletonHolder._instance;
    }

    protected WifiSniffer() {
        requestHelper = RequestHelper.getInstance();
        log.info("loaded.Start connection to router.");
        connectToRouter();
    }

    private void connectToRouter() {
        requestHelper.connect(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        log.info("Connection failed. Try again latter.");
        System.exit(0);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String htmlBody = response.body().string();

        if (htmlBody.contains(HOME_PAGE)) {
            connectionParams = HtmlParseUtils.getConnectionParams(htmlBody);
            if (connectionParams.getRequestId() != 0) {
                log.info("Success parsing request_id = " + connectionParams.getRequestId());
                requestHelper.navigateToAdvanceSettings(connectionParams, this);
            }
        } else if (htmlBody.contains(LOGIN_PAGE)) {
            authParams = HtmlParseUtils.getLoginParams(htmlBody, routerRootPassword);
            requestHelper.authorization(connectionParams, authParams, this);
        } else if (htmlBody.contains(ADVANCE_SETTING)) {
            log.info("Success login.");
            requestHelper.navigateToWirelessConfiguration(connectionParams, this);
        } else if (htmlBody.contains(PAGE_WIRELESS_CONFIGURATION)) {
            if (!isChanged) {
                requestHelper.setChanel(connectionParams, getChanelConfiguration(), this);
                isChanged = true;
            } else {
                log.info("Chanel changed.");
                System.exit(0);
            }
        }
    }


    private ChangeWirelessConfiguration getChanelConfiguration() {
        ChangeWirelessConfiguration changeWirelessConfiguration = new ChangeWirelessConfiguration();
        changeWirelessConfiguration.set_wirelessChanelDropDown(get_WirelessChanelDropDown());
        changeWirelessConfiguration.setWirelessChanelDropDown(getWirelessChanelDropDown());
        return changeWirelessConfiguration;
    }

    private String getWirelessChanelDropDown() {
        if (setChanel == WirelessChanel.Automatic)
            return "-1";
        switch (setChanel) {
            case Chanel_1:
                return "1";
            case Chanel_2:
                return "2";
            case Chanel_3:
                return "3";
            case Chanel_4:
                return "4";
            case Chanel_5:
                return "5";
            case Chanel_6:
                return "6";
            case Chanel_7:
                return "7";
            case Chanel_8:
                return "8";
            case Chanel_9:
                return "9";
            case Chanel_10:
                return "10";
        }
        return "-1";
    }

    private String get_WirelessChanelDropDown() {
        String chanel = getWirelessChanelDropDown();
        if (chanel.equals("-1"))
            return "Automatic (Smart Wireless)";
        else {
            return "Channel ".concat(chanel);
        }
    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
