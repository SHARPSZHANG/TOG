package com.sharpszhang.tog.Bean;

public class NoticeVo extends Notice {


    private String clubName;

    private String userName;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public NoticeVo(String clubName, String userName) {
        this.clubName = clubName;
        this.userName = userName;
    }

    public NoticeVo(Long id, String title, String content, String image, Long userId, Long clubId, String gmtCreate, int isDelete, String clubName, String userName) {
        super(id, title, content, image, userId, clubId, gmtCreate, isDelete);
        this.clubName = clubName;
        this.userName = userName;
    }
}
