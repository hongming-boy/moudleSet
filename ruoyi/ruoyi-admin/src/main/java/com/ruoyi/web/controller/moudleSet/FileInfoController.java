package com.ruoyi.web.controller.moudleSet;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.moudleSet.domain.FileInfo;
import com.ruoyi.moudleSet.service.IFileInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片上传Controller
 * 
 * @author ruoyi
 * @date 2019-10-22
 */
@Controller
@RequestMapping("/moudleSet/info")
public class FileInfoController extends BaseController
{
    private String prefix = "moudleSet/info";

    @Autowired
    private IFileInfoService fileInfoService;

    @RequiresPermissions("moudleSet:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询图片上传列表
     */
    @RequiresPermissions("moudleSet:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileInfo fileInfo)
    {
        startPage();
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        return getDataTable(list);
    }

    /**
     * 导出图片上传列表
     */
    @RequiresPermissions("moudleSet:info:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FileInfo fileInfo)
    {
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        ExcelUtil<FileInfo> util = new ExcelUtil<FileInfo>(FileInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增图片上传
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图片上传
     */
    @RequiresPermissions("moudleSet:info:add")
    @Log(title = "图片上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FileInfo fileInfo)
    {
        return toAjax(fileInfoService.insertFileInfo(fileInfo));
    }

    /**
     * 修改图片上传
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FileInfo fileInfo = fileInfoService.selectFileInfoById(id);
        mmap.put("fileInfo", fileInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存图片上传
     */
    @RequiresPermissions("moudleSet:info:edit")
    @Log(title = "图片上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FileInfo fileInfo)
    {
        return toAjax(fileInfoService.updateFileInfo(fileInfo));
    }

    /**
     * 删除图片上传
     */
    @RequiresPermissions("moudleSet:info:remove")
    @Log(title = "图片上传", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fileInfoService.deleteFileInfoByIds(ids));

    }

    @GetMapping("/addImg")
    public String addImages(){
    return prefix + "/addImg";
}

    @GetMapping("/editImg")
    public String editImages(){
        return prefix + "/editImg";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public Map<String, Object> updateAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String ,Object> mmap = new HashMap<>();
        String fileNamew = FileUploadUtils.upload(Global.getUploadPath(), file);
        mmap.put("fileNamew",fileNamew);
        mmap.put("success",true);
        return  mmap;
    }



}
