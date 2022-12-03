package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.api.ApiResult;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.params.ClubParams;
import com.ruoyi.system.service.*;
import com.ruoyi.system.vo.ActivityVo;
import com.ruoyi.system.vo.ClubMemberVo;
import com.ruoyi.system.vo.NoticeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动Controller
 *
 * @author ruoyi
 * @date 2022-11-16
 */
@Api(tags = "移动的接口")
@RestController
@RequestMapping("/system/mobile")
public class TogMobileController extends BaseController {
    @Autowired
    private IActivityService activityService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IClubService clubService;
    @Autowired
    private IClubMemberService clubMemberService;
    @Autowired
    private ITogMessageService togMessageService;
    @Autowired
    private INoticeService noticeService;

/*====================================活动=============================================    */
    @ApiOperation("移动：查询所有活动列表")
    @GetMapping("/activity/findAllActivity")
    public ApiResult findAllActivity()
    {
        List<ActivityVo> list = new ArrayList<>(16);
        return new ApiResult<List<ActivityVo>>().setData(list);
    }


    @ApiOperation("移动根据用户Id查询活动列表")
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/activity/findActivityByUserId")
    public ApiResult findActivityByUserId(@RequestParam("userId") Long userId)
    {
        /*
          1.根据用户ID查询出社团
          2.根据社团编号查询出所有活动信息（按时间倒序排列）
          返回List<Activity>
         */

        List<ActivityVo> activities = activityService.findActivityByUserId(userId);
        return new ApiResult<List<ActivityVo>>().setData(activities);
    }

    @ApiOperation("移动：根据社团Id查询活动列表")
    @GetMapping("/activity/findActivityByClubId")
    public ApiResult findActivityByClubId(@RequestParam Long clubId)
    {
        /*
          根据社团编号查询出所有活动信息（按时间倒序排列）
          返回List<ActivityVo>
         */
        List<ActivityVo> activities = activityService.findActivityByClubId(clubId);
        return new ApiResult<List<ActivityVo>>().setData(activities);
    }


