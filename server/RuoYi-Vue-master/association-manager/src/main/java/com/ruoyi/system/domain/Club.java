package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社团对象 club
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public class Club extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 社团id */
    private Long id;

    /** 社团名称 */
    @Excel(name = "社团名称")
    private String clubName;

    /** 社团说明 */
    @Excel(name = "社团说明")
    private String clubDesc;

    /** 社团详情 */
    @Excel(name = "社团详情")
    private String clubDetail;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 是否删除（1：删除 0：未删除） */
    @Excel(name = "是否删除", readConverterExp = "1=：删除,0=：未删除")
    private Integer isDelete;

    /** 社团图标url */
    @Excel(name = "社团图标url")
    private String clubIcon;

    /** 社团图标url */
    @Excel(name = "指导老师")
    private String theacher;

    /** 社团图标url */
    @Excel(name = "电话")
    private String mobilePhone;

    /** 社团图标url */
    @Excel(name = "QQ号")
    private String qqNumber;

    /** 是否删除（1：删除 0：未删除） */
    @Excel(name = "状态", readConverterExp = "1=通过,0=：未通过")
    private Integer state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClubName(String clubName) 
    {
        this.clubName = clubName;
    }

    public String getClubName() 
    {
        return clubName;
    }
    public void setClubDesc(String clubDesc) 
    {
        this.clubDesc = clubDesc;
    }

    public String getClubDesc() 
    {
        return clubDesc;
    }
    public void setClubDetail(String clubDetail) 
    {
        this.clubDetail = clubDetail;
    }

    public String getClubDetail() 
    {
        return clubDetail;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setClubIcon(String clubIcon) 
    {
        this.clubIcon = clubIcon;
    }

    public String getClubIcon() 
    {
        return clubIcon;
    }

    public String getTheacher() {
        return theacher;
    }

    public void setTheacher(String theacher) {
        this.theacher = theacher;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clubName", getClubName())
            .append("clubDesc", getClubDesc())
            .append("clubDetail", getClubDetail())
            .append("gmtCreate", getGmtCreate())
            .append("isDelete", getIsDelete())
            .append("clubIcon", getClubIcon())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
