package com.ruoyi.moudleSet.service;

import org.springframework.stereotype.Repository;

/**
 * Office文件转换pdf的Service层
 * @author 王泓明
 */
@Repository
public interface IPdfService {
    /**
     * Office文件转换为pdf文件
     * @author 王泓明
     * @date 2019-9-20
     * @param filePath  源文件（包含路径，例如：C:WINDOW/USERS/xxxx.xls,xlsx,doc,docx）
     * @param outFilePath 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf,html）
     * @param suffixType 文件类型的后缀，例如: doc,docx,xls,xlsx
     */
    void OfficeToPdf(String filePath, String outFilePath, String suffixType);

}
