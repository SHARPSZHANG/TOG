package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TogNotice;

/**
 * 公告Service接口
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
public interface ITogNoticeService 
{
    /**
     * 查询公告
     * 
     * @param id 公告主键
     * @return 公告
     */
    public TogNotice selectTogNoticeById(Long id);

    /**
     * 查询公告列表
     * 
     * @param togNotice 公告
     * @return 公告集合
     */
    public List<TogNotice> selectTogNoticeList(TogNotice togNotice);

    /**
     * 新增公告
     * 
     * @param togNotice 公告
     * @return 结果
     */
    public int insertTogNotice(TogNotice togNotice);

    /**
     * 修改公告
     * 
     * @param togNotice 公告
     * @return 结果
     */
    public int updateTogNotice(TogNotice togNotice);

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的公告主键集合
     * @return 结果
     */
    public int deleteTogNoticeByIds(Long[] ids);

    /**
     * 删除公告信息
     * 
     * @param id 公告主键
     * @return 结果
     */
    public int deleteTogNoticeById(Long id);
}
