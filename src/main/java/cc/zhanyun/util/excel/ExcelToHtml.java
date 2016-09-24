package cc.zhanyun.util.excel;

import java.io.*;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;

import cc.zhanyun.util.RandomUtil;

public class ExcelToHtml {
    /**
     * excel转换为HTMLString字符串
     *
     * @param excelPath
     * @return
     */

    public static String excelToHtmlString(String excelPath) {
        String content = null;
        try {
            InputStream inputStream = new FileInputStream(excelPath);
            content = inputStreamToString(inputStream);
            inputStream.close();
        } catch (Exception e) {

        }
        return content;
    }

    /**
     * 输入流转换为String
     *
     * @param input
     * @return
     */
    public static String inputStreamToString(InputStream input) {
        HSSFWorkbook excelBook = null;
        ByteArrayOutputStream outStream = null;
        String content = null;
        try {
            excelBook = new HSSFWorkbook(input);

            excelBook.removeSheetAt(1);

            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .newDocument());
            // 设置属性值
            excelToHtmlConverter.setOutputColumnHeaders(false);
            excelToHtmlConverter.setOutputHiddenColumns(false);
            excelToHtmlConverter.setOutputHiddenRows(false);
            excelToHtmlConverter.setOutputLeadingSpacesAsNonBreaking(false);
            excelToHtmlConverter.setOutputRowNumbers(false);
            excelToHtmlConverter.setUseDivsToSpan(false);
            excelToHtmlConverter.processWorkbook(excelBook);

            // 获取文档中图片
            List<HSSFPictureData> pics = excelBook.getAllPictures();
            // System.out.println(pics.size());
            // 解析文档
            Document htmlDocument = excelToHtmlConverter.getDocument();
            outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            content = new String(outStream.toByteArray());
            //关闭流
            outStream.close();
            input.close();
        } catch (Exception e) {
            //  e.printStackTrace();
            content = null;
        }
        return content;

    }

    /**
     * excel转化html文件
     *
     * @param excelPath
     * @param htmlpath
     * @return
     */
    public static String excelToHtmlFile(String excelPath, String htmlpath) {

        String content = excelToHtmlString(excelPath);
        String htmlName = RandomUtil.getRandomFileName() + ".html";
        try {
            FileUtils.writeStringToFile(new File(htmlpath, htmlName), content,
                    "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlpath + htmlName;
    }
}
