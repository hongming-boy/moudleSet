package com.ruoyi.moudleSet.mapper;

import com.ruoyi.moudleSet.domain.MsFile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 上传文件Mapper接口
 * 
 * @author moudleSet
 * @date 2019-10-18
 */
@Repository
public interface MsFileMapper 
{
    /**
     * 查询上传文件
     * 
     * @param id 上传文件ID
     * @return 上传文件
     */
    public MsFile selectMsFileById(String id);

    /**
     * 查询上传文件列表
     * 
     * @param msFile 上传文件
     * @return 上传文件集合
     */
    public List<MsFile> selectMsFileList(MsFile msFile);

    /**
     * 新增上传文件
     * 
     * @param msFile 上传文件
     * @return 结果
     */
    public int insertMsFile(MsFile msFile);

    /**
     * 修改上传文件
     * 
     * @param msFile 上传文件
     * @return 结果
     */
    public int updateMsFile(MsFile msFile);

    /**
     * 删除上传文件
     * 
     * @param id 上传文件ID
     * @return 结果
     */
    public int deleteMsFileById(String id);

    /**
     * 批量删除上传文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsFileByIds(String[] ids);
}
