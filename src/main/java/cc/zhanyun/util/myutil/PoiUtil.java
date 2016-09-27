package cc.zhanyun.util.myutil;


import java.util.Iterator;

import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;


public class PoiUtil {
    /**
     * 复制单元格样式
     *
     * @param fromStyle
     * @param toStyle
     */
    public static void copyCellStyle(HSSFCellStyle fromStyle,
                                     HSSFCellStyle toStyle, Workbook workbook) {
        toStyle.setAlignment(fromStyle.getAlignment());
        // 边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
        toStyle.setFont(fromStyle.getFont(workbook));

        // 背景和前景
        toStyle.setFillBackgroundColor(fromStyle
                .getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle
                .getFillForegroundColor());
        // 数据格式
        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());

        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());// 首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());// 旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());
        toStyle.setFont(fromStyle.getFont(workbook));

    }

    /**
     * 复制sheet
     *
     * @param wb
     * @param fromSheet
     * @param toSheet
     * @param copyValueFlag
     */
    public static void copySheet(HSSFWorkbook wb, HSSFSheet fromSheet,
                                 HSSFSheet toSheet, boolean copyValueFlag) {
        mergerRegion(fromSheet, toSheet);

        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
            HSSFRow tmpRow = (HSSFRow) rowIt.next();
            HSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());

            copyRow(wb, tmpRow, newRow, copyValueFlag);
        }
    }

    /**
     * 复制行
     *
     * @param wb
     * @param fromRow
     * @param toRow
     * @param copyValueFlag
     */
    public static void copyRow(HSSFWorkbook wb, HSSFRow fromRow,
                               HSSFRow toRow, boolean copyValueFlag) {
        for (Iterator<?> cellIt = fromRow.cellIterator(); cellIt
                .hasNext(); ) {
            HSSFCell tmpCell = (HSSFCell) cellIt.next();
            HSSFCell newCell = toRow.createCell(tmpCell.getCellNum());
            copyCell(wb, tmpCell, newCell, copyValueFlag);

        }
    }

    /**
     * @param fromSheet
     * @param toSheet
     */
    public static void mergerRegion(HSSFSheet fromSheet, HSSFSheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            Region mergedRegionAt = fromSheet.getMergedRegionAt(i);
            toSheet.addMergedRegion(mergedRegionAt);
        }
    }

    /**
     * 复制单元格
     *
     * @param wb
     * @param srcCell
     * @param distCell
     * @param copyValueFlag
     */
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell,
                                HSSFCell distCell, boolean copyValueFlag) {
        HSSFCellStyle newstyle = wb.createCellStyle();

        copyCellStyle(srcCell.getCellStyle(), newstyle, wb);


        distCell.setCellStyle(newstyle);

        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }

        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == 0) {
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell
                            .getNumericCellValue());
                }
            } else if (srcCellType == 1) {
                distCell
                        .setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType != 3) {
                if (srcCellType == 4) {
                    distCell.setCellValue(srcCell
                            .getBooleanCellValue());
                } else if (srcCellType == 5) {
                    distCell.setCellErrorValue(srcCell
                            .getErrorCellValue());
                } else if (srcCellType == 2) {
                    distCell.setCellFormula(srcCell.getCellFormula());
                }
            }
        }
    }

    /**
     * 插入空白行
     *
     * @param sheet
     * @param rowIndex
     * @param rows
     */
    public static void insertRow(HSSFSheet sheet, Integer rowIndex, Integer rows) {
        if (sheet.getRow(rowIndex.intValue()) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex.intValue(), lastRowNo,
                    rows.intValue());
        }
        sheet.createRow(rowIndex.intValue());
    }

}
