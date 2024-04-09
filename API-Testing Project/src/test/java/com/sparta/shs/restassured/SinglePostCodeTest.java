package com.sparta.shs.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinglePostCodeTest {
    private static Response response;
    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given()
                    .baseUri("https://api.postcodes.io")
                    .basePath("/postcodes")
                    .header("Accept", "text/json")
                .when()
                    .log().all()
                    .get("/EC2Y5AS")
                .thenReturn();

    }

    @Test
    @DisplayName("status code 200 returned")
    public void testStatusCode200(){

        MatcherAssert.assertThat(response.statusCode(), Is.is(200));

    }

    @Test
    @DisplayName("The server name in the headers is cloudflare")
    void testServerNameHeader(){
        MatcherAssert.assertThat(response.header("Server"), Is.is("cloudflare"));
    }

    @Test
    @DisplayName("Correct postcode returned in response")
    void testCorrectPostcodeReturnedInResponse(){
        MatcherAssert.assertThat(response.jsonPath().getString("result.postcode"), Is.is("EC2Y 5AS"));
    }

    @Test
    @DisplayName("Correct primary-care-trust returned in response is City and Hackney Teaching")
    void testCorrectPrimarycareTrustReturnsResponse(){
        MatcherAssert.assertThat(response.jsonPath().getString("result.primary_care_trust"), Is.is("City and Hackney Teaching"));

    }
    @Test
    @DisplayName("Correct number of count returned is 14")
    void testTotalNumOfCodesReturned(){
        MatcherAssert.assertThat(response.jsonPath().getMap("result.codes").size(), Is.is(14));
    }

}
