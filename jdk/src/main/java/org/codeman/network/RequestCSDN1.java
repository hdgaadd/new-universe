package org.codeman.network;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hdgaadd
 * created on 2023/01/08
 *
 * https://tool.chinaz.com/tools/urlencode.aspx
 */
class RequestCSDN1 {

    private static final Integer PAGE_SIZE = 200;

    private static final Set<String> CONTAINER = new HashSet<>();

    private static final String MSG = "开发案例";

    private static final String KEYWORD = "设计";

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 1; i++) {
            get("https://blog.csdn.net/hdgaadd/article/details/137514322");
        }
    }

    private static String get(String url) throws Exception {
        String content = null;
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            content = builder.toString();
        }
        return content;
    }
}