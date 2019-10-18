package com.ruoyi.moudleSet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 上传文件对象 ms_file
 * 
 * @author moudleSet
 * @date 2019-10-18
 */
public class MsFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 文件名字 */
    @Excel(name = "文件名字")
    private String filename;

    /** 文件路径 */
    private String filepath;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String filetype;

    /** 文件保存名称 */
    private String savename;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setFilepath(String filepath) 
    {
        this.filepath = filepath;
    }

    public String getFilepath() 
    {
        return filepath;
    }
    public void setFiletype(String filetype) 
    {
        this.filetype = filetype;
    }

    public String getFiletype() 
    {
        return filetype;
    }
    public void setSavename(String savename) 
    {
        this.savename = savename;
    }

    public String getSavename() 
    {
        return savename;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filename", getFilename())
            .append("filepath", getFilepath())
            .append("filetype", getFiletype())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .append("savename", getSavename())
            .toString();
    }
}
