package cc.zhanyun.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

    public static String excelToHtml(String excelPath, String htmlpath) {
        String htmlName = null;
        try {
            // 查找文件,输入流
            InputStream input = new FileInputStream(excelPath);
            HSSFWorkbook excelBook = new HSSFWorkbook(input);
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
            System.out.println(pics.size());
            // 解析文档
            Document htmlDocument = excelToHtmlConverter.getDocument();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            outStream.close();

            String content = new String(outStream.toByteArray());
            htmlName = RandomUtil.getRandomFileName() + ".html";
            FileUtils.writeStringToFile(new File(htmlpath, htmlName), content,
                    "utf-8");
        } catch (Exception e) {
            htmlName = null;
        }
        return htmlName;
    }
}
