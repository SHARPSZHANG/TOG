package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TogMessage;
import com.ruoyi.system.service.ITogMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息Controller
 * 
 * @author ruoyi
 * @date 2022-11-16
 */
@Api("消息管理")
@RestController
@RequestMapping("/system/tog/message")
public class TogMessageController extends BaseController
{
    @Autowired
    private ITogMessageService togMessageService;

    /**
     * 查询消息列表
     */
    @ApiOperation("查询消息列表")
//    @PreAuthorize("@ss.hasPermi('system:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(TogMessage togMessage)
    {
        startPage();
        List<TogMessage> list = togMessageService.selectTogMessageList(togMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息列表
     */
    @ApiOperation("导出消息列表")
//    @PreAuthorize("@ss.hasPermi('system:message:export')")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TogMessage togMessage)
    {
        List<TogMessage> list = togMessageService.selectTogMessageList(togMessage);
        ExcelUtil<TogMessage> util = new ExcelUtil<TogMessage>(TogMessage.class);
        util.exportExcel(response, list, "消息数据");
    }

    /**
     * 获取消息详细信息
     */
    @ApiOperation("获取消息详细信息")
    @ApiImplicitParam(name = "id", value = "消息id", required = true, dataType = "Long", paramType = "path", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermi('system:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(togMessageService.selectTogMessageById(id));
    }

    /**
     * 新增消息
     */
//    @PreAuthorize("@ss.hasPermi('system:message:add')")
    @ApiOperation("新增消息")
    @ApiImplicitParam(name = "togMessage", value = "消息id数组", required = true, dataType = "TogMessage", paramType = "body", dataTypeClass = TogMessage.class)
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TogMessage togMessage)
    {
        return toAjax(togMessageService.insertTogMessage(togMessage));
    }

    /**
     * 修改消息
     */
//    @PreAuthorize("@ss.hasPermi('system:message:edit')")
    @ApiOperation("修改消息")
    @ApiImplicitParam(name = "togMessage", value = "消息id数组", required = true, dataType = "TogMessage", paramType = "body", dataTypeClass = TogMessage.class)
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TogMessage togMessage)
    {
        return toAjax(togMessageService.updateTogMessage(togMessage));
    }

    /**
     * 删除消息
     */
    @ApiOperation("删除消息")
    @ApiImplicitParam(name = "ids", value = "消息id数组", required = true, dataType = "Long[]", paramType = "path", dataTypeClass = Long[].class)
//    @PreAuthorize("@ss.hasPermi('system:message:remove')")
    @Log(title = "消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(togMessageService.deleteTogMessageByIds(ids));
    }
}
