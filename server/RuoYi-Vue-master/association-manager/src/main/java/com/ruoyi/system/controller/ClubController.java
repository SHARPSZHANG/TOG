package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
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

    @ApiOperation("查询社团列表")
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/listByUserId")
    public AjaxResult listByUserId(@RequestParam Long userId)
    {
        /*
         * 查询用户所参加的社团列表
         * 返回List<Club>
         */
        return AjaxResult.success();
    }

    @ApiOperation("根据社长ID查询社团信息")
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/findClubByUserId")
    public AjaxResult findClubByUserId(@RequestParam Long userId)
    {
        /*
         * 查询用户所参加的社团列表
         * 返回List<Club>
         */
        return AjaxResult.success();
    }



    @ApiOperation("查询社团列表")
    //    @PreAuthorize("@ss.hasPermi('system:club:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        /*
         * 查询所有社团列表
         * 返回List<Club>
         */
        return AjaxResult.success();
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
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(clubService.selectClubById(id));
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
        return AjaxResult.success(true);
    }

    /**
     * 新增社团
     */
//    @PreAuthorize("@ss.hasPermi('system:club:add')")
    @ApiOperation("新增社团")
    @ApiImplicitParam(name = "club", value = "社团信息", required = true, dataType = "Long", paramType = "body", dataTypeClass = Club.class)
    @Log(title = "社团", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Club club, @RequestParam("userId") Long userId)
    {
        club.setCreateBy(getUsername());
        return AjaxResult.success(clubService.insertClub(club) > 0);
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
    public AjaxResult enRecruit(@RequestParam("clubId") Long clubId,
                                @RequestParam("notice") Boolean notice,
                                @RequestParam("activity") Boolean activity)
    {
        /*
         * 开启社团招新
         * 写入招新活动（activity == true）、招新通知（notice == true）、消息
         */
        return AjaxResult.success();
    }
}
