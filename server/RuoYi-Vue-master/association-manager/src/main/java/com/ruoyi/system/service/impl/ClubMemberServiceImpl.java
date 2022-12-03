package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.TogMessage;
import com.ruoyi.system.service.ITogMessageService;
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

    @Autowired
    private ITogMessageService iTogMessageService;

    @Override
    public void delClubUser(Long userId, Long clubId) {
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(clubId);
        clubMember.setUserId(userId);
        List<ClubMember> clubMembers = selectClubMemberList2(clubMember);
        if (clubMembers != null && clubMembers.size() > 0){
            ClubMember clubMember1 = clubMembers.get(0);
            deleteClubMemberById(clubMember1.getId());
        }

    }

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

    @Override
    public List<ClubMember> selectClubMemberList2(ClubMember clubMember) {
        return clubMemberMapper.selectClubMemberList2(clubMember);
    }

    /**
     * 新增社团成员
     * 
     * @param clubMember 社团成员
     * @return 结果
     * //        TogMessage message = new TogMessage();
     * //        // 返回插入ID
     * //        Long memberId = ;
     * //        message.setSendId(memberId);
     * //
     * //        message.setTitle(getUsername());
     * //        message.setContent("申请加入社团");
     * //        // 根据社团id查找社长ID
     * //        message.setUserId();
     * //        message.setStatus(0);
     * //        message.setGmtCreate(new Date());
     * //        message.setType(0);
     */
    @Override
    public int insertClubMember(ClubMember clubMember)
    {
        clubMember.setCreateTime(DateUtils.getNowDate());

        int i = clubMemberMapper.insertClubMember(clubMember);
        ClubMember clubMember1 = selectClubLeader(clubMember.getClubId());
        TogMessage togMessage = new TogMessage();
        togMessage.setCreateTime(DateUtils.getNowDate());
        togMessage.setStatus(0);
        togMessage.setSendId(clubMember.getId());
        togMessage.setType(0);
        togMessage.setTitle("新的申请");
        togMessage.setContent("申请加入社团");
        togMessage.setSendId(clubMember.getId());
        togMessage.setUserId(clubMember1.getUserId());
        togMessage.setCreateTime(DateUtils.getNowDate());
        togMessage.setGmtCreate(DateUtils.getNowDate());
        iTogMessageService.insertTogMessage(togMessage);
        return i;
    }


    ClubMember selectClubLeader(Long clubId){
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(clubId);
        clubMember.setPosition("社长");
        List<ClubMember> clubMembers = selectClubMemberList2(clubMember);
        if (clubMembers != null && clubMembers.size()>0){
            return clubMembers.get(0);
        }
        return null;
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

    @Override
    public Boolean getPermissionByUserId(Long userId, Long clubId) {
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(clubId);
        clubMember.setUserId(userId);
        List<ClubMember> clubMembers = selectClubMemberList2(clubMember);
        if (clubMembers !=null && clubMembers.size()>0){
            ClubMember clubMember1 = clubMembers.get(0);
            return "社长".equals(clubMember1.getPosition()) ? true : false;
        }
        return false;
    }
}
