package com.ruoyi.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.ClubMember;

import java.util.Date;

public class ClubMemberVo  extends ClubMember {

//    private Long id;
//
//    private Long userId;
//
//    private Long clubId;
//
//
//    private Integer isDelete;
//
//    private String position;
//
//    private String reason;
//
    private String clubName;

    private String userName;
//
//    private Integer state;
//    private String qqNumber;
//
//
//    private String hobby;
//
//
//    private String speciality;
//
//    private String apply;

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
