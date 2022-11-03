package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.TogNotice;
import com.ruoyi.system.service.ITogNoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告Controller
 * 
 * @author ruoyi
 * @date 2022-11-03
 */
@RestController
@RequestMapping("/system/notice")
public class TogNoticeController extends BaseController
{
    @Autowired
    private ITogNoticeService togNoticeService;

    /**
     * 查询公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(TogNotice togNotice)
    {
        startPage();
        List<TogNotice> list = togNoticeService.selectTogNoticeList(togNotice);
        return getDataTable(list);
    }

    /**
     * 导出公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:export')")
    @Log(title = "公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TogNotice togNotice)
    {
        List<TogNotice> list = togNoticeService.selectTogNoticeList(togNotice);
        ExcelUtil<TogNotice> util = new ExcelUtil<TogNotice>(TogNotice.class);
        util.exportExcel(response, list, "公告数据");
    }

    /**
     * 获取公告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(togNoticeService.selectTogNoticeById(id));
    }

    /**
     * 新增公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TogNotice togNotice)
    {
        return toAjax(togNoticeService.insertTogNotice(togNotice));
    }

    /**
     * 修改公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TogNotice togNotice)
    {
        return toAjax(togNoticeService.updateTogNotice(togNotice));
    }

    /**
     * 删除公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(togNoticeService.deleteTogNoticeByIds(ids));
    }
}
