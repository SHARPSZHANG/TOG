package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Association;

/**
 * 社团Service接口
 *
 * @author ruoyi
 * @date 2022-10-11
 */
public interface IAssociationService
{
    /**
     * 查询社团
     *
     * @param id 社团主键
     * @return 社团
     */
    public Association selectAssociationById(Long id);

    /**
     * 查询社团列表
     *
     * @param association 社团
     * @return 社团集合
     */
    public List<Association> selectAssociationList(Association association);

    /**
     * 新增社团
     *
     * @param association 社团
     * @return 结果
     */
    public int insertAssociation(Association association);

    /**
     * 修改社团
     *
     * @param association 社团
     * @return 结果
     */
    public int updateAssociation(Association association);

    /**
     * 批量删除社团
     *
     * @param ids 需要删除的社团主键集合
     * @return 结果
     */
    public int deleteAssociationByIds(Long[] ids);

    /**
     * 删除社团信息
     *
     * @param id 社团主键
     * @return 结果
     */
    public int deleteAssociationById(Long id);
}
