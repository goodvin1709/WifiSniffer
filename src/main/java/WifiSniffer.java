import model.ConnectionParams;
import model.LoginParams;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.apache.log4j.Logger;
import provider.RequestHelper;
import utils.HtmlParseUtils;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class WifiSniffer implements Callback {

    public static final String routerRootPassword = "H9FKEFK3";
    private static final String HOME_PAGE = "Page(9096)=[Home]";
    private static final String LOGIN_PAGE = "Page(9146)=[Login]";

    private static final Logger log = Logger.getLogger(WifiSniffer.class);
    private RequestHelper requestHelper;
    private ConnectionParams connectionParams;
    private LoginParams authParams;

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
                log.info("Success parsing reqduest_id = " + connectionParams.getRequestId());
                requestHelper.navigateToSettingPage(connectionParams, this);
            }
        } else if (htmlBody.contains(LOGIN_PAGE)) {
            authParams = HtmlParseUtils.getLoginParams(connectionParams, htmlBody, routerRootPassword);
            requestHelper.authorization(authParams, this);
        } else if (htmlBody.contains("Page(9098)=[Basic Settings]")) {
            log.info("Success login.");
            PrintWriter printWriter = new PrintWriter("C://logined.html");
            printWriter.print(htmlBody);
            printWriter.close();
        }
    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
