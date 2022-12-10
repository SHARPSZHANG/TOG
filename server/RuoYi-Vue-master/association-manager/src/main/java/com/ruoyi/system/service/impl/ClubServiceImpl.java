package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ClubMember;
import com.ruoyi.system.params.ClubParams;
import com.ruoyi.system.service.IClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClubMapper;
import com.ruoyi.system.domain.Club;
import com.ruoyi.system.service.IClubService;

/**
 * 社团Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Service
public class ClubServiceImpl implements IClubService 
{
    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private IClubMemberService clubMemberService;

    @Override
    public List<Club> listByUserId(Long userId) {


        List<Club> clubs = clubMapper.listByUserId(userId);
        return clubs;
    }

    @Override
    public Club findClubByParams(ClubParams params) {

        return  clubMapper.findClubByParams(params);
    }

    /**
     * 查询社团
     * 
     * @param id 社团主键
     * @return 社团
     */
    @Override
    public Club selectClubById(Long id)
    {
        return clubMapper.selectClubById(id);
    }

    @Override
    public void pass(Long id,String userName) {
        Club club = clubMapper.selectClubById(id);
        club.setUpdateBy(userName);
        club.setUpdateTime(DateUtils.getNowDate());
        club.setState(1);
        clubMapper.updateClub(club);
    }

    /**
     * 查询社团列表
     * 
     * @param club 社团
     * @return 社团
     */
    @Override
    public List<Club> selectClubList(Club club)
    {
        return clubMapper.selectClubList(club);
    }

    /**
     * 新增社团
     * 
     * @param club 社团
     * @return 结果
     */
    @Override
    public int insertClub(Club club)
    {

        club.setCreateTime(DateUtils.getNowDate());
        return clubMapper.insertClub(club);
    }

    @Override
    public int insertClub(Club club, Long userId) {
        club.setCreateTime(DateUtils.getNowDate());
        club.setState(0);
        int i = clubMapper.insertClub(club);
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(club.getId());
        clubMember.setUserId(userId);
        clubMember.setPosition("社长");
        clubMember.setState(1);
        clubMember.setCreateTime(DateUtils.getNowDate());
        clubMemberService.insertClubMember(clubMember);
        return i;
    }

    /**
     * 修改社团
     * 
     * @param club 社团
     * @return 结果
     */
    @Override
    public int updateClub(Club club)
    {
        club.setUpdateTime(DateUtils.getNowDate());
        return clubMapper.updateClub(club);
    }

    /**
     * 批量删除社团
     * 
     * @param ids 需要删除的社团主键
     * @return 结果
     */
    @Override
    public int deleteClubByIds(Long[] ids)
    {
        return clubMapper.deleteClubByIds(ids);
    }

    /**
     * 删除社团信息
     * 
     * @param id 社团主键
     * @return 结果
     */
    @Override
    public int deleteClubById(Long id)
    {
        return clubMapper.deleteClubById(id);
    }
}
