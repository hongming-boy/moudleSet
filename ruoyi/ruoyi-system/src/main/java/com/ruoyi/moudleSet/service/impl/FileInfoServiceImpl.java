package com.ruoyi.moudleSet.service.impl;


import com.ruoyi.common.core.text.Convert;
import com.ruoyi.moudleSet.domain.FileInfo;
import com.ruoyi.moudleSet.mapper.FileInfoMapper;
import com.ruoyi.moudleSet.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 图片上传Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-10-22
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService
{
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 查询图片上传
     * 
     * @param id 图片上传ID
     * @return 图片上传
     */
    @Override
    public FileInfo selectFileInfoById(String id)
    {
        return fileInfoMapper.selectFileInfoById(id);
    }

    /**
     * 查询图片上传列表
     * 
     * @param fileInfo 图片上传
     * @return 图片上传
     */
    @Override
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo)
    {
        return fileInfoMapper.selectFileInfoList(fileInfo);
    }

    /**
     * 新增图片上传
     * 
     * @param fileInfo 图片上传
     * @return 结果
     */
    @Override
    public int insertFileInfo(FileInfo fileInfo)
    {
        fileInfo.setId(UUID.randomUUID().toString());
        return fileInfoMapper.insertFileInfo(fileInfo);
    }

    /**
     * 修改图片上传
     * 
     * @param fileInfo 图片上传
     * @return 结果
     */
    @Override
    public int updateFileInfo(FileInfo fileInfo)
    {
        return fileInfoMapper.updateFileInfo(fileInfo);
    }

    /**
     * 删除图片上传对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFileInfoByIds(String ids)
    {
        return fileInfoMapper.deleteFileInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图片上传信息
     * 
     * @param id 图片上传ID
     * @return 结果
     */
    public int deleteFileInfoById(String id)
    {
        return fileInfoMapper.deleteFileInfoById(id);
    }
}
