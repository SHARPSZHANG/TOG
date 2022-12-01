package com.ruoyi.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.ClubMember;

import java.util.Date;

public class ClubMemberVo  extends ClubMember {

    private Long id;

    private Long userId;

    private Long clubId;

    private Integer isDelete;

    private String position;

    private String reason;

    private String clubName;

    private String userName;

    private Integer state;

    private String qqNumber;


    private String hobby;


    private String speciality;

    private String apply;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getClubId() {
        return clubId;
    }

    @Override
    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    @Override
    public Integer getIsDelete() {
        return isDelete;
    }

    @Override
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
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

    @Override
    public Integer getState() {
        return state;
    }

    @Override
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String getQqNumber() {
        return qqNumber;
    }

    @Override
    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    @Override
    public String getHobby() {
        return hobby;
    }

    @Override
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String getSpeciality() {
        return speciality;
    }

    @Override
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String getApply() {
        return apply;
    }

    @Override
    public void setApply(String apply) {
        this.apply = apply;
    }
}
