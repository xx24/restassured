package com.demo.util;

public class Constants {
    public static final String PATH_EXCEL = "/workspace/restassured/src/main/resources/data.xlsx";
    public static final String PATH_JSON = "/workspace/restassured/src/main/resources/data.json";
    public static final String PATH_TESTBYEXCEL="com.demo.test.TestByExcel";

    /**
     * 获取每个sheet中每一列的列数
     */
    public static final int COL_CASENAME=0;
    public static final int COL_URL=3;
    public static final int COL_METHOD=4;
    public static final int COL_DATA=5;
    public static final int COL_EXPECT=6;

    public static final int COL_RUNFLAG=2;
    public static final String SHEET_SUITNAME="suite";

}
