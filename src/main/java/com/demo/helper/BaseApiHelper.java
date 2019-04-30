package com.demo.helper;

import com.demo.util.Generalconstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiHelper {
    public static Gson gson;

    public static RequestSpecification baseGiven() {
        RestAssured.useRelaxedHTTPSValidation();
        return given().contentType(ContentType.JSON).auth().oauth2(Generalconstant.accessToken);
    }

    public static Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }
    public static Gson gson(GsonBuilder gsonBuilder){
        gson = gsonBuilder.create();
        return gson;
    }



}

