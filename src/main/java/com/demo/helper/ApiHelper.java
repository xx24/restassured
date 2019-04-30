package com.demo.helper;

import io.restassured.response.Response;

import java.util.Map;

public class ApiHelper extends BaseApiHelper {
    /**
     * @param url：请求的url
     * @param params：请求的参数
     * @return
     */
    public static Response get(String url, Map params) {
        return baseGiven().params(params).when().get(url);
    }

    public static Response post(String url, Map params) {
        return baseGiven().body(params).when().post(url);
    }

    public static Response put(String url, Map params) {
        return baseGiven().body(params).when().put(url);
    }

    public static Response delete(String url, Long id) {
        return baseGiven().body(id).when().delete(url);
    }

}
