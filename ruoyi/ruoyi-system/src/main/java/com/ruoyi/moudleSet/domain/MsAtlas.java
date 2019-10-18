package com.ruoyi.moudleSet.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 地图对象 sys_atlas
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public class MsAtlas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 创建人 */
    @Excel(name = "创建人")
    private String name;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String zwd;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setZwd(String zwd) 
    {
        this.zwd = zwd;
    }

    public String getZwd() 
    {
        return zwd;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("zwd", getZwd())
            .append("address", getAddress())
            .toString();
    }
}
