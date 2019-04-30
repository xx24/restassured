package com.demo.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private static XSSFWorkbook ExcelWBooK;
    private static XSSFSheet ExcelSheet;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    /**
     * @param path:设置要操作的Excel路径
     */
    public static void setExcelFile(String path) {
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            ExcelWBooK = new XSSFWorkbook(ExcelFile);
        } catch (IOException e) {
            System.out.println("Excel路径设置失败");
            e.printStackTrace();
        }
    }

    /**
     * @param path：设置要操作的excel路径
     * @param sheetName：设置要操作的sheet
     */
    public static void setExcelFile(String path, String sheetName) {
        FileInputStream ExcelFile;
        try {
            ExcelFile = new FileInputStream(path);
            ExcelWBooK = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBooK.getSheet(sheetName);
        } catch (IOException e) {
            System.out.println("Excel路径设置失败");
            e.printStackTrace();
        }
    }

    public static String getCellData(String sheetName, int row, int col) {
        ExcelSheet = ExcelWBooK.getSheet(sheetName);
        try {
            Cell = ExcelSheet.getRow(row).getCell(col);
            //判断cell格式是否为String，String类型的话就直接用Cell.getStringCellValue()，不是String的话用另外的get方法
            String CellData = Cell.getCellType() == CellType.STRING ? Cell.getStringCellValue() + ""
                    : String.valueOf(Math.round(Cell.getNumericCellValue()));
            return CellData;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取sheet中总行数
    public static int getRowCount(String SheetName) {
        ExcelSheet = ExcelWBooK.getSheet(SheetName);
        int RowCount = ExcelSheet.getLastRowNum();
        return RowCount;
    }

    /**
     * @param sheetName：要操作的sheet名字
     * @param testCaseName：sheet中第一列的casename
     * @return
     */
    public static int getFirstRowContainsCaseId(String sheetName, String testCaseName) {
        int i;
        try {
            int rowCount = ExcelUtil.getRowCount(sheetName);
            for (i = 0; i < rowCount; i++) {
                if (ExcelUtil.getCellData(sheetName, i, Constants.COL_CASENAME).equalsIgnoreCase(testCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getLastRowContainsCaseId(String sheetName, String testCaseName, int firstStep) {
        try {
            for (int i = firstStep; i < ExcelUtil.getRowCount(sheetName); i++) {
                if (!testCaseName.equalsIgnoreCase(ExcelUtil.getCellData(sheetName, i, Constants.COL_CASENAME))) {
                    return i;
                }
            }
            return ExcelUtil.getRowCount(sheetName)+1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

