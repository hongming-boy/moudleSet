package com.ruoyi.moudleSet.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * office文件转换pdf、html的工具类
 * 支持world转pdf（doc、docx）、excel转html（xls、xlsx）、excel转pdf（xls、xlsx）
 * @author 王泓明
 * @date 2019-9-3
 */
public class OfficeToPdf implements Runnable  {
    /**
     *  PDF 格式
     */
    public static final int WORLD_TO_PDF = 17;
    public static final int EXCEL_HTML = 44;
    /**
     *  文件后缀类型
     */
    public static final String SUFFIX_DOC = "doc";
    public static final String SUFFIX_DOCX = "docx";
    public static final String SUFFIX_XLS = "xls";
    public static final String SUFFIX_XLSX = "xlsx";
    public static final String SUFFIX_PDF = "pdf";
    public static final String SUFFIX_TXT = "txt";
    public static final String SUFFIX_PPT = "ppt";

    /** 源文件（包含路径，例如：C:WINDOW/USERS/xxxx.xls,xlsx,doc,docx）*/
    private String filePath;
    /** 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf,html） */
    private String outFilePath;
    /** 文件类型的后缀，例如: doc,docx,xls,xlsx */
    private String suffixType;

    /** 构造器 */
    public OfficeToPdf(String filePath,String outFilePath,String suffixType){
        this.filePath = filePath;
        this.outFilePath = outFilePath;
        this.suffixType = suffixType;
    }

