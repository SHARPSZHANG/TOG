package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AssociationMapper;
import com.ruoyi.system.domain.Association;
import com.ruoyi.system.service.IAssociationService;

/**
 * 社团Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-11
 */
@Service
public class AssociationServiceImpl implements IAssociationService
{
    @Autowired
    private AssociationMapper associationMapper;

    /**
     * 查询社团
     *
     * @param id 社团主键
     * @return 社团
     */
    @Override
    public Association selectAssociationById(Long id)
    {
        return associationMapper.selectAssociationById(id);
    }

    /**
     * 查询社团列表
     *
     * @param association 社团
     * @return 社团
     */
    @Override
    public List<Association> selectAssociationList(Association association)
    {
        return associationMapper.selectAssociationList(association);
    }

    /**
     * 新增社团
     *
     * @param association 社团
     * @return 结果
     */
    @Override
    public int insertAssociation(Association association)
    {
        association.setType("未审核");
        association.setCreateTime(DateUtils.getNowDate());
        return associationMapper.insertAssociation(association);
    }

    /**
     * 修改社团
     *
     * @param association 社团
     * @return 结果
     */
    @Override
    public int updateAssociation(Association association)
    {
        association.setUpdateTime(DateUtils.getNowDate());
        return associationMapper.updateAssociation(association);
    }

    /**
     * 批量删除社团
     *
     * @param ids 需要删除的社团主键
     * @return 结果
     */
    @Override
    public int deleteAssociationByIds(Long[] ids)
    {
        return associationMapper.deleteAssociationByIds(ids);
    }

    /**
     * 删除社团信息
     *
     * @param id 社团主键
     * @return 结果
     */
    @Override
    public int deleteAssociationById(Long id)
    {
        return associationMapper.deleteAssociationById(id);
    }
}
