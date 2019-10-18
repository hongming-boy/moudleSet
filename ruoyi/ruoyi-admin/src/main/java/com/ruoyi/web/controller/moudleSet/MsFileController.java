package com.ruoyi.web.controller.moudleSet;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.moudleSet.domain.MsFile;
import com.ruoyi.moudleSet.service.IMsFileService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 上传文件Controller
 * 
 * @author moudleSet
 * @date 2019-10-18
 */
@Controller
@RequestMapping("/moudleSet/file")
public class MsFileController extends BaseController
{
    private String prefix = "moudleSet/file";

    @Autowired
    private IMsFileService msFileService;

    @RequiresPermissions("moudleSet:file:view")
    @GetMapping()
    public String file()
    {
        return prefix + "/file";
    }

    /**
     * 查询上传文件列表
     */
    @RequiresPermissions("moudleSet:file:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MsFile msFile)
    {
        startPage();
        List<MsFile> list = msFileService.selectMsFileList(msFile);
        return getDataTable(list);
    }

    /**
     * 导出上传文件列表
     */
    @RequiresPermissions("moudleSet:file:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MsFile msFile)
    {
        List<MsFile> list = msFileService.selectMsFileList(msFile);
        ExcelUtil<MsFile> util = new ExcelUtil<MsFile>(MsFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 新增上传文件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存上传文件
     */
    @RequiresPermissions("moudleSet:file:add")
    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MsFile msFile)
    {
        return toAjax(msFileService.insertMsFile(msFile));
    }

    /**
     * 修改上传文件
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        MsFile msFile = msFileService.selectMsFileById(id);
        mmap.put("msFile", msFile);
        return prefix + "/edit";
    }

    /**
     * 修改保存上传文件
     */
    @RequiresPermissions("moudleSet:file:edit")
    @Log(title = "上传文件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MsFile msFile)
    {
        return toAjax(msFileService.updateMsFile(msFile));
    }

    /**
     * 删除上传文件
     */
    @RequiresPermissions("moudleSet:file:remove")
    @Log(title = "上传文件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(msFileService.deleteMsFileByIds(ids));
    }
}
