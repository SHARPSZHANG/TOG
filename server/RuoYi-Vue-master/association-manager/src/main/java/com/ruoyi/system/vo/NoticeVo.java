package com.ruoyi.system.vo;

import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.Notice;

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



    public NoticeVo() {
    }
}
