package com.sparta.shs.simpleclient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class PostRequest {
    public static void main(String[] args) {
        //THE REQUEST
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes"))
                .POST(HttpRequest.BodyPublishers.ofString("{\"geolocations\" : [{\"longitude\":  0.629834723775309,\"latitude\": 51.7923246977375}, {\"longitude\": -2.49690382054704,\"latitude\": 53.5351312861402,\"radius\": 1000,\"limit\": 5}]}"))
                .headers("Content-Type", "application/json")
                .build();

        System.out.println(httpRequest);


        //SENDING THE REQUEST USING httpClient

        HttpResponse<String> httpResponse = null;
        // handling the response as a JSON string - could be a file, or an input stream, or we discard it
        try {
            httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());

        Map<String, List<String>> headers = httpResponse.headers().map();

        for (var item: headers.entrySet()){
            System.out.print(item.getKey() + ": ");
            System.out.println(item.getValue());
        }

        // examine the body using JSON simple
        System.out.println("Look at elements of the response using JSON Simple");
        JSONParser jsonParser = new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject)jsonParser.parse(httpResponse.body()); // You do a cast to change from one object to another
            System.out.println(jsonObject.get("status"));

            JSONArray results = (JSONArray) jsonObject.get("result");

            for (var item : results) {
                JSONObject arrayItem = (JSONObject) item;
                //JSONObject resultChild = (JSONObject) arrayItem.get("result");
                JSONArray postcodes = (JSONArray) arrayItem.get("result");

                for(var postcode: postcodes){
                    System.out.println(postcode);
                }
//                System.out.println("Primary Care Trust for postcode " + arrayItem.get("query") + " is " + resultChild.get("primary_care_trust"));
//
//                JSONObject codes = (JSONObject) resultChild.get("codes");
//                System.out.println("Codes are: " +codes.entrySet().toString());
//                System.out.println("Codes for Parish is :" + (codes.get("parish")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
