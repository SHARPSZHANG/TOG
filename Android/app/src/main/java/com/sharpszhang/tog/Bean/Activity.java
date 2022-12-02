package com.sharpszhang.tog.Bean;

/**
 * 活动对象 activity
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long id;

    /** 活动主题 */
    private String title;

    /** 活动内容 */
    private String content;

    /** 活动简介 */
    private String description;

    /** 图片url */
    private String image;

    /** 活动开始时间 */
    private String startTime;

    /** 活动结束时间 */
    private String endTime;

    /** 活动状态（0：未开始 1：进行中 2：已结束） */
    private Integer status;

    /** 是否删除（1：删除状态） */
    private Integer isDelete;

    /** 所属社团id */
    private Long clubId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getStartTime()
    {
        return startTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getEndTime()
    {
        return endTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setClubId(Long clubId) 
    {
        this.clubId = clubId;
    }

    public Long getClubId() 
    {
        return clubId;
    }

}
