package cc.zhanyun.util.excel;


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
    public static void copySheet(HSSFWorkbook wb, HSSFSheet fromSheet, HSSFSheet toSheet, boolean copyValueFlag) {
        mergerRegion(fromSheet, toSheet);
        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
            HSSFRow tmpRow = (HSSFRow) rowIt.next();
            HSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());
            //复制
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
    public static void copyRow(HSSFWorkbook wb, HSSFRow fromRow, HSSFRow toRow, boolean copyValueFlag) {
        for (Iterator<?> cellIt = fromRow.cellIterator(); cellIt
                .hasNext(); ) {
            HSSFCell tmpCell = (HSSFCell) cellIt.next();
            HSSFCell newCell = toRow.createCell(tmpCell.getCellNum());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }
    }

    /**
     * 复制 sheet
     *
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
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell, HSSFCell distCell, boolean copyValueFlag) {
        HSSFCellStyle newstyle = wb.createCellStyle();
        //复制单元格样式
        copyCellStyle(srcCell.getCellStyle(), newstyle, wb);
        //设置新样式
        distCell.setCellStyle(newstyle);
        //
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


    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            //获取位置
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {

        if (cell == null) return " ";

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

            return String.valueOf(cell.getBooleanCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

            return cell.getCellFormula();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

            return String.valueOf(cell.getNumericCellValue());

        }
        return " ";
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        //获取单元格的数量
        int sheetMergeCount = sheet.getNumMergedRegions();
        //遍历操作
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }


}
