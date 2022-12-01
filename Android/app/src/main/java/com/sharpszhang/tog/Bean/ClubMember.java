package com.sharpszhang.tog.Bean;

import java.util.Date;

/**
 * 社团成员对象 club_member
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public class ClubMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 社团id */
    private Long clubId;

    /** 加入社团时间 */
    private Date gmtCreate;

    /** 离开社团时间 */
    private Date gmtDelete;

    /** 职位 */
    private String position;

    /** 退出原因 */
    private String reason;

    /** 是否删除（1：删除 0：未删除） */
    private Integer isDelete;

    /** 状态 */
    private Integer state;

    /** QQ号 */
    private String qqNumber;

    /** 爱好 */
    private String hobby;

    /** 特长 */
    private String speciality;

    /** 申请理由 */
    private String apply;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setClubId(Long clubId) 
    {
        this.clubId = clubId;
    }

    public Long getClubId() 
    {
        return clubId;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setGmtDelete(Date gmtDelete) 
    {
        this.gmtDelete = gmtDelete;
    }

    public Date getGmtDelete() 
    {
        return gmtDelete;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }
}
