package com.ruoyi.moudleSet.service.impl;


import com.ruoyi.moudleSet.service.IPdfService;
import com.ruoyi.moudleSet.utils.OfficeToPdf;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王泓明
 */
@Service
public class IPdfServiceImpl implements IPdfService {

    ExecutorService sendExecutor = Executors.newFixedThreadPool(30);

    /**
     * Office文件转换为pdf文件
     * @author 王泓明
     * @date 2019-9-20
     * @param filePath  源文件（包含路径，例如：C:WINDOW/USERS/xxxx.xls,xlsx,doc,docx）
     * @param outFilePath 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf,html）
     * @param suffixType 文件类型的后缀，例如: doc,docx,xls,xlsx
     */
    @Override
    public void OfficeToPdf(String filePath, String outFilePath, String suffixType) {
        sendExecutor.execute(new OfficeToPdf(filePath,outFilePath,suffixType));
    }
}
