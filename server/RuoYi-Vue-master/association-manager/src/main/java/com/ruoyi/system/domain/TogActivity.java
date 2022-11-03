package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 tog_activity
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public class TogActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String place;

    /** 活动人数 */
    @Excel(name = "活动人数")
    private Long peopleNum;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    /** 社团id */
    @Excel(name = "社团id")
    private Long associationId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }
    public void setPeopleNum(Long peopleNum) 
    {
        this.peopleNum = peopleNum;
    }

    public Long getPeopleNum() 
    {
        return peopleNum;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setAssociationId(Long associationId) 
    {
        this.associationId = associationId;
    }

    public Long getAssociationId() 
    {
        return associationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("activityName", getActivityName())
            .append("place", getPlace())
            .append("peopleNum", getPeopleNum())
            .append("content", getContent())
            .append("associationId", getAssociationId())
            .toString();
    }
}
