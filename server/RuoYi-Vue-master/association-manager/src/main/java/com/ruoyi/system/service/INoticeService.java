package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Notice;
import com.ruoyi.system.vo.NoticeVo;

/**
 * 公告Service接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface INoticeService 
{
    /**
     * 查询公告
     * 
     * @param id 公告主键
     * @return 公告
     */
    public Notice selectNoticeById(Long id);

    /**
     * 查询公告列表
     * 
     * @param notice 公告
     * @return 公告集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的公告主键集合
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] ids);

    /**
     * 删除公告信息
     * 
     * @param id 公告主键
     * @return 结果
     */
    public int deleteNoticeById(Long id);

    Boolean getPermissionByUserId(Long userId, Long noticeId);

    List<NoticeVo> findNoticeByUserId(Long userId);

    NoticeVo selectNoticeVoById(Long id);

    List<NoticeVo> findNoticeByClubId(Long clubId);
}
