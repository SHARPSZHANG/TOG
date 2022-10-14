package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Association;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public interface IAssociationService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Association selectAssociationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param association 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Association> selectAssociationList(Association association);

    /**
     * 新增【请填写功能名称】
     * 
     * @param association 【请填写功能名称】
     * @return 结果
     */
    public int insertAssociation(Association association);

    /**
     * 修改【请填写功能名称】
     * 
     * @param association 【请填写功能名称】
     * @return 结果
     */
    public int updateAssociation(Association association);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAssociationByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAssociationById(Long id);
}
