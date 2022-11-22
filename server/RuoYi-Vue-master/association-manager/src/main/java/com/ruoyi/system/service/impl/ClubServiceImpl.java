package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
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
