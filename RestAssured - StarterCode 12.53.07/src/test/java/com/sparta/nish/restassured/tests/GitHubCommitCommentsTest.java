package com.sparta.nish.restassured.tests;

import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

// This class is used to test GitHub commit comments
public class GitHubCommitCommentsTest {
    public static final String BASE_URI = "https://api.github.com";
    public static final String REPO_PATH = "/repos/{owner}/{repo}";



    @Test
    @DisplayName("Get all repository comments returns 1 comment")
        // This test method checks if the API returns exactly one comment for the repository
    void getAllRepositoryComments_Returns1Comment() {
        // The number of comments returned by the API
        Integer numberOfComments =
                    given()
                        // Base URL of the GitHub API
                        .baseUri(BASE_URI)
                        // Path to the comments of a specific repository
                        .basePath(REPO_PATH + "/comments")
                        // Headers required by the GitHub API
                        .headers(Map.of(
                                "Accept", "application/vnd.github+json",
                                "Authorization", "Bearer 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",
                                "X-GitHub-Api-Version", "2022-11-28"
                        ))
                        // Path parameters for the owner and repository name
                        .pathParams(Map.of(
                                "owner", "Nishman89",
                                "repo", "TestNish"
                        ))
                        .when()
                            .log().all()
                            // Send a GET request to the API
                            .get()
                        .then()
                            .log().all()
                            // Assert that the status code is 200
                            .assertThat()
                            .statusCode(200)
                            // Assert that the content type is JSON
                            .assertThat()
                            .contentType(ContentType.JSON)
                            .extract()
                            // Use jsonPath() to parse the JSON response body
                            // The "$" sign represents the root of the JSON document
                            // getList("$") gets a list of all items in the root of the JSON response
                            .jsonPath()
                            .getList("$")
                            .size();

        // Assert that the number of comments is 1
        MatcherAssert.assertThat(numberOfComments, Matchers.is(1));
    }

    @Test
    @DisplayName("Get comment with a specific Id returns a comment with that Id")
    void getCommentWithId_ReturnsThatComment() {
        var commentId =
                given()
                        // Base URL of the GitHub API
                        .baseUri(BASE_URI)
                        // Path to the comments of a specific repository
                        .basePath(REPO_PATH + "/comments/{comment_id}")
                        // Headers required by the GitHub API
                        .headers(Map.of(
                                "Accept", "application/vnd.github+json",
                                "Authorization", "Bearer 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",
                                "X-GitHub-Api-Version", "2022-11-28"
                        ))
                        // Path parameters for the owner and repository name
                        .pathParams(Map.of(
                                "owner", "Nishman89",
                                "repo", "TestNish",
                                "comment_id", 140752584

                        ))
                        .when()
                        .log().all()
                        // Send a GET request to the API
                        .get()
                        .then()
                        .log().all()
                        // Assert that the status code is 200
                        .assertThat()
                        .statusCode(200)
                        // Assert that the content type is JSON
                        .assertThat()
                        .contentType(ContentType.JSON)
                        .extract()
                        // Use jsonPath() to parse the JSON response body
                        // The "$" sign represents the root of the JSON document
                        // getList("$") gets a list of all items in the root of the JSON response
                        .jsonPath()
                        .get("id");

        MatcherAssert.assertThat(commentId, Matchers.is(140752584));
    }
}

