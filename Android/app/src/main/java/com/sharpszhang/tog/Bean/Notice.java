package com.sharpszhang.tog.Bean;

public class Notice {

    private Long id;

    private String title;

    private String content;

    private String image;

    private Long userId;

    private Long clubId;

    private String gmtCreate;

    private int isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Notice() {
    }

    public Notice(Long id, String title, String content, String image, Long userId, Long clubId, String gmtCreate, int isDelete) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.userId = userId;
        this.clubId = clubId;
        this.gmtCreate = gmtCreate;
        this.isDelete = isDelete;
    }
}