    /**
     * world转换pdf文件
     * 支持doc，docx版本的world转换成pdf
     * @param sfileName 源文件（包含路径，例如：C:WINDOW/USERS/xxxx.doc，docx）
     * @param toFileName 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf）
     * @return
     * @throws Exception
     * @author ygl
     */
    public static int wordToPdf(String sfileName,String toFileName) throws Exception{

        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
           /**  打开word文件  */
            Dispatch docs = app.getProperty("Documents").toDispatch();
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] {
                    sfileName, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
            File tofile = new File(toFileName);
            if (tofile.exists()) {
                tofile.delete();
            }
            /**作为html格式保存到临时文件：
             * new Variant(8)
             * 其中8表示word转html;
             * 7表示word转txt;
             * 44表示Excel转html;
             * 17表示word转成pdf。*/
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    toFileName, new Variant(WORLD_TO_PDF) }, new int[1]);
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        }catch(Throwable t){
            t.printStackTrace();
        } finally {
            /** 关闭word  */
            Dispatch.call(doc,"Close",false);
            if (app != null){
                app.invoke("Quit", new Variant[] {});
            }
        }
        /** 如果没有这句winword.exe进程将不会关 */
        ComThread.Release();
        return 1;
    }
    private static Document read(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlFile);
    }
    public  int getDocPageSize(String filePath)  throws Exception {
        XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(filePath));
        /**   总页  */
        int pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        /**  忽略空格的字符另外还有getCharactersWithSpaces()方法获取带空格的总字数�?   */
        int wordCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();
        return pages;
    }
    /**
     * excel文件转换成pdf （xls,xlsx）
     * 当前excel转pdf方法有弊端，暂不使用（转换后的文件会出现列过多分页的现象）
     * @date 2019-9-3
     * @param inFilePath 源文件（包含路径，例如：C:WINDOW/USERS/xxxx.xls，xlsx）
     * @param outFilePath 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf）
     */
    public static void excelToPdf(String inFilePath, String outFilePath) throws Exception {
        ActiveXComponent ax = null;
        Dispatch excel = null;
        try {
            ComThread.InitSTA();
            ax = new ActiveXComponent("Excel.Application");
            ax.setProperty("Visible", new Variant(false));
            /** 禁用宏  */
            ax.setProperty("AutomationSecurity", new Variant(3));
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();
            Object[] obj = new Object[]{
                    inFilePath,
                    new Variant(false),
                    new Variant(false)
            };
            excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();
            File tofile = new File(outFilePath);
            if (tofile.exists()) {
                tofile.delete();
            }
            /** 转换格式    */
            Object[] obj2 = new Object[]{
                    /** PDF格式=0    */
                    new Variant(0),
                    outFilePath,
                    /** 0=标准 (生成的PDF图片不会变模糊) ; 1=最小文件*/
                    new Variant(0)
            };
            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method,obj2, new int[1]);
        } catch (Exception es) {
            es.printStackTrace();
            throw es;
        } finally {
            if (excel != null) {
                Dispatch.call(excel, "Close", new Variant(false));
            }
            if (ax != null) {
                ax.invoke("Quit", new Variant[] {});
                ax = null;
            }
            ComThread.Release();
        }
    }

    /**
     * 这是excek转换html的文件
     * @param xlsfile 源文件（包含路径，例如：C:WINDOW/USERS/xxxx.xls，xlsx）
     * @param htmlfile 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf）
     */
    public static void excelToHtml(String xlsfile, String htmlfile)
    {
       /**  启动Excel */
        ActiveXComponent app = new ActiveXComponent("Excel.Application");
        try {
            app.setProperty("Visible", new Variant(false));
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.invoke(
                    excels,
                    "Open",
                    Dispatch.Method,
                    new Object[] { xlsfile, new Variant(false),new Variant(true) },
                    new int[1]).toDispatch();
            Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {
                    htmlfile, new Variant(EXCEL_HTML) }, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(excel, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[] {});
        }
    }

    /**
     * ppt文件转换PDF文件
     * @param srcFilePath 源文件（包含路径，例如：C:WINDOW/USERS/xxxx.ppt）
     * @param pdfFilePath 转换后的文件（包含路径，例如：C:WINDOW/USERS/xxxx.pdf）
     * @return
     */
    public boolean ppt2PDF(String srcFilePath, String pdfFilePath) {
        ActiveXComponent app = null;
        Dispatch ppt = null;
        boolean result = true;
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("PowerPoint.Application");
            Dispatch ppts = app.getProperty("Presentations").toDispatch();

            /**  因POWER.EXE的发布规则为同步，所以设置为同步发布 */
            ppt = Dispatch.call(ppts, "Open", srcFilePath,
                    /**  ReadOnly 设置只读*/
                    true,
                    /** Untitled指定文件是否有标题 */
                    true,
                    /** WithWindow指定文件是否可见 */
                    false
            ).toDispatch();
            // ppSaveAsPDF为特定值32
            Dispatch.call(ppt, "SaveAs", pdfFilePath, 32);
            System.out.println("转换文档到 PDF..." + pdfFilePath);
            /** set flag true */
            result = true;
        } catch (ComFailException e) {
            result = false;
        } catch (Exception e) {
            result = false;
        } finally {
            if (ppt != null) {
                Dispatch.call(ppt, "Close");
            }
            if (app != null) {
                app.invoke("Quit");
            }
            ComThread.Release();
        }
        return result;
    }

    @Override
    public void run() {
        /** 校验文件后缀是否是doc、docx类型的文件
         *  如果是此类型的文件则转换为pdf，如果不是则再次
         *  校验文件后缀是否是xls和xlsx类型的文件
         *  如果是此类型的文件则转换成html
         *  校验文件后缀是否是ppt类型的文件
         *  如果是此类型的文件则转换成pdf
         *  如果不是以上类型的文件则自动忽略 */
        if(SUFFIX_DOC.equals(suffixType)||SUFFIX_DOCX.equals(suffixType)){
            try {
                wordToPdf(filePath,outFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(SUFFIX_XLS.equals(suffixType)||SUFFIX_XLSX.equals(suffixType)){
            /** excel转换pdf */
            excelToHtml(filePath,outFilePath);
        }else if(SUFFIX_PPT.equals(suffixType)){
            /** ppt转换pdf */
            ppt2PDF(filePath,outFilePath);
        }else{
            /** 除了以上三种类型的文件其他的自动忽略 */
        }
    }

}
