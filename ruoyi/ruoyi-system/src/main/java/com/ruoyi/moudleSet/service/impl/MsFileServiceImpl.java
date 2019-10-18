package com.ruoyi.moudleSet.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.moudleSet.mapper.MsFileMapper;
import com.ruoyi.moudleSet.domain.MsFile;
import com.ruoyi.moudleSet.service.IMsFileService;
import com.ruoyi.common.core.text.Convert;

import static com.ruoyi.moudleSet.utils.OfficeUtils.distinguishType;

/**
 * 上传文件Service业务层处理
 * 
 * @author moudleSet
 * @date 2019-10-18
 */
@Service
public class MsFileServiceImpl implements IMsFileService 
{
    @Autowired
    private MsFileMapper msFileMapper;

    /**
     * 查询上传文件
     * 
     * @param id 上传文件ID
     * @return 上传文件
     */
    @Override
    public MsFile selectMsFileById(String id)
    {
        return msFileMapper.selectMsFileById(id);
    }

    /**
     * 查询上传文件列表
     * 
     * @param msFile 上传文件
     * @return 上传文件
     */
    @Override
    public List<MsFile> selectMsFileList(MsFile msFile)
    {
        return msFileMapper.selectMsFileList(msFile);
    }

    /**
     * 新增上传文件
     * 
     * @param msFile 上传文件
     * @return 结果
     */
    @Override
    public int insertMsFile(MsFile msFile)
    {
        msFile.setId(UUID.randomUUID().toString());
        msFile.setFileType(distinguishType(msFile.getFileName()));
        msFile.setCreateTime(new Date());
        return msFileMapper.insertMsFile(msFile);
    }

    /**
     * 修改上传文件
     * 
     * @param msFile 上传文件
     * @return 结果
     */
    @Override
    public int updateMsFile(MsFile msFile)
    {
        return msFileMapper.updateMsFile(msFile);
    }

    /**
     * 删除上传文件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsFileByIds(String ids)
    {
        return msFileMapper.deleteMsFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除上传文件信息
     * 
     * @param id 上传文件ID
     * @return 结果
     */
    @Override
    public int deleteMsFileById(String id)
    {
        return msFileMapper.deleteMsFileById(id);
    }
}
