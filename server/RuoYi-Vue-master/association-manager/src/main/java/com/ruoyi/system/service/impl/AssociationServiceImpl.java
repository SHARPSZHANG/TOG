package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AssociationMapper;
import com.ruoyi.system.domain.Association;
import com.ruoyi.system.service.IAssociationService;

/**
 * 【请填写功能名称】Service业务层处理
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
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Association selectAssociationById(Long id)
    {
        return associationMapper.selectAssociationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param association 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Association> selectAssociationList(Association association)
    {
        return associationMapper.selectAssociationList(association);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param association 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAssociation(Association association)
    {
        association.setCreateTime(DateUtils.getNowDate());
        return associationMapper.insertAssociation(association);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param association 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAssociation(Association association)
    {
        association.setUpdateTime(DateUtils.getNowDate());
        return associationMapper.updateAssociation(association);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAssociationByIds(Long[] ids)
    {
        return associationMapper.deleteAssociationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAssociationById(Long id)
    {
        return associationMapper.deleteAssociationById(id);
    }
}
