package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TogActivity;

/**
 * 活动Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ITogActivityService 
{
    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    public TogActivity selectTogActivityById(Long id);

    /**
     * 查询活动列表
     * 
     * @param togActivity 活动
     * @return 活动集合
     */
    public List<TogActivity> selectTogActivityList(TogActivity togActivity);

    /**
     * 新增活动
     * 
     * @param togActivity 活动
     * @return 结果
     */
    public int insertTogActivity(TogActivity togActivity);

    /**
     * 修改活动
     * 
     * @param togActivity 活动
     * @return 结果
     */
    public int updateTogActivity(TogActivity togActivity);

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键集合
     * @return 结果
     */
    public int deleteTogActivityByIds(Long[] ids);

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    public int deleteTogActivityById(Long id);
}
