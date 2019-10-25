package com.ruoyi.moudleSet.utils;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.SwingUtilities;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * @author 王泓明
 */
public class TestWorldToJpg {

    public static void setup(String pdfPath,String imgPath) throws IOException {

        File file = new File(
                pdfPath);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel
                .size());
        PDFFile pdffile = new PDFFile(buf);

        System.out.println("页数： " + pdffile.getNumPages());

        for (int i = 1; i <= pdffile.getNumPages(); i++) {
            // draw the first page to an image
            PDFPage page = pdffile.getPage(i);

            // get the width and height for the doc at the default zoom
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
                    .getWidth(), (int) page.getBBox().getHeight());

            // generate the image
            Image img = page.getImage(rect.width, rect.height, // width &
                    // height
                    rect, // clip rect
                    null, // null for the ImageObserver
                    true, // fill background with white
                    true // block until drawing is done
            );

            BufferedImage tag = new BufferedImage(rect.width, rect.height,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
                    null);
            FileOutputStream out = new FileOutputStream(
                    imgPath
                            +"图片"+ i + ".jpg"); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // JPEG编码

            out.close();
        }


    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TestWorldToJpg.setup("F:\\新建文件夹\\noteBook(1).pdf","F:\\新建文件夹\\");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
