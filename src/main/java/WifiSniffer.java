import model.ConnectionParams;
import model.LoginParams;
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

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String htmlBody = response.body().string();

        if (htmlBody.contains("Page(9096)=[Home]")) {
            connectionParams = HtmlParseUtils.getConnectionParams(htmlBody);
            if (connectionParams.getRequestId() != 0) {
                log.info("Success parsing requset_id = " + connectionParams.getRequestId());
                requestHelper.navigateToSettingPage(connectionParams, this);
            }
        }

        else if (htmlBody.contains("Page(9146)=[Login]")) {
            authParams = HtmlParseUtils.getLoginParams(connectionParams, htmlBody, routerRootPassword);
            requestHelper.authorization(authParams, this);
        }
        else
        {
            System.out.println(htmlBody);
        }



    }

    private static class SingletonHolder {
        protected static final WifiSniffer _instance = new WifiSniffer();
    }
}
