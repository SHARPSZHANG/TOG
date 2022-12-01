package com.sharpszhang.tog.Bean;

public class Club {

    private Long id;

    private String clubName;

    private String clubDescription;

    private String clubDetails;

    private String clubIcon;

    private String gmtCreate;

    private int isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public String getClubDetails() {
        return clubDetails;
    }

    public void setClubDetails(String clubDetails) {
        this.clubDetails = clubDetails;
    }

    public String getClubIcon() {
        return clubIcon;
    }

    public void setClubIcon(String clubIcon) {
        this.clubIcon = clubIcon;
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

    public Club() {
    }

    public Club(Long id, String clubName, String clubDescription, String clubDetails, String clubIcon, String gmtCreate, int isDelete) {
        this.id = id;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubDetails = clubDetails;
        this.clubIcon = clubIcon;
        this.gmtCreate = gmtCreate;
        this.isDelete = isDelete;
    }
}
