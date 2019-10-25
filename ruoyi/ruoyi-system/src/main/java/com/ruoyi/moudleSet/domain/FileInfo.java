package com.ruoyi.moudleSet.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 图片上传对象 file_info
 * 
 * @author ruoyi
 * @date 2019-10-22
 */
public class FileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 创建名字 */
    @Excel(name = "创建名字")
    private String name;

    /** 图片路径1 */
    @Excel(name = "图片路径1")
    private String fileNamew;

    /** 图片路径2 */
    @Excel(name = "图片路径2")
    private String fileNamet;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creationTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFileNamew(String fileNamew) 
    {
        this.fileNamew = fileNamew;
    }

    public String getFileNamew() 
    {
        return fileNamew;
    }
    public void setFileNamet(String fileNamet) 
    {
        this.fileNamet = fileNamet;
    }

    public String getFileNamet() 
    {
        return fileNamet;
    }
    public void setCreationTime(Date creationTime) 
    {
        this.creationTime = creationTime;
    }

    public Date getCreationTime() 
    {
        return creationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("fileNamew", getFileNamew())
            .append("fileNamet", getFileNamet())
            .append("creationTime", getCreationTime())
            .toString();
    }
}
