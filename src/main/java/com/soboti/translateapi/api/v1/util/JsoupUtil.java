package com.soboti.translateapi.api.v1.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupUtil {
    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static Connection getConnection(String url) throws IOException {
        return Jsoup.connect(url);
    }

    /**
     * @param connection
     * @param selector
     * @return
     */
    public static Elements getJsoupElements(Connection connection, String selector) {
        Elements elements = null;
        try {
            elements = connection.get().select(selector);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }
}
