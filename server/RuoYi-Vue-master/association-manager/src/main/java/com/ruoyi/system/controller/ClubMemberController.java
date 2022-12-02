package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.api.ApiResult;
import com.ruoyi.system.domain.Club;
import com.ruoyi.system.params.ClubParams;
import com.ruoyi.system.service.IClubService;
import com.ruoyi.system.vo.ClubMemberVo;
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
import com.ruoyi.system.domain.ClubMember;
import com.ruoyi.system.service.IClubMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 社团成员Controller
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Api("社团成员")
@RestController
@RequestMapping("/system/member")
public class ClubMemberController extends BaseController
{
    @Autowired
    private IClubMemberService clubMemberService;


    @Autowired
    private IClubService clubService;

    /**
     * 查询社团成员列表
     */
    @ApiOperation("查询社团成员列表")
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public AjaxResult list(ClubMember clubMember)
    {
        return AjaxResult.success(clubMemberService.selectClubMemberList2(clubMember));
    }


    @ApiOperation("通过加入社团申请")
    @ApiImplicitParam(name = "id", value = "社团申请id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/{id}/pass")
    public AjaxResult pass(@PathVariable("id") Long id)
    {
        clubMemberService.pass(id,getUsername());
        return AjaxResult.success();
    }


    /**
     * 导出社团成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:export')")
    @Log(title = "社团成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClubMember clubMember)
    {
        List<ClubMemberVo> list = clubMemberService.selectClubMemberList(clubMember);
        ExcelUtil<ClubMemberVo> util = new ExcelUtil<ClubMemberVo>(ClubMemberVo.class);
        util.exportExcel(response, list, "社团成员数据");
    }

    /**
     * 获取社团成员详细信息
     */
    @ApiOperation("获取社团成员详细信息")
    @ApiImplicitParam(name = "id", value = "社团申请id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('system:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(clubMemberService.selectClubMemberById(id));
    }


    /**
     * 新增社团成员
     */
    @ApiOperation("新增社团成员")
    @ApiImplicitParam(name = "ClubMember", value = "社团成员信息", required = true, dataType = "ClubMember", paramType = "body", dataTypeClass = ClubMember.class)
    @PreAuthorize("@ss.hasPermi('system:member:add')")
    @Log(title = "社团成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClubMember clubMember)
    {
        ClubParams params = new ClubParams(getUserId(), "社长");
        Club club = clubService.findClubByParams(params);
        clubMember.setCreateBy(getUsername());
        clubMember.setCreateTime(DateUtils.getNowDate());
        clubMember.setClubId(club.getId());
        return AjaxResult.success(clubMemberService.insertClubMember(clubMember) > 0);
    }

    /**
     * 修改社团成员
     */
    @ApiOperation("修改社团成员")
    @ApiImplicitParam(name = "ClubMember", value = "社团成员信息", required = true, dataType = "ClubMember", paramType = "body", dataTypeClass = ClubMember.class)
    @PreAuthorize("@ss.hasPermi('system:member:edit')")
    @Log(title = "社团成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClubMember clubMember)
    {
        clubMember.setUpdateBy(getUsername());
        clubMember.setUpdateTime(DateUtils.getNowDate());
        return toAjax(clubMemberService.updateClubMember(clubMember));
    }

    /**
     * 删除社团成员
     */
    @ApiOperation("删除社团成员")
    @ApiImplicitParam(name = "ids", value = "社团成员id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @PreAuthorize("@ss.hasPermi('system:member:remove')")
    @Log(title = "社团成员", businessType = BusinessType.DELETE)
	@DeleteMapping()
    public AjaxResult remove(@RequestParam Long[] ids)
    {
        /*
         * 删除club_member 中 clubId所属的ids
         */
        return AjaxResult.success(clubMemberService.deleteClubMemberByIds(ids) > 0);
    }

}
