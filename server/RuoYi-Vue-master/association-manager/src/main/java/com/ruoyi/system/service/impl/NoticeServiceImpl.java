package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.service.IClubMemberService;
import com.ruoyi.system.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NoticeMapper;
import com.ruoyi.system.domain.Notice;
import com.ruoyi.system.service.INoticeService;

/**
 * 公告Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Service
public class NoticeServiceImpl implements INoticeService 
{
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private IClubMemberService iClubMemberService;

    /**
     * 查询公告
     * 
     * @param id 公告主键
     * @return 公告
     */
    @Override
    public Notice selectNoticeById(Long id)
    {
        return noticeMapper.selectNoticeById(id);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告
     * @return 公告
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        notice.setUpdateTime(DateUtils.getNowDate());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] ids)
    {
        return noticeMapper.deleteNoticeByIds(ids);
    }

    /**
     * 删除公告信息
     * 
     * @param id 公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long id)
    {
        return noticeMapper.deleteNoticeById(id);
    }

    @Override
    public Boolean getPermissionByUserId(Long userId, Long noticeId) {
        Notice notice = selectNoticeById(noticeId);
        Boolean permissionByUserId = iClubMemberService.getPermissionByUserId(userId, notice.getClubId());
        return permissionByUserId;
    }

    @Override
    public List<NoticeVo> findNoticeByUserId(Long userId) {
        return noticeMapper.findNoticeByUserId(userId);
    }

    @Override
    public NoticeVo selectNoticeVoById(Long id) {
        return noticeMapper.selectNoticeVoById(id);
    }

    @Override
    public List<NoticeVo> findNoticeByClubId(Long clubId) {
        return noticeMapper.findNoticeByClubId(clubId);
    }
}
