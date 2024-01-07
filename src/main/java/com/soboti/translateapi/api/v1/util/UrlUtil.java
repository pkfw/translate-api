package com.soboti.translateapi.api.v1.util;

public class UrlUtil {
    /**
     * @param domain
     * @param query
     * @return
     *  google: https://www.google.com/search?q=cat+to+korean
     *  navar: https://papago.naver.com/?sk=en&tk=ko&hn=1&st=cat
     */
    public String getTranslateUrl(String domain, String query) {
        String scheme = "https://";
        String host = null;
        String path = "/";
        String formattedQuery = null;

        if (domain.equals("google")) {
            host = "www.google.com";
            path = "/search";
            formattedQuery = String.format("?q=%s+to+korean", query);
        } else if (domain.equals("naver")) {
            host = "papago.naver.com";
            formattedQuery = String.format("?sk=en&tk=ko&hn=1&st=%s", query);
        } else {
            return null;
        }

        String url = String.format("%s%s%s%s", scheme, host, path, formattedQuery);
        return url;
    }
}
