package com.sharpszhang.tog.Bean;


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
    private String clubName;

    /** 社团说明 */
    private String clubDesc;

    /** 社团详情 */
    private String clubDetail;

    /** 创建时间 */
    private String gmtCreate;

    /** 是否删除（1：删除 0：未删除） */
    private Integer isDelete;

    /** 社团图标url */
    private String clubIcon;

    /** 社团图标url */
    private String theacher;

    /** 社团图标url */
    private String mobilePhone;

    /** 社团图标url */
    private String qqNumber;

    /** 是否删除（1：删除 0：未删除） */
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
    public void setGmtCreate(String gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtCreate()
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

}
