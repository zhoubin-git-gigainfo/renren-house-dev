package io.renren;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import io.renren.common.utils.NumberToCNUtils;
import io.renren.modules.house.entity.TsReportEntity;
import io.renren.modules.house.entity.TuReportEntity;
import io.renren.modules.house.service.TsReportService;
import io.renren.modules.house.service.TuReportService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.service.DataSourceTestService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.*;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Autowired
    private TsReportService tsReportService;

    @Autowired
    private TuReportService tuReportService;

    @Test
    public void testNumber() {
        double money = 2020004.01;
        BigDecimal numberOfMoney = new BigDecimal(money);
        String s = NumberToCNUtils.number2CNMontrayUnit(numberOfMoney);
        System.out.println("你输入的金额为：【" + money + "】   #--# [" + s.toString() + "]");
    }

    @Test
    public void testDataSource() {
        //数据源1
        SysUserEntity user1 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user1));

        //数据源2
        SysUserEntity user2 = dataSourceTestService.queryUser2(1L);
        System.out.println(ToStringBuilder.reflectionToString(user2));

        //数据源1
        SysUserEntity user3 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user3));
    }

    @Test
    public void testTuReportEntity() throws IOException {
        TuReportEntity t = tuReportService.selectById("e1ff3718-f087-4277-bbfa-d55ceed5ada4");
        OutputStream fos = new FileOutputStream("E://12341111.pdf");
        fos.write(t.getrFile());
        fos.close();
    }

    @Test
    public void testInsertTsReportEntity() throws IOException {
        InputStream in = new FileInputStream("E://存量房示范文本.pdf");
        byte[] data = toByteArray(in);
        TsReportEntity tsReportEntity = new TsReportEntity();
        tsReportEntity.setId(UUID.randomUUID().toString());
        tsReportEntity.setCreatedate(new Date());
        tsReportEntity.setFile(data);
        tsReportEntity.setName("存量房示范文本");
        tsReportService.insertFile(tsReportEntity);
        in.close();
    }


    @Test
    public void testPDF() throws Exception {
        Map map = new HashMap();
        Map map1 = new HashMap();
        List list = new ArrayList();
        map1.put("businessNo", "1214");
        map1.put("contractNo", "657676767766727272");
        map.put("sellerName", "甲方姓名");
        map.put("sellerIc", "甲方身份证");
        map.put("sellerAgentName", "甲方代理人");
        map.put("sellerAgentIc", "代理人身份证");
        map.put("buyerName", "乙方姓名");
        map.put("buyerIc", "乙方身份证");
        map.put("buyerAgentName", "代理人姓名");
        map.put("buyerAgentIc", "代理人Ic");
        map.put("lname", "坐落");
        map.put("hdesc", "2301");
        map.put("cdno", "289473247205723489");
        map.put("barea", "88.00");
        map.put("huse", "88.00");
        map.put("price", "54000");
        map.put("priceC", "伍拾肆万元整");
        map.put("payMethod", "1");
        map.put("payCustom", "无");
        map.put("taxation", "1");
        map.put("taxationCustom", "无");
        map.put("term", "15");
        map.put("responsibility", "啥看法橘红色的规范化四高回报森林防火宿管发乎吧䒑");
        map.put("dispute", "1");
        map.put("matter", "的覅啊速度快 阿杜花开了大货车啊刘德华");
        list.add(map);
        convertTransData(list, map1);
        System.out.println("执行完毕");
    }

    public OutputStream testBarCode(String strInfo) throws IOException {

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

    /**
     * 将数据转换为输入字节流
     */
    private InputStream convertTransData(List input, Map map) throws Exception {
        if (input == null || input.isEmpty() || input.get(0) == null)
            return null;
        try {
            TsReportEntity t = tsReportService.selectById("45708f9f-0a10-4d4d-8123-4f12ce37976f");
            InputStream in = new ByteArrayInputStream(t.getFile());

            ByteArrayOutputStream out = (ByteArrayOutputStream) generate(new PdfReader(in), (Map) input.get(0), map);
            ByteArrayInputStream ret = new ByteArrayInputStream(out.toByteArray());

            TuReportEntity tuReportEntity = new TuReportEntity();
            tuReportEntity.setrId(UUID.randomUUID().toString());
            tuReportEntity.setCreatedate(new Date());
            tuReportEntity.setrFile(out.toByteArray());
            tuReportEntity.setrName("存量");
            tuReportEntity.setcId("");
            tuReportService.insertFile(tuReportEntity);

            out.close();
            return ret;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 将数据，填入pdf表单域
     */
    public OutputStream generate(PdfReader template, Map data, Map map) throws Exception {
//        BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamp = new PdfStamper(template, out);
            AcroFields form = stamp.getAcroFields();
            // set the field values in the pdf form
            for (Iterator it = data.keySet().iterator(); it.hasNext(); ) {
                String key = (String) it.next();
                String value = (String) data.get(key);
                form.setFieldProperty(key, "textfont", bfChinese, null);
                form.setField(key, value);
            }
            // 通过域名获取所在页和坐标，左下角为起点
            if (null != map) {
                for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next() + "";
                    String vuale = map.get(key) + "";
                    form.getFieldPositions(key).stream().filter(c -> c != null).forEach(fieldPosition -> {
                        com.itextpdf.text.Rectangle signRect = fieldPosition.position;
                        float x = signRect.getLeft();
                        float y = signRect.getBottom();
                        // 读图片
                        com.itextpdf.text.Image image = null;
                        try {
                            // 提取pdf中的表单
                            image = com.itextpdf.text.Image.getInstance((((ByteArrayOutputStream) testBarCode(vuale)).toByteArray()));
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
     * 文件流--转byte数组
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}
