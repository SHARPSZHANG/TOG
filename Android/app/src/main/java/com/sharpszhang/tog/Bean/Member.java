package com.sharpszhang.tog.Bean;

public class Member {

    private Long id;

    private Long userId;

    private Long clubId;

    private String gmtCreate;

    private String gmtDelete;

    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGmtDelete() {
        return gmtDelete;
    }

    public void setGmtDelete(String gmtDelete) {
        this.gmtDelete = gmtDelete;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Member() {
    }

    public Member(Long id, Long userId, Long clubId, String gmtCreate, String gmtDelete, String position, int isDelete) {
        this.id = id;
        this.userId = userId;
        this.clubId = clubId;
        this.gmtCreate = gmtCreate;
        this.gmtDelete = gmtDelete;
        this.position = position;
        this.isDelete = isDelete;
    }

    private int isDelete;
}
