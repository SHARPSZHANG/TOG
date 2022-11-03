package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TogNoticeMapper;
import com.ruoyi.system.domain.TogNotice;
import com.ruoyi.system.service.ITogNoticeService;

/**
 * 公告Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class TogNoticeServiceImpl implements ITogNoticeService 
{
    @Autowired
    private TogNoticeMapper togNoticeMapper;

    /**
     * 查询公告
     * 
     * @param id 公告主键
     * @return 公告
     */
    @Override
    public TogNotice selectTogNoticeById(Long id)
    {
        return togNoticeMapper.selectTogNoticeById(id);
    }

    /**
     * 查询公告列表
     * 
     * @param togNotice 公告
     * @return 公告
     */
    @Override
    public List<TogNotice> selectTogNoticeList(TogNotice togNotice)
    {
        return togNoticeMapper.selectTogNoticeList(togNotice);
    }

    /**
     * 新增公告
     * 
     * @param togNotice 公告
     * @return 结果
     */
    @Override
    public int insertTogNotice(TogNotice togNotice)
    {
        togNotice.setCreateTime(DateUtils.getNowDate());
        return togNoticeMapper.insertTogNotice(togNotice);
    }

    /**
     * 修改公告
     * 
     * @param togNotice 公告
     * @return 结果
     */
    @Override
    public int updateTogNotice(TogNotice togNotice)
    {
        togNotice.setUpdateTime(DateUtils.getNowDate());
        return togNoticeMapper.updateTogNotice(togNotice);
    }

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的公告主键
     * @return 结果
     */
    @Override
    public int deleteTogNoticeByIds(Long[] ids)
    {
        return togNoticeMapper.deleteTogNoticeByIds(ids);
    }

    /**
     * 删除公告信息
     * 
     * @param id 公告主键
     * @return 结果
     */
    @Override
    public int deleteTogNoticeById(Long id)
    {
        return togNoticeMapper.deleteTogNoticeById(id);
    }
}
