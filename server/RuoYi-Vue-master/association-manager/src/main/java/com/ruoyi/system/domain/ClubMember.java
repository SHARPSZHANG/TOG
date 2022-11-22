package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

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
    @Excel(name = "用户id")
    private Long userId;

    /** 社团id */
    @Excel(name = "社团id")
    private Long clubId;

    /** 加入社团时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入社团时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 离开社团时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离开社团时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtDelete;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 退出原因 */
    @Excel(name = "退出原因")
    private String reason;

    /** 是否删除（1：删除 0：未删除） */
    @Excel(name = "是否删除", readConverterExp = "1=：删除,0=：未删除")
    private Integer isDelete;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** QQ号 */
    @Excel(name = "QQ号")
    private String qqNumber;

    /** 爱好 */
    @Excel(name = "爱好")
    private String hobby;

    /** 特长 */
    @Excel(name = "特长")
    private String speciality;

    /** 申请理由 */
    @Excel(name = "申请理由")
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("clubId", getClubId())
            .append("gmtCreate", getGmtCreate())
            .append("gmtDelete", getGmtDelete())
            .append("position", getPosition())
            .append("isDelete", getIsDelete())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
