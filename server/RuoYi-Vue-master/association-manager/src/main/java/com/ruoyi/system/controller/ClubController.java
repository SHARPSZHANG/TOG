package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.api.ApiResult;
import com.ruoyi.system.domain.ClubMember;
import com.ruoyi.system.params.ClubParams;
import com.ruoyi.system.service.IClubMemberService;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import com.ruoyi.system.domain.Club;
import com.ruoyi.system.service.IClubService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 社团Controller
 * 
 * @author xuzili
 * @date 2022-11-16
 */
@Api("社团信息管理")
@RestController
@RequestMapping("/system/club")
public class ClubController extends BaseController
{
    @Autowired
    private IClubService clubService;

    @Autowired
    private IClubMemberService clubMemberService;

    /**
     * 查询社团列表
     */
    @ApiOperation("查询社团列表")
//    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/list")
    public TableDataInfo list(Club club)
    {
        startPage();
        List<Club> list = clubService.selectClubList(club);
        return getDataTable(list);
    }

    @ApiOperation("根据userId查询社团列表")
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/listByUserId")
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
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/findClubByUserId")
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
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/all/list")
    public ApiResult list()
    {
        /*
         * 查询所有社团列表
         * 返回List<Club>
         */
        List<Club> clubs = clubService.selectClubList(new Club());
        return new ApiResult<List<Club>>().setData(clubs);
    }

    /**
     * 导出社团列表
     */
//    @PreAuthorize("@ss.hasPermi('system:club:export')")
    @Log(title = "社团", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Club club)
    {
        List<Club> list = clubService.selectClubList(club);
        ExcelUtil<Club> util = new ExcelUtil<Club>(Club.class);
        util.exportExcel(response, list, "社团数据");
    }

    /**
     * 获取社团详细信息
     */
    @ApiOperation("获取社团详细信息")
    @ApiImplicitParam(name = "id", value = "社团ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermi('system:club:query')")
    @GetMapping(value = "/{id}")
    public ApiResult getInfo(@PathVariable("id") Long id)
    {
        return new ApiResult<Club>().setData(clubService.selectClubById(id));
    }

    /**
     * 社团审核通过
     * @param id
     * @return
     */
    @ApiOperation("社团审核通过")
    @ApiImplicitParam(name = "id", value = "社团ID", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/{id}/pass")
    public AjaxResult pass(@PathVariable("id") Long id)
    {
        clubService.pass(id,getUsername());
        return AjaxResult.success();
    }

    /**
     * 新增社团
     */
//    @PreAuthorize("@ss.hasPermi('system:club:add')")
    @ApiOperation("新增社团")
    @ApiImplicitParam(name = "club", value = "社团信息", required = true, dataType = "Long", paramType = "body", dataTypeClass = Club.class)
    @Log(title = "社团", businessType = BusinessType.INSERT)
    @PostMapping
    public ApiResult add(@RequestBody Club club, @RequestParam("userId") Long userId)
    {
        club.setCreateBy(getUsername());
        return new ApiResult<Boolean>().setData(clubService.insertClub(club,userId) > 0);
    }

    /**
     * 修改社团
     */
//    @PreAuthorize("@ss.hasPermi('system:club:edit')")
    @ApiOperation("修改社团")
    @ApiImplicitParam(name = "club", value = "社团信息", required = true, dataType = "Long", paramType = "body", dataTypeClass = Club.class)
    @Log(title = "社团", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Club club)
    {
        club.setUpdateBy(getUsername());
        club.setUpdateTime(DateUtils.getNowDate());
        return toAjax(clubService.updateClub(club));
    }

    /**
     * 删除社团
     */
//    @PreAuthorize("@ss.hasPermi('system:club:remove')")
    @ApiOperation("删除社团")
    @ApiImplicitParam(name = "ids", value = "社团id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @Log(title = "社团", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clubService.deleteClubByIds(ids));
    }

    @ApiOperation("开启社团招新")
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @PostMapping("/enRecruit")
    public ApiResult enRecruit(@RequestParam("clubId") Long clubId,
                                @RequestParam("notice") Boolean notice,
                                @RequestParam("activity") Boolean activity)
    {
        /*
         * 开启社团招新
         * 写入招新活动（activity == true）、招新通知（notice == true）、消息
         */
        return new ApiResult().setData(true);
    }
}
