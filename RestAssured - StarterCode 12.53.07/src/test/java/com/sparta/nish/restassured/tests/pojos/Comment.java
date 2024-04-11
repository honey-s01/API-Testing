
package com.sparta.nish.restassured.tests.pojos;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)


public class Comment {

    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("user")
    private User user;
    @JsonProperty("position")
    private Object position;
    @JsonProperty("line")
    private Object line;
    @JsonProperty("path")
    private Object path;
    @JsonProperty("commit_id")
    private String commitId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("author_association")
    private String authorAssociation;
    @JsonProperty("body")
    private String body;
    @JsonProperty("reactions")
    private Reactions reactions;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("position")
    public Object getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Object position) {
        this.position = position;
    }

    @JsonProperty("line")
    public Object getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(Object line) {
        this.line = line;
    }

    @JsonProperty("path")
    public Object getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(Object path) {
        this.path = path;
    }

    @JsonProperty("commit_id")
    public String getCommitId() {
        return commitId;
    }

    @JsonProperty("commit_id")
    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("author_association")
    public String getAuthorAssociation() {
        return authorAssociation;
    }

    @JsonProperty("author_association")
    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("reactions")
    public Reactions getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }

    public boolean createdDateInThePast(){
        LocalDateTime createDate = LocalDateTime.parse(createdAt.substring(0, 16));
        return createDate.isBefore(LocalDateTime.now());
    }

}
