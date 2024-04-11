package com.sparta.nish.restassured.tests;

import com.sparta.nish.restassured.tests.pojos.Comment;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

// This class is used to test GitHub commit comments
public class GitHubCommitCommentsTest {
    public static final String BASE_URI = ApiConfig.getBaseUri();
    public static final String REPO_PATH = ApiConfig.getCommonBasePath();
    public static final String TOKEN = ApiConfig.getToken();

    private ValidatableResponse setupRequest(String path, Map<String, Object> pathParameters){
        return  given()
                .baseUri(BASE_URI)
                .basePath(REPO_PATH + path)
                .headers(Map.of(
                        "Accept", "application/vnd.github+json",
                        "Authorization", "Bearer " + TOKEN,
                        "X-GitHub-Api-Version", "2022-11-28"
                ))
                .pathParams(pathParameters)
                .when()
                //.log().all()
                .get()
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON);
    }



    @Test
    @DisplayName("Get all repository comments returns 1 comment")
        // This test method checks if the API returns exactly one comment for the repository
    void getAllRepositoryComments_Returns1Comment() {
        // The number of comments returned by the API
        Comment[] comments = setupRequest( "/comments", Map.of("owner", "Nishman89", "repo", "TestNish"))
                            .extract()
                                    .as(Comment[].class);

        // Assert that the number of comments is 1
        MatcherAssert.assertThat(comments.length, Matchers.is(1));
    }

    @Test
    @DisplayName("Get comment with a specific Id returns a comment with that Id")
    void getCommentWithId_ReturnsThatComment() {
        var comment = setupRequest("/comments/{comment_id}", Map.of(
                "owner", "Nishman89",
                "repo", "TestNish",
                "comment_id", 140752584

        ))

                        .extract()
                                .as(Comment.class);

        MatcherAssert.assertThat(comment.getId(), Matchers.is(140752584));
    }

    @Test
    @DisplayName("Comment createdAt date/time is in the past")
    void getCommentWithId_ReturnsCommentWithCreatedAtDateTimeInThePast() {
        var comment = setupRequest("/comments/{comment_id}", Map.of(
                "owner", "Nishman89",
                "repo", "TestNish",
                "comment_id", 140752584

        ))

                        .extract()
                        .as(Comment.class);

        MatcherAssert.assertThat(comment.createdDateInThePast(), Matchers.is(true));
    }
}

