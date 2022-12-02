package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Club;
import com.ruoyi.system.params.ClubParams;

/**
 * 社团Service接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface IClubService 
{


    List<Club> listByUserId(Long userId);

    Club findClubByParams(ClubParams params);

    /**
     * 查询社团
     * 
     * @param id 社团主键
     * @return 社团
     */
    public Club selectClubById(Long id);

    /**
     * 查询社团
     *
     * @param id 社团主键
     * @return 社团
     */
    public void pass(Long id,String userName);

    /**
     * 查询社团列表
     * 
     * @param club 社团
     * @return 社团集合
     */
    public List<Club> selectClubList(Club club);

    /**
     * 新增社团
     * 
     * @param club 社团
     * @return 结果
     */
    public int insertClub(Club club);

    public int insertClub(Club club,Long userId);

    /**
     * 修改社团
     * 
     * @param club 社团
     * @return 结果
     */
    public int updateClub(Club club);

    /**
     * 批量删除社团
     * 
     * @param ids 需要删除的社团主键集合
     * @return 结果
     */
    public int deleteClubByIds(Long[] ids);

    /**
     * 删除社团信息
     * 
     * @param id 社团主键
     * @return 结果
     */
    public int deleteClubById(Long id);
}
