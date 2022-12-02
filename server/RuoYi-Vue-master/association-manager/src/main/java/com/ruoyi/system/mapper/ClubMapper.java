package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Club;
import com.ruoyi.system.params.ClubParams;

/**
 * 社团Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
public interface ClubMapper
{



    Club findClubByParams(ClubParams params);

    List<Club> listByUserId(Long userId);
    /**
     * 查询社团
     * 
     * @param id 社团主键
     * @return 社团
     */
    public Club selectClubById(Long id);

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

    /**
     * 修改社团
     * 
     * @param club 社团
     * @return 结果
     */
    public int updateClub(Club club);

    /**
     * 删除社团
     * 
     * @param id 社团主键
     * @return 结果
     */
    public int deleteClubById(Long id);

    /**
     * 批量删除社团
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClubByIds(Long[] ids);
}
