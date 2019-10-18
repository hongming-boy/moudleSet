package com.ruoyi.moudleSet.service.impl;

import com.ruoyi.common.core.text.Convert;

import com.ruoyi.moudleSet.domain.MsAtlas;
import com.ruoyi.moudleSet.mapper.MsAtlasMapper;
import com.ruoyi.moudleSet.service.IMsAtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地图Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-10-16
 */
@Service
public class MsAtlasServiceImpl implements IMsAtlasService
{
    @Autowired
    private MsAtlasMapper MsAtlasMapper;

    /**
     * 查询地图
     * 
     * @param id 地图ID
     * @return 地图
     */
    @Override
    public MsAtlas selectMsAtlasById(Long id)
    {
        return MsAtlasMapper.selectMsAtlasById(id);
    }

    /**
     * 查询地图列表
     * 
     * @param msAtlas 地图
     * @return 地图
     */
    @Override
    public List<MsAtlas> selectMsAtlasList(MsAtlas msAtlas)
    {
        return MsAtlasMapper.selectMsAtlasList(msAtlas);
    }

    /**
     * 新增地图
     * 
     * @param msAtlas 地图
     * @return 结果
     */
    @Override
    public int insertMsAtlas(MsAtlas msAtlas)
    {
        return MsAtlasMapper.insertMsAtlas(msAtlas);
    }

    /**
     * 修改地图
     * 
     * @param msAtlas 地图
     * @return 结果
     */
    @Override
    public int updateMsAtlas(MsAtlas msAtlas) { return MsAtlasMapper.updateMsAtlas(msAtlas); }

    /**
     * 删除地图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMsAtlasByIds(String ids)
    {
        return MsAtlasMapper.deleteMsAtlasByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地图信息
     * 
     * @param id 地图ID
     * @return 结果
     */
    public int deleteMsAtlasById(Long id)
    {
        return MsAtlasMapper.deleteMsAtlasById(id);
    }
}
