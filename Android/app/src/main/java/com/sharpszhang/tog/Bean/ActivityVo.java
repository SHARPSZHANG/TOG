package com.sharpszhang.tog.Bean;


public class ActivityVo extends Activity{

    private String clubName;

    private String userName;

    public ActivityVo(String clubName, String userName) {
        this.clubName = clubName;
        this.userName = userName;
    }

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
}
