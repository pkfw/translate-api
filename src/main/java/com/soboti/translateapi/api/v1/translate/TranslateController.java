package com.soboti.translateapi.api.v1.translate;

import com.soboti.translateapi.api.v1.util.JsoupUtil;
import com.soboti.translateapi.api.v1.util.UrlUtil;
import org.jsoup.Connection;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@RestController
public class TranslateController {

    @GetMapping("/translate-app/api/v1/translate/jsoup")
    public HashMap<String, String> getTestJsoup(String q) {
        // result type is HashMap<String, String> :: json
        HashMap<String, String> result = new HashMap<>();
        result.put("request", q);

        // parameter null check
        if (q == null) {
            result.put("response", "translate is null");
            return result;
        }
        String url = new UrlUtil().getTranslateUrl("google", q);

        // jsoup parse
        JsoupUtil jsoupUtil = new JsoupUtil();
        Connection connection = null;
        try {
            connection = jsoupUtil.getConnection(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = null;

        // ask google translate
        String selector = "[data-entityname=\"Google Translate\"] [lang=\"ko\"]";

        // ask naver papago
//        String selector = "#txtTarget";

        String searchResult = null;
        try {
            elements = jsoupUtil.getJsoupElements(connection, selector);
            searchResult = elements.eachText().getFirst();
        } catch (NoSuchElementException e) {
            searchResult = "no such element";
        } catch (Exception e) {
            searchResult = e.toString();
        }

        result.put("response", searchResult);
        return result;
    }
}
