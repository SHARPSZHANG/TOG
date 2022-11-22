package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.vo.ClubMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClubMemberMapper;
import com.ruoyi.system.domain.ClubMember;
import com.ruoyi.system.service.IClubMemberService;

/**
 * 社团成员Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Service
public class ClubMemberServiceImpl implements IClubMemberService 
{
    @Autowired
    private ClubMemberMapper clubMemberMapper;

    /**
     * 查询社团成员
     * 
     * @param id 社团成员主键
     * @return 社团成员
     */
    @Override
    public ClubMemberVo selectClubMemberById(Long id)
    {
        return clubMemberMapper.selectClubMemberById(id);
    }

    @Override
    public void pass(Long id, String userName) {
        ClubMember clubMember = clubMemberMapper.selectClubMemberById(id);
        clubMember.setState(1);
        clubMember.setUpdateBy(userName);
        clubMember.setUpdateTime(DateUtils.getNowDate());
        clubMemberMapper.insertClubMember(clubMember);
    }

    /**
     * 查询社团成员列表
     * 
     * @param clubMember 社团成员
     * @return 社团成员
     */
    @Override
    public List<ClubMemberVo> selectClubMemberList(ClubMember clubMember)
    {
        return clubMemberMapper.selectClubMemberList(clubMember);
    }

    /**
     * 新增社团成员
     * 
     * @param clubMember 社团成员
     * @return 结果
     */
    @Override
    public int insertClubMember(ClubMember clubMember)
    {
        clubMember.setCreateTime(DateUtils.getNowDate());
        return clubMemberMapper.insertClubMember(clubMember);
    }

    /**
     * 修改社团成员
     * 
     * @param clubMember 社团成员
     * @return 结果
     */
    @Override
    public int updateClubMember(ClubMember clubMember)
    {
        clubMember.setUpdateTime(DateUtils.getNowDate());
        return clubMemberMapper.updateClubMember(clubMember);
    }

    /**
     * 批量删除社团成员
     * 
     * @param ids 需要删除的社团成员主键
     * @return 结果
     */
    @Override
    public int deleteClubMemberByIds(Long[] ids)
    {
        return clubMemberMapper.deleteClubMemberByIds(ids);
    }

    /**
     * 删除社团成员信息
     * 
     * @param id 社团成员主键
     * @return 结果
     */
    @Override
    public int deleteClubMemberById(Long id)
    {
        return clubMemberMapper.deleteClubMemberById(id);
    }
}
