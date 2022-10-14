package com.sharpszhang.tog.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostBean {
    public int postId;
    public String postUser;
    public String postContent;
    public Date postDate;
//    public int pv;

    public Boolean like = false;

    public PostBean() {
    }

    public PostBean(int postId, String postUser, String postContent, Date postDate) throws ParseException {
        this.postId = postId;
        this.postUser = postUser;
        this.postContent = postContent;
        this.postDate = postDate;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public int getPostId() {
        return postId;
    }

    public String getPostUser() {
        return postUser;
    }

    public String getPostContent() {
        return postContent;
    }

    public Date getPostDate() {
        return postDate;
    }

    public Boolean getLike() {
        return like;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostDate(String postDate) throws ParseException {
        this.postDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(postDate);
    }
}