    /**
     * 获取活动详细信息
     */
    @ApiOperation("获取活动详细信息vo")
    @ApiImplicitParam(name = "id", value = "活动id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping(value = "/activity/{id}/vo")
    public ApiResult getInfoActivityVo(@PathVariable("id") Long id)
    {
        return new ApiResult().setData(activityService.selectActivityVoById(id));
    }


    /**
     * 新增活动
     */
    @ApiOperation("新增活动")
    @ApiImplicitParam(name = "activity", value = "活动信息", dataType = "Activity", dataTypeClass = Activity.class)
    @PostMapping("/activity")
    public ApiResult addActivity(@RequestBody Activity activity)
    {
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = userService.selectUserById(loginUser.getUserId());
        activity.setCreateBy(sysUser.getNickName());
        activity.setCreateTime(DateUtils.getNowDate());
        return new ApiResult<Boolean>().setData(activityService.insertActivity(activity) > 0);
    }

    @ApiOperation("查询活动权限")
    @GetMapping("/activity/getPermissionByUserId")
    public ApiResult getPermissionByUserId(@RequestParam Long userId,@RequestParam Long activityId)
    {

        /*
         * 1.查询该用户是否为当前活动所属社团社长
         * 2.返回结果 true or false
         */
        return new ApiResult<Boolean>().setData(activityService.getPermissionByUserId(userId,activityId));
    }

    /**
     * 修改活动
     */
    @ApiOperation("修改活动")
    @ApiImplicitParam(name = "activity", value = "活动信息", dataType = "Activity", dataTypeClass = Activity.class)
    @PutMapping("/activity")
    public ApiResult editActivity(@RequestBody Activity activity)
    {
        return new ApiResult<Boolean>().setData(activityService.updateActivity(activity) > 0);
    }

    /**
     * 删除活动
     */
    @ApiOperation("删除活动")
    @ApiImplicitParam(name = "ids", value = "活动id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @DeleteMapping("/activity/{ids}")
    public ApiResult removeActivity(@PathVariable Long[] ids)
    {
        return new ApiResult<Boolean>().setData(activityService.deleteActivityByIds(ids) > 0);
    }

    /* ========================================活动结束=====================================================*/

/* ========================================社团=====================================================*/
    @ApiOperation("根据userId查询社团列表")
    @GetMapping("/club/listByUserId")
    public ApiResult listByUserId(@RequestParam Long userId)
    {
        /*
         * 查询用户所参加的社团列表
         * 返回List<Club>
         */
        List<Club> club = clubService.listByUserId(userId);
        return new ApiResult<List<Club>>().setData(club);
    }

    @ApiOperation("根据社长ID查询社团信息")
    @GetMapping("/club/findClubByUserId")
    public ApiResult findClubByUserId(@RequestParam Long userId)
    {
        /*
         * 根据社长ID查询社团信息
         * 返回Club
         */
        ClubParams params = new ClubParams(userId, "社长");

        Club clubs = clubService.findClubByParams(params);
        return new ApiResult<Club>().setData(clubs);
    }

    @ApiOperation("查询社团列表")
    @GetMapping("/club/all/list")
    public ApiResult getClubList()
    {
        /*
         * 查询所有社团列表
         * 返回List<Club>
         */
        Club club = new Club();
        club.setState(1);
        List<Club> clubs = clubService.selectClubList(club);
        return new ApiResult<List<Club>>().setData(clubs);
    }

    /**
     * 查询当前用户是否已经加入改社团
     * @param clubId
     * @return
     */
    @ApiOperation("查询当前用户是否已经加入改社团")
    @GetMapping("/club/getMember")
    public ApiResult getMember(@RequestParam Long clubId)
    {
        /*
            查询当前用户是否已经加入改社团
            根据userID 和 clubId 查询member表
         */
        Long userId = getUserId();
        ClubMember clubMember = new ClubMember();
        clubMember.setUserId(userId);
        clubMember.setClubId(clubId);
        List<ClubMember> clubMembers = clubMemberService.selectClubMemberList2(clubMember);
        if (clubMembers != null && clubMembers.size()>0){
            return new ApiResult<Boolean>().setData(true);
        }else {
            return new ApiResult<Boolean>().setData(false);
        }
    }


    /**
     * 获取社团详细信息
     */
    @ApiOperation("获取社团详细信息")
    @ApiImplicitParam(name = "id", value = "社团ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/club/{id}")
    public ApiResult getInfo(@PathVariable("id") Long id)
    {
        return new ApiResult<Club>().setData(clubService.selectClubById(id));
    }

//    /**
//     * 获取社团详细信息
//     */
//    @ApiOperation("获取社团详细信息")
//    @ApiImplicitParam(name = "id", value = "社团ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
//    @GetMapping(value = "/club/{id}")
//    public ApiResult getInfo(@PathVariable("id") Long id)
//    {
//        Long userId = getUserId();
//        /*
//          创建社团、并将当前用户设置为社长
//         */
//        return new ApiResult<Club>().setData(clubService.selectClubById(id));
//    }

    /**
     * 新增活动
     */
    @ApiOperation("社团审核通过")
    @ApiImplicitParam(name = "id", value = "社团ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/club/{id}/pass")
    public ApiResult passClub(@PathVariable("id") Long id)
    {
        clubService.pass(id,getUsername());
        return ApiResult.success();
    }

    /**
     * 新增社团
     */
    @ApiOperation("创建社团、并将当前用户设置为社长")
    @ApiImplicitParam(name = "club", value = "社团信息", required = true, dataType = "Long", paramType = "body", dataTypeClass = Club.class)
    @PostMapping("/club")
    public ApiResult addClub(@RequestBody Club club, @RequestParam("userId") Long userId)
    {

        LoginUser loginUser = getLoginUser();
        SysUser sysUser = userService.selectUserById(loginUser.getUserId());
        club.setCreateBy(sysUser.getNickName());
        club.setCreateTime(DateUtils.getNowDate());
        return new ApiResult<Boolean>().setData(clubService.insertClub(club,userId) > 0);
    }

    /**
     * 修改活动
     */
    @ApiOperation("修改社团")
    @ApiImplicitParam(name = "activity", value = "活动信息", dataType = "Activity", dataTypeClass = Activity.class)
    @PostMapping("/club/update")
    public ApiResult editClub(@RequestBody Club club)
    {
        return new ApiResult<Boolean>().setData(clubService.updateClub(club) > 0);
    }

    @ApiOperation("删除社团")
    @DeleteMapping("/club/{id}")
    public ApiResult deleteClub( @PathVariable Long id)
    {
        return new ApiResult<Boolean>().setData(clubService.deleteClubById(id) > 0);
    }

    /* ========================================社团结束=====================================================*/

    /* ========================================社团成员=====================================================*/

        /**
     * 查询社团成员列表
     */
    @ApiOperation("查询社团成员列表")
    @GetMapping("/member/list")
    public ApiResult listMember(@RequestParam Long clubId)
    {
        /*
         * 1.根据clubId查询member列表，返回List<memberVo>
         */
        ClubMember clubMember = new ClubMember();
        clubMember.setClubId(clubId);
        List<ClubMemberVo> clubMemberVos = clubMemberService.selectClubMemberList(clubMember);
        return new ApiResult<List<ClubMemberVo>>().setData(clubMemberVos);
    }

    @ApiOperation("查询社团成员,返回memberVo")
    @GetMapping("/member/id")
    public ApiResult listMemberById(@RequestParam Long memberId)
    {
        /*
         * 1.根据idd查询member，返回memberVo
         */
        return new ApiResult<ClubMemberVo>().setData(clubMemberService.selectClubMemberById(memberId));
    }

    @ApiOperation("通过加入社团申请")
    @ApiImplicitParam(name = "id", value = "社团申请id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/member/{id}/pass")
    public ApiResult pass(@PathVariable("id") Long id)
    {
        clubMemberService.pass(id,getUsername());
        return new ApiResult<Boolean>().setData(true);
    }

    /**
     * 新增社团成员
     */
    @ApiOperation("新增社团成员")
    @ApiImplicitParam(name = "ClubMember", value = "社团成员信息", required = true, dataType = "ClubMember", paramType = "body", dataTypeClass = ClubMember.class)
    @PostMapping("/member/add")
    public ApiResult addMember(@RequestBody ClubMember clubMember)
    {
        SysUser sysUser = userService.selectUserById(getUserId());
        clubMember.setCreateBy(sysUser.getNickName());
        clubMember.setCreateTime(DateUtils.getNowDate());
        clubMember.setState(0);
        clubMemberService.insertClubMember(clubMember);
        return new ApiResult<Boolean>().setData(true);
    }

    /**
     * 修改社团成员
     */
    @ApiOperation("修改社团成员")
    @ApiImplicitParam(name = "ClubMember", value = "社团成员信息", required = true, dataType = "ClubMember", paramType = "body", dataTypeClass = ClubMember.class)
    @PostMapping("/member/update")
    public ApiResult editMember(@RequestBody ClubMember clubMember)
    {
        clubMember.setUpdateBy(getUsername());
        clubMember.setUpdateTime(DateUtils.getNowDate());
        clubMemberService.updateClubMember(clubMember);
        return new ApiResult<Boolean>().setData(true);
    }


    /**
     * 删除社团成员
     */
    @ApiOperation("删除club_member 中 clubId所属的ids")
    @ApiImplicitParam(name = "userId", value = "社团成员id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @Log(title = "社团成员", businessType = BusinessType.DELETE)
    @PostMapping("/member/del/club/user")
    public ApiResult removeMember(@RequestParam Long userId, @RequestParam Long clubId)
    {
        /*
         * 删除club_member 中 clubId所属的ids
         */
        clubMemberService.delClubUser(userId,clubId);
        return new ApiResult<Boolean>().setData(true);
    }


    @ApiOperation("查询该用户是否为社团社长")
    @GetMapping("/member/getPermissionByUserId")
    public ApiResult getMemPermissionByUserId(@RequestParam Long userId, @RequestParam Long clubId) {

        /*
         * 1.查询该用户是否为社团社长
         * 2.返回结果 true or false
         */
        return new ApiResult<Boolean>().setData(clubMemberService.getPermissionByUserId(userId,clubId));
    }

    /**
     * 删除社团成员
     */
    @ApiOperation("删除社团成员")
    @ApiImplicitParam(name = "ids", value = "社团成员id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @PostMapping("/member/del")
        public ApiResult removeMember(@RequestBody List<Long> ids)
    {


        return new ApiResult<Boolean>().setData(clubMemberService.deleteClubMemberByIds(ids.toArray(new Long[ids.size()])) > 0);
    }

    /* ========================================社团成员结束=====================================================*/




    /* ========================================公告=====================================================*/
    @ApiOperation("根据用户ID查询所属社团公告列表")
    @GetMapping("/notice/findNoticeByUserId")
    public ApiResult findNoticeByUserId(@RequestParam Long userId)
    {
        /*
         * 同activityController
         * 返回List<NoticeVo>
         */
        List<NoticeVo> noticeByUserId = noticeService.findNoticeByUserId(userId);
        return new ApiResult<List<NoticeVo>>().setData(noticeByUserId);
    }

    /**
     * 查询社团下面的公告
     * @param clubId
     * @return
     */
    @GetMapping("/notice/findNoticeByClubId")
    public ApiResult findNoticeByClubId(@RequestParam Long clubId)
    {
        /*
         * 同activityController
         * 返回List<NoticeVo>
         */
        List<NoticeVo> notice = noticeService.findNoticeByClubId(clubId);
        return new ApiResult<List<NoticeVo>>().setData(notice);
    }


    /**
     * 获取公告详细信息
     */
    @ApiOperation("获取公告详细信息")
    @ApiImplicitParam(name = "id", value = "公告id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/notice/{id}")
    public ApiResult getNoticeInfo(@PathVariable("id") Long id)
    {
        // 返回 NoticeVo
        NoticeVo noticeVo = noticeService.selectNoticeVoById(id);
        return new ApiResult<NoticeVo>().setData(noticeVo);
    }

    /**
     * 新增公告
     */
    @ApiOperation("新增公告")
    @ApiImplicitParam(name = "notice", value = "公告信息", required = true, dataType = "Notice", paramType = "body", dataTypeClass = Notice.class)
    @PostMapping("/notice")
    public ApiResult addNotice(@RequestBody Notice notice)
    {
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = userService.selectUserById(loginUser.getUserId());
        notice.setCreateBy(sysUser.getNickName());
        notice.setCreateTime(DateUtils.getNowDate());
        return new ApiResult<Boolean>().setData(noticeService.insertNotice(notice) > 0);
    }

    /**
     * 修改公告
     */
    @ApiOperation("修改公告")
    @ApiImplicitParam(name = "notice", value = "公告信息", required = true, dataType = "Notice", paramType = "body", dataTypeClass = Notice.class)
    @PutMapping("/notice")
    public ApiResult editNotice(@RequestBody Notice notice)
    {
        return new ApiResult<Boolean>().setData(noticeService.updateNotice(notice) >0);
    }

    /**
     * 删除公告
     */
    @ApiOperation("删除公告")
    @ApiImplicitParam(name = "ids", value = "公告id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @DeleteMapping("/notice/{ids}")
    public ApiResult removeNotice(@PathVariable Long[] ids)
    {
        return new ApiResult<Boolean>().setData(noticeService.deleteNoticeByIds(ids)>0);
    }


    @ApiOperation("删除公告")
    @ApiImplicitParam(name = "ids", value = "公告id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public ApiResult deleteNoticeById(@PathVariable Long id)
    {
        return new ApiResult<Boolean>().setData(noticeService.deleteNoticeById(id) > 0);
    }

    @ApiOperation("查询公告权限")
    @GetMapping("/notice/getPermissionByUserId")
    public ApiResult getNoticePermissionByUserId(@RequestParam Long userId,@RequestParam Long noticeId)
    {

        /*
         * 1.查询该用户是否为当前通知所属社团社长
         * 2.返回结果 true or false
         */
        return new ApiResult<Boolean>().setData(noticeService.getPermissionByUserId(userId,noticeId));
    }


    /* ========================================公告结束=====================================================*/



    /* ========================================消息=====================================================*/

    @ApiOperation("查询用户的消息列表")
    @GetMapping("/message/all/list")
    public ApiResult getMessageList(@RequestParam Long userId)
    {
        /*
         * 根据用户ID查询消息列表
         * 返回结果List<TogMessage>
         */
        TogMessage togMessage = new TogMessage();
        togMessage.setUserId(userId);
        List<TogMessage> togMessages = togMessageService.selectTogMessageList(togMessage);
        return new ApiResult<List<TogMessage>>().setData(togMessages);
    }


    /**
     * 获取消息详细信息
     */
    @ApiOperation("获取消息详细信息")
    @ApiImplicitParam(name = "id", value = "消息id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/message/{id}")
    public ApiResult getMessageInfo(@PathVariable("id") Long id)
    {
        return new ApiResult<TogMessage>().setData(togMessageService.selectTogMessageById(id));
    }

    /**
     * 新增消息
     */
    @ApiOperation("新增消息")
    @ApiImplicitParam(name = "togMessage", value = "消息id数组", required = true, dataType = "TogMessage", paramType = "body", dataTypeClass = TogMessage.class)
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping("/message")
    public ApiResult addMessage(@RequestBody TogMessage togMessage)
    {
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = userService.selectUserById(loginUser.getUserId());
        togMessage.setCreateBy(sysUser.getNickName());
        togMessage.setCreateTime(DateUtils.getNowDate());
        return new ApiResult<Boolean>().setData(togMessageService.insertTogMessage(togMessage) > 0);
    }

    /**
     * 修改消息
     */
    @ApiOperation("修改消息")
    @ApiImplicitParam(name = "togMessage", value = "消息id数组", required = true, dataType = "TogMessage", paramType = "body", dataTypeClass = TogMessage.class)
    @PutMapping("/message")
    public ApiResult editMessage(@RequestBody TogMessage togMessage)
    {
        return new ApiResult<Boolean>().setData(togMessageService.updateTogMessage(togMessage) >0);
    }

    /**
     * 删除消息
     */
    @ApiOperation("删除消息")
    @ApiImplicitParam(name = "ids", value = "消息id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @DeleteMapping("/message/{ids}")
    public ApiResult remove(@PathVariable Long[] ids)
    {
        return new ApiResult<Boolean>().setData(togMessageService.deleteTogMessageByIds(ids) >0);
    }
}
