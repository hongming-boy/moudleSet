package com.ruoyi.moudleSet.utils;


import static com.ruoyi.moudleSet.utils.OfficeToPdf.*;

/**
 * 区分文件类型的工具类
 * @author 王泓明
 * @date 2019-9-3
 */
public class OfficeUtils {

    private static final String STR = ".";

    /**
     * 根据给定的文件名称（含后缀，例如：xxx.doc）区分返回存储值类型
     * @param fileName 文件名称（含后缀）
     * @return int类型 -1：未识别(不匹配文件类型或没有包含后缀），1：world，2：excel，3：pdf，4：txt
     */
    public static int distinguishType(String fileName){

        if(!fileName.contains(STR)){
            return -1;
        }

        String suffix = fileName.substring(fileName.lastIndexOf(STR)+1);

        if(SUFFIX_DOC.equals(suffix)||SUFFIX_DOCX.equals(suffix)){
            return 1;
        }

        if(SUFFIX_XLS.equals(suffix)||SUFFIX_XLSX.equals(suffix)){
            return 2;
        }

        if(SUFFIX_PDF.equals(suffix)){
            return 3;
        }

        if(SUFFIX_TXT.equals(suffix)){
            return 4;
        }

        return -1;
    }

    /**
     * 根据传递过来的文件名进行替换文件后缀
     * @param fileName 文件名称
     * @author 王泓明
     * @return
     */
    public static String replaceAnnexName (String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf(STR)+1);
        String AnnexName = "";
        if(SUFFIX_XLS.equals(suffix)||SUFFIX_XLSX.equals(suffix)){
            AnnexName = fileName.substring(0,fileName.lastIndexOf(STR)+1)+"html";
        }else if(SUFFIX_DOC.equals(suffix)||SUFFIX_DOCX.equals(suffix)||SUFFIX_PPT.equals(suffix)){
            AnnexName = fileName.substring(0,fileName.lastIndexOf(STR)+1)+"pdf";
        }else{
            // TODO 其他类型的文件暂不处理
        }
        return AnnexName;
    }



}
