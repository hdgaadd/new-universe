package org.codeman.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hdgaadd
 * created on 2023/01/08
 *
 * https://tool.chinaz.com/tools/urlencode.aspx
 */
class RequestCSDN1 {

    private static final List<String> urlList = Arrays.asList(
    "https://blog.csdn.net/hdgaadd/article/details/136200021",
    "https://blog.csdn.net/hdgaadd/article/details/136217109",
    "https://blog.csdn.net/hdgaadd/article/details/136243500",
    "https://blog.csdn.net/hdgaadd/article/details/136273941",
    "https://blog.csdn.net/hdgaadd/article/details/136323687",
    "https://blog.csdn.net/hdgaadd/article/details/136383018",
    "https://blog.csdn.net/hdgaadd/article/details/136399736",
    "https://blog.csdn.net/hdgaadd/article/details/136414408",
    "https://blog.csdn.net/hdgaadd/article/details/136434611",
    "https://blog.csdn.net/hdgaadd/article/details/136489516",
    "https://blog.csdn.net/hdgaadd/article/details/136545625",
    "https://blog.csdn.net/hdgaadd/article/details/136601786",
    "https://blog.csdn.net/hdgaadd/article/details/136654409",
    "https://blog.csdn.net/hdgaadd/article/details/136706699",
    "https://blog.csdn.net/hdgaadd/article/details/136750761",
    "https://blog.csdn.net/hdgaadd/article/details/136816072",
    "https://blog.csdn.net/hdgaadd/article/details/136849948",
    "https://blog.csdn.net/hdgaadd/article/details/136887897",
    "https://blog.csdn.net/hdgaadd/article/details/136914795",
    "https://blog.csdn.net/hdgaadd/article/details/136951003",
    "https://blog.csdn.net/hdgaadd/article/details/136979372",
    "https://blog.csdn.net/hdgaadd/article/details/137020665",
    "https://blog.csdn.net/hdgaadd/article/details/137056437",
    "https://blog.csdn.net/hdgaadd/article/details/137123762",
    "https://blog.csdn.net/hdgaadd/article/details/137152984",
    "https://blog.csdn.net/hdgaadd/article/details/137174831",
    "https://blog.csdn.net/hdgaadd/article/details/137239455",
    "https://blog.csdn.net/hdgaadd/article/details/137351212",
    "https://blog.csdn.net/hdgaadd/article/details/137514322",
    "https://blog.csdn.net/hdgaadd/article/details/137645700",
    "https://blog.csdn.net/hdgaadd/article/details/137719325",
    "https://blog.csdn.net/hdgaadd/article/details/137781493",
    "https://blog.csdn.net/hdgaadd/article/details/137838828",
    "https://blog.csdn.net/hdgaadd/article/details/137929332",
    "https://blog.csdn.net/hdgaadd/article/details/137971436",
    "https://blog.csdn.net/hdgaadd/article/details/138196280",
    "https://blog.csdn.net/hdgaadd/article/details/138252580",
    "https://blog.csdn.net/hdgaadd/article/details/138445960",
    "https://blog.csdn.net/hdgaadd/article/details/138577716",
    "https://blog.csdn.net/hdgaadd/article/details/138675311",
    "https://blog.csdn.net/hdgaadd/article/details/138853203",
    "https://blog.csdn.net/hdgaadd/article/details/139009159",
    "https://blog.csdn.net/hdgaadd/article/details/139099118",
    "https://blog.csdn.net/hdgaadd/article/details/139328697",
    "https://blog.csdn.net/hdgaadd/article/details/139507068",
    "https://blog.csdn.net/hdgaadd/article/details/139549774",
    "https://blog.csdn.net/hdgaadd/article/details/139602312",
    "https://blog.csdn.net/hdgaadd/article/details/139685074",
    "https://blog.csdn.net/hdgaadd/article/details/139750952");


    private static final int TOTAL_REQUESTS = 10000;
    private static final long PERIOD = 24 * 60 * 60 * 1000 / TOTAL_REQUESTS;

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                try {
                    int index = new Random().nextInt(urlList.size());
                    String url = urlList.get(index);
                    String result = get(url);
                    count++;
                    System.out.println("第 " + count + " 次访问，index为" + index + ": " + url);
                    if (count >= TOTAL_REQUESTS) {
                        scheduler.shutdown();
                        System.out.println("已完成所有访问。");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        scheduler.scheduleWithFixedDelay(task, 0, PERIOD, TimeUnit.MILLISECONDS);
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