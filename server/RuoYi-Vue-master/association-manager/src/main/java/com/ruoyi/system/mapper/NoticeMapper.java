package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Notice;
import com.ruoyi.system.vo.NoticeVo;

/**
 * 公告Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface NoticeMapper 
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
     * 删除公告
     * 
     * @param id 公告主键
     * @return 结果
     */
    public int deleteNoticeById(Long id);

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] ids);

    List<NoticeVo> findNoticeByUserId(Long userId);

    NoticeVo selectNoticeVoById(Long id);

    List<NoticeVo> findNoticeByClubId(Long clubId);
}
