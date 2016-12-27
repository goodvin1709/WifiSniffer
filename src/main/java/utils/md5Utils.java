package utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class md5Utils {

    public static String encryptPassword(String authKey, String password) {
        String encode = password.concat(authKey);
        return DigestUtils.md5Hex(encode);
    }
}
