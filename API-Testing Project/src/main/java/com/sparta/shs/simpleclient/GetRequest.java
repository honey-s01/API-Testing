package com.sparta.shs.simpleclient;

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

public class GetRequest {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/ec2y5as"))
                .build();

        HttpResponse<String> httpResponse = null;
        // handling the response as a JSON string - could be a file, or an input stream, or we discardnit
        try {
            httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException  | InterruptedException e) {
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

            JSONObject result = (JSONObject) jsonObject.get("result");
            System.out.println("primary care trust: " + result.get("primary_care_trust"));

            JSONObject codes = (JSONObject) result.get("codes");
            System.out.println("Codes are: " + codes.entrySet().toString());

        }catch(ParseException e){
            e.printStackTrace();

        }










    }
}
