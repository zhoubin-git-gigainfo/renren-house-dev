package io.renren.common.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

public class ITextUtil {


    /**
     * 数据表单填入
     *
     * @param template 模板
     * @param from     表单信息
     * @param barCode      条形码
     * @return
     * @throws Exception
     */
    public static OutputStream generate(PdfReader template, Map from, Map barCode) throws Exception {

        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamp = new PdfStamper(template, out);
            AcroFields form = stamp.getAcroFields();
            // set the field values in the pdf form
            /**
             * 表单值填入
             */
            for (Iterator it = from.keySet().iterator(); it.hasNext(); ) {
                String key = (String) it.next();
                String value = (String) from.get(key);
                form.setFieldProperty(key, "textfont", bfChinese, null);
                form.setField(key, value);
            }
            /**
             * 条形码--存在多个
             */
            if (null != barCode) {
                for (Iterator it = barCode.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next() + "";
                    String vuale = barCode.get(key) + "";
                    form.getFieldPositions(key).stream().filter(c -> c != null).forEach(fieldPosition -> {
                        // 通过域名获取所在页和坐标，左下角为起点
                        Rectangle signRect = fieldPosition.position;
                        float x = signRect.getLeft();
                        float y = signRect.getBottom();
                        // 读图片
                        Image image = null;
                        try {
                            // 提取pdf中的表单
                            image = Image.getInstance((((ByteArrayOutputStream) barCode(vuale)).toByteArray()));
                            // 获取操作的页面
                            PdfContentByte under = stamp.getOverContent(fieldPosition.page);
                            // 根据域的大小缩放图片
                            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                            // 添加图片
                            image.setAbsolutePosition(x, y);
                            under.addImage(image);
                        } catch (BadElementException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            stamp.setFormFlattening(true);
            stamp.close();
            template.close();
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 生成条形码
     *
     * @param strInfo
     * @return
     * @throws IOException
     */
    public static OutputStream barCode(String strInfo) throws IOException {
        int barCodeWidth = 180;
        int barCodeHeight = 60;
        int HEIGHT_SPACE = 20;
        //图片宽度
        int imageWidth = barCodeWidth;
        // 图片高度
        int imageHeight = barCodeHeight + HEIGHT_SPACE;
        BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.fillRect(0, 0, imageWidth, imageHeight);
        Font font = new java.awt.Font("", java.awt.Font.PLAIN, 12);
        Barcode128 barcode128 = new Barcode128();
        FontRenderContext fontRenderContext = g.getFontRenderContext();
        //条形码（文字）的高度
        int stringHeight = (int) font.getStringBounds("", fontRenderContext).getHeight();
        // 图片横坐标开始位置
        int startX = 0;
        // 图片纵坐标开始位置
        int imageStartY = 0;
        int stringStartY = imageHeight - 8;// 条形码（文字）开始位置

        int codeWidth = (int) font.getStringBounds(strInfo, fontRenderContext).getWidth();
        barcode128.setCode(strInfo);
        java.awt.Image codeImg = barcode128.createAwtImage(Color.black, Color.white);

        g.drawImage(codeImg, startX, imageStartY, barCodeWidth, barCodeHeight, Color.white, null);

        //为图片添加条形码（文字），位置为条形码图片的下部居中
        AttributedString ats = new AttributedString(strInfo);
        ats.addAttribute(TextAttribute.FONT, font, 0, strInfo.length());
        AttributedCharacterIterator iter = ats.getIterator();

        // 设置条形码（文字）的颜色
        g.setColor(Color.BLACK);
        // 绘制条形码（文字）
        g.drawString(iter, startX + (barCodeWidth - codeWidth) / 2, stringStartY);
        g.dispose();

        OutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "PNG", os);
        return os;
    }
}
