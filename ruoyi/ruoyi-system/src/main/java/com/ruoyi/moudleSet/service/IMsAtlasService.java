package com.ruoyi.moudleSet.service;

import com.ruoyi.moudleSet.domain.MsAtlas;
import com.ruoyi.moudleSet.domain.MsAtlas;

import java.util.List;

/**
 * 地图Service接口
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
public interface IMsAtlasService
{
    /**
     * 查询地图
     * 
     * @param id 地图ID
     * @return 地图
     */
    public MsAtlas selectMsAtlasById(Long id);

    /**
     * 查询地图列表
     * 
     * @param msAtlas 地图
     * @return 地图集合
     */
    public List<MsAtlas> selectMsAtlasList(MsAtlas msAtlas);

    /**
     * 新增地图
     * 
     * @param msAtlas 地图
     * @return 结果
     */
    public int insertMsAtlas(MsAtlas msAtlas);

    /**
     * 修改地图
     * 
     * @param msAtlas 地图
     * @return 结果
     */
    public int updateMsAtlas(MsAtlas msAtlas);

    /**
     * 批量删除地图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMsAtlasByIds(String ids);

    /**
     * 删除地图信息
     * 
     * @param id 地图ID
     * @return 结果
     */
    public int deleteMsAtlasById(Long id);
}
