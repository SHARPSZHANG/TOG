package com.sharpszhang.tog.Bean;


public class NoticeVo extends Notice {

    private String clubName;

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

    private String userName;

    public NoticeVo() {
    }
}
