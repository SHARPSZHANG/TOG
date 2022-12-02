package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.vo.ActivityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.service.IActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Api(tags = "活动")
@RestController
@RequestMapping("/system/activity")
public class ActivityController extends BaseController
{
    @Autowired
    private IActivityService activityService;
    @Autowired
    private ISysUserService userService;
    /**
     * 查询活动列表
     */
    @ApiOperation("查询活动列表")
//    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(Activity activity)
    {
        startPage();
        List<Activity> list = activityService.selectActivityList(activity);
        return getDataTable(list);
    }

    @ApiOperation("查询所有活动列表")
//    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/findAllActivity")
    public AjaxResult list()
    {
        startPage();
        List<Activity> list = new ArrayList<>(16);
        return AjaxResult.success(list);
    }

    @ApiOperation("根据用户Id查询活动列表")
//    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/findActivityByUserId")
    public AjaxResult findActivityByUserId(@RequestParam("userId") Long userId)
    {
        /*
          1.根据用户ID查询出社团
          2.根据社团编号查询出所有活动信息（按时间倒序排列）
          返回List<Activity>
         */

        List<Activity> activities = activityService.findActivityByUserId(userId);
        return AjaxResult.success(activities);
    }
//
//    @ApiOperation("根据社团Id查询活动列表")
////    @PreAuthorize("@ss.hasPermi('system:activity:list')")
//    @GetMapping("/findActivityByClubId")
//    public AjaxResult findActivityByClubId(@RequestParam Long clubId)
//    {
//        /*
//          1.根据用户ID查询出社团
//          2.根据社团编号查询出所有活动信息（按时间倒序排列）
//          返回List<ActivityVo>
//         */
//        List<ActivityVo> activities = new ArrayList<ActivityVo>();
////        Activity activity = new Activity();
////        activity.setClubId(clubId);
////        activities = activityService.selectActivityList(activity);
//        return AjaxResult.success(activities);
//    }
    /**
     * 导出活动列表
     */
    @ApiOperation("导出活动列表")
//    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Activity activity)
    {
        List<Activity> list = activityService.selectActivityList(activity);
        ExcelUtil<Activity> util = new ExcelUtil<Activity>(Activity.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @ApiOperation("获取活动详细信息")
    @ApiImplicitParam(name = "id", value = "活动id", dataType = "Long", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(activityService.selectActivityById(id));
    }


    /**
     * 获取活动详细信息
     */
    @ApiOperation("获取活动详细信息vo")
    @ApiImplicitParam(name = "id", value = "活动id", dataType = "Long", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}/vo")
    public AjaxResult getInfoActivityVo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(activityService.selectActivityVoById(id));
    }

    /**
     * 新增活动
     */
//    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @ApiOperation("新增活动")
    @ApiImplicitParam(name = "activity", value = "活动信息", dataType = "Activity", dataTypeClass = Activity.class)
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Activity activity)
    {
        return AjaxResult.success(activityService.insertActivity(activity) > 0);
    }

    /**
     * 修改活动
     */
    @ApiOperation("修改活动")
    @ApiImplicitParam(name = "activity", value = "活动信息", dataType = "Activity", dataTypeClass = Activity.class)
//    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Activity activity)
    {
        return AjaxResult.success(activityService.updateActivity(activity) > 0);
    }

    @ApiOperation("删除活动")
    //    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{id}")
    public AjaxResult delete( @PathVariable Long id)
    {
        return AjaxResult.success(activityService.deleteActivityById(id) > 0);
    }

    @ApiOperation("查询权限")
    //    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
//    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @GetMapping("/getPermissionByUserId")
    public AjaxResult getPermissionByUserId(@RequestParam Long userId,@RequestParam Long activityId)
    {

        /*
         * 1.查询该用户是否为当前活动所属社团社长
         * 2.返回结果 true or false
         */
        Boolean res = activityService.getPermissionByUserId(userId,activityId);
        return AjaxResult.success(res);
    }

    /**
     * 删除活动
     */
    @ApiOperation("删除活动")
    @ApiImplicitParam(name = "ids", value = "社团成员id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
//    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(activityService.deleteActivityByIds(ids));
    }
}
