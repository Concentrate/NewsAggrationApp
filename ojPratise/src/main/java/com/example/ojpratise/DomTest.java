package com.example.ojpratise;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by liudeyu on 2018/2/2.
 */

public class DomTest {
    static String url = "https://m.weibo.cn/u/2855893887?uid=2855893887&luicode=20000061&lfid=4202958298714873";

    public static void main(String[] argv) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            String tmp = okHttpClient.newCall(request).execute().body().string();
            Document document = new Document(tmp);
            document.removeClass("aside");
            System.out.println(document.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getResponse(String url) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        int readCount = 0;
        byte[] res = new byte[inputStream.available()];
        inputStream.read(res);
        return new String(res, "UTF8");
    }
}
