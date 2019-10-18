package com.ruoyi.web.controller.moudleSet;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.moudleSet.domain.MsAtlas;
import com.ruoyi.moudleSet.service.IMsAtlasService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地图Controller
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
@Controller
@RequestMapping("/moudleSet/atlas")
public class MsAtlasController extends BaseController
{
    private String prefix = "moudleSet/atlas";

    @Autowired
    private IMsAtlasService msAtlasService;

    @GetMapping("/openMap")
    public String openMap(){
        return prefix+ "/openMapPlace";
    }
    @RequiresPermissions("moudleSet:atlas:view")
    @GetMapping()
    public String atlas()
    {
        return prefix + "/atlas";
    }

    /**
     * 查询地图列表
     */
    @RequiresPermissions("moudleSet:atlas:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MsAtlas msAtlas)
    {
        startPage();
        List<MsAtlas> list = msAtlasService.selectMsAtlasList(msAtlas);
        return getDataTable(list);
    }

    /**
     * 导出地图列表
     */
    @RequiresPermissions("moudleSet:atlas:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsAtlas msAtlas)
    {
        List<MsAtlas> list = msAtlasService.selectMsAtlasList(msAtlas);
        ExcelUtil<MsAtlas> util = new ExcelUtil<MsAtlas>(MsAtlas.class);
        return util.exportExcel(list, "atlas");
    }

    /**
     * 新增地图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存地图
     */
    @RequiresPermissions("moudleSet:atlas:add")
    @Log(title = "地图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsAtlas msAtlas)
    {
        return toAjax(msAtlasService.insertMsAtlas(msAtlas));
    }

    /**
     * 修改地图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MsAtlas msAtlas = msAtlasService.selectMsAtlasById(id);
        mmap.put("msAtlas", msAtlas);
        return prefix + "/edit";
    }

    /**
     * 修改保存地图
     */
    @RequiresPermissions("moudleSet:atlas:edit")
    @Log(title = "地图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsAtlas msAtlas)
    {
        return toAjax(msAtlasService.updateMsAtlas(msAtlas));
    }

    /**
     * 删除地图
     */
    @RequiresPermissions("system:atlas:remove")
    @Log(title = "地图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msAtlasService.deleteMsAtlasByIds(ids));
    }
}
