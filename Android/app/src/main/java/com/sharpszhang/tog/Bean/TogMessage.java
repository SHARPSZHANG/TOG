package com.sharpszhang.tog.Bean;


public class TogMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    private Long id;

    /** 消息标题 */
    private String title;

    /** 消息内容 */
    private String content;

    /** 送达用户id */
    private Long userId;

    /** 消息状态（0：未查看 1：已查看） */
    private Integer status;

    /** 是否删除（0：未删除 1：已删除） */
    private Integer isDelete;

    /** 创建时间 */
    private String gmtCreate;

    /** 查看时间 */
    private String gmtModifiel;

    private int type;

    private Long sendId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
    public void setGmtCreate(String gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtCreate()
    {
        return gmtCreate;
    }
    public void setGmtModifiel(String gmtModifiel)
    {
        this.gmtModifiel = gmtModifiel;
    }

    public String getGmtModifiel()
    {
        return gmtModifiel;
    }

}
