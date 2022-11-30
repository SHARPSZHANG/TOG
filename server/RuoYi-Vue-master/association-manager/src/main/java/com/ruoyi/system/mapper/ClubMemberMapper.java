package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ClubMember;
import com.ruoyi.system.vo.ClubMemberVo;

/**
 * 社团成员Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface ClubMemberMapper 
{
    /**
     * 查询社团成员
     * 
     * @param id 社团成员主键
     * @return 社团成员
     */
    public ClubMemberVo selectClubMemberById(Long id);

    /**
     * 查询社团成员列表
     * 
     * @param clubMember 社团成员
     * @return 社团成员集合
     */
    public List<ClubMemberVo> selectClubMemberList(ClubMember clubMember);

    public List<ClubMember> selectClubMemberList2(ClubMember clubMember);
    /**
     * 新增社团成员
     * 
     * @param clubMember 社团成员
     * @return 结果
     */
    public int insertClubMember(ClubMember clubMember);

    /**
     * 修改社团成员
     * 
     * @param clubMember 社团成员
     * @return 结果
     */
    public int updateClubMember(ClubMember clubMember);

    /**
     * 删除社团成员
     * 
     * @param id 社团成员主键
     * @return 结果
     */
    public int deleteClubMemberById(Long id);

    /**
     * 批量删除社团成员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClubMemberByIds(Long[] ids);
}
