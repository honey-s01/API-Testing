package com.sparta.shs.restassured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

import java.util.Map;

//Token key
//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

public class GithubCommitCommentsTest {
    public static final String BASE_URI = "https://api.github.com";

    public static final String REPO_PATH  = "/repos/{owner}/{repo}";


    private Integer commentId;



    @Test
    @DisplayName("Get all repository comments returns 1 comment")
    void getAllRepoComments_Returns1Comment(){

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBasePath(REPO_PATH + "/comments")
                .build();


        Integer numberOfComments =
                        given(requestSpec)

                        .when()
                            .log().all()
                            .get()
                        .then()
                                .spec(getJsonResponseWithStatus(200))
                            .log().all()

                        .extract()
                            .jsonPath()
                            .getList("$")
                            .size();

        MatcherAssert.assertThat(numberOfComments, Matchers.is(1));
    }

    @Test
    @DisplayName("Get request for a comment returns the expected text")
    void getRequest_comment_Returned_With_Expected_Text(){

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBasePath(REPO_PATH + "/comments/{comment_id}")
                .addPathParam("comment_id", 140727896)
                .build();

        String textComment =
                    given(requestSpec)

                    .when()
                        .log().all()
                        .get()
                    .then()
                            .spec(getJsonResponseWithStatus(200))
                            .log().all()

                    .extract()
                    .jsonPath()
                    .getString("body");

        MatcherAssert.assertThat(textComment, Matchers.is("This is Honey's initial commit."));
    }

    @Test
    @DisplayName("Get comment with a specific Id returns a comment with that Id")
    void getCommentWithId_ReturnsThatComment() {


        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBasePath(REPO_PATH + "/comments/{comment_id}")
                .addPathParam("comment_id", 140727896)
                .build();

        Integer commentId =
                        given(requestSpec)

                        .when()
                            .log().all()
                            .get()
                        .then()
                                .spec(getJsonResponseWithStatus(200))
                                .log().all()

                        .extract()
                        .jsonPath()
                        .get("id");

        MatcherAssert.assertThat(commentId, Matchers.is(140727896));
    }

    @Test
    @DisplayName("Get comment with a specific Id returns a comment with that Id - Refactored")
    void getCommentWithId_ReturnsThatComment_Refactored() {

        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBasePath(REPO_PATH + "/comments/{comment_id}")
                .addPathParam("comment_id", 140727896)
                .build();

        ResponseSpecification responseSpec = getJsonResponseWithStatus(200);


        Integer commentId =
                given(requestSpec)
                        .when()
                            .log().all()
                            .get()
                        .then()
                            .spec(getJsonResponseWithStatus(200))
                            .log().all()
                        .extract()
                        .jsonPath()
                        .get("id");

        MatcherAssert.assertThat(commentId, Matchers.is(140727896));
    }

    private static ResponseSpecification getJsonResponseWithStatus(Integer status) {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        return responseSpec;
    }

    private static RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)

                .addHeaders(Map.of(
                        "Accept", "application/vnd.github+json",
                        "Authorization", "Bearer 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",
                        "X-GitHub-Api-Version", "2022-11-28"
                ))
                .addPathParams(Map.of(
                        "owner", "honey-s01",
                        "repo", "testing"

                ));
    }

}
