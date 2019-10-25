package com.ruoyi.moudleSet.service;

import com.ruoyi.moudleSet.domain.FileInfo;

import java.util.List;

/**
 * 图片上传Service接口
 * 
 * @author ruoyi
 * @date 2019-10-22
 */
public interface IFileInfoService 
{
    /**
     * 查询图片上传
     * 
     * @param id 图片上传ID
     * @return 图片上传
     */
    public FileInfo selectFileInfoById(String id);

    /**
     * 查询图片上传列表
     * 
     * @param fileInfo 图片上传
     * @return 图片上传集合
     */
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo);

    /**
     * 新增图片上传
     * 
     * @param fileInfo 图片上传
     * @return 结果
     */
    public int insertFileInfo(FileInfo fileInfo);

    /**
     * 修改图片上传
     * 
     * @param fileInfo 图片上传
     * @return 结果
     */
    public int updateFileInfo(FileInfo fileInfo);

    /**
     * 批量删除图片上传
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileInfoByIds(String ids);

    /**
     * 删除图片上传信息
     * 
     * @param id 图片上传ID
     * @return 结果
     */
    public int deleteFileInfoById(String id);
}
