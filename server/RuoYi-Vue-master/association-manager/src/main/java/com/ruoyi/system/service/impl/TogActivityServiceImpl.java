package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TogActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TogActivityMapper;

import com.ruoyi.system.service.ITogActivityService;

/**
 * 活动Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-03
 */
@Service
public class TogActivityServiceImpl implements ITogActivityService
{
    @Autowired
    private TogActivityMapper togActivityMapper;

    /**
     * 查询活动
     *
     * @param id 活动主键
     * @return 活动
     */
    @Override
    public TogActivity selectTogActivityById(Long id)
    {
        return togActivityMapper.selectTogActivityById(id);
    }

    /**
     * 查询活动列表
     *
     * @param togActivity 活动
     * @return 活动
     */
    @Override
    public List<TogActivity> selectTogActivityList(TogActivity togActivity)
    {
        return togActivityMapper.selectTogActivityList(togActivity);
    }

    /**
     * 新增活动
     *
     * @param togActivity 活动
     * @return 结果
     */
    @Override
    public int insertTogActivity(TogActivity togActivity)
    {
        togActivity.setCreateTime(DateUtils.getNowDate());
        return togActivityMapper.insertTogActivity(togActivity);
    }

    /**
     * 修改活动
     *
     * @param togActivity 活动
     * @return 结果
     */
    @Override
    public int updateTogActivity(TogActivity togActivity)
    {
        togActivity.setUpdateTime(DateUtils.getNowDate());
        return togActivityMapper.updateTogActivity(togActivity);
    }

    /**
     * 批量删除活动
     *
     * @param ids 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteTogActivityByIds(Long[] ids)
    {
        return togActivityMapper.deleteTogActivityByIds(ids);
    }

    /**
     * 删除活动信息
     *
     * @param id 活动主键
     * @return 结果
     */
    @Override
    public int deleteTogActivityById(Long id)
    {
        return togActivityMapper.deleteTogActivityById(id);
    }
}
