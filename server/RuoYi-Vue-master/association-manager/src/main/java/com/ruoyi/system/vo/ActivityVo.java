package com.ruoyi.system.vo;

import com.ruoyi.system.domain.Activity;

public class ActivityVo extends Activity {

    private String clubName;

    public ActivityVo() {
    }

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

    public ActivityVo(String clubName, String userName) {
        this.clubName = clubName;
        this.userName = userName;
    }
}
