package com.j01y.fastxposedsekiro.Utils;

import android.util.Log;

import java.io.File;
import java.util.UUID;

public class Utils {
    public static final String TAG = "J01Y_HOOK";

    public static String getClientId(String AppFilesDir) {
        try {
            File clientIdFile = new File(AppFilesDir, "CLIENT_ID");
            if (!clientIdFile.exists()) {
                String clientId = UUID.randomUUID().toString();
                FileUtils.writeFile(clientIdFile.getPath(), clientId, false);
                return clientId;
            }
            return FileUtils.readFileToString(clientIdFile.getPath());
        } catch (Exception ex) {
            // 如果文件读写失败，随机返回UUID
            Log.i(TAG, "getClientId err: " + ex.getMessage());
            return UUID.randomUUID().toString();
        }
    }
}
