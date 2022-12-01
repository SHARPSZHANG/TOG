package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.system.domain.Notice;
import com.ruoyi.system.service.INoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告Controller
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Api("公告")
@RestController
@RequestMapping("/system/tog/notice")
public class NoticeController extends BaseController
{
    @Autowired
    private INoticeService noticeService;

    /**
     * 查询公告列表
     */
//    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @ApiOperation("查询公告列表")
    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 导出公告列表
     */
//    @PreAuthorize("@ss.hasPermi('system:notice:export')")

    @Log(title = "公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notice notice)
    {
        List<Notice> list = noticeService.selectNoticeList(notice);
        ExcelUtil<Notice> util = new ExcelUtil<Notice>(Notice.class);
        util.exportExcel(response, list, "公告数据");
    }

    /**
     * 获取公告详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @ApiOperation("获取公告详细信息")
    @ApiImplicitParam(name = "id", value = "公告id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noticeService.selectNoticeById(id));
    }

    /**
     * 新增公告
     */
    @ApiOperation("新增公告")
    @ApiImplicitParam(name = "notice", value = "公告信息", required = true, dataType = "Notice", paramType = "body", dataTypeClass = Notice.class)
//    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice)
    {
        return AjaxResult.success(noticeService.insertNotice(notice) > 0);
    }

    /**
     * 修改公告
     */
    @ApiOperation("修改公告")
    @ApiImplicitParam(name = "notice", value = "公告信息", required = true, dataType = "Notice", paramType = "body", dataTypeClass = Notice.class)
//    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
//    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @ApiOperation("删除公告")
    @ApiImplicitParam(name = "ids", value = "公告id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
    @Log(title = "公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }


    @ApiOperation("删除公告")
    @ApiImplicitParam(name = "ids", value = "公告id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
    @Log(title = "公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable Long id)
    {
        return AjaxResult.success(noticeService.deleteNoticeById(id) > 0);
    }

    @ApiOperation("查询权限")
    //    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @GetMapping("/getPermissionByUserId")
    public AjaxResult getPermissionByUserId(@RequestParam Long userId)
    {

        /*
         * 1.查询该用户是否为当前活动所属社团社长
         * 2.返回结果 true or false
         */
        return AjaxResult.success();
    }
}
