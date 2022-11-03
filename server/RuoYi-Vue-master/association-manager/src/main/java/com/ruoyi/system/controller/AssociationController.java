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
import com.ruoyi.system.domain.Association;
import com.ruoyi.system.service.IAssociationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 社团Controller
 *
 * @author ruoyi
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/system/association")
public class AssociationController extends BaseController
{
    @Autowired
    private IAssociationService associationService;

    /**
     * 查询社团列表
     */
    @PreAuthorize("@ss.hasPermi('system:association:list')")
    @GetMapping("/list")
    public TableDataInfo list(Association association)
    {
        startPage();
        List<Association> list = associationService.selectAssociationList(association);
        return getDataTable(list);
    }

    /**
     * 导出社团列表
     */
    @PreAuthorize("@ss.hasPermi('system:association:export')")
    @Log(title = "社团", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Association association)
    {
        List<Association> list = associationService.selectAssociationList(association);
        ExcelUtil<Association> util = new ExcelUtil<Association>(Association.class);
        util.exportExcel(response, list, "社团数据");
    }

    /**
     * 获取社团详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:association:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(associationService.selectAssociationById(id));
    }

    /**
     * 新增社团
     */
    @PreAuthorize("@ss.hasPermi('system:association:add')")
    @Log(title = "社团", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Association association)
    {
        return toAjax(associationService.insertAssociation(association));
    }

    /**
     * 修改社团
     */
    @PreAuthorize("@ss.hasPermi('system:association:edit')")
    @Log(title = "社团", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Association association)
    {
        return toAjax(associationService.updateAssociation(association));
    }

    /**
     * 删除社团
     */
    @PreAuthorize("@ss.hasPermi('system:association:remove')")
    @Log(title = "社团", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(associationService.deleteAssociationByIds(ids));
    }
}
