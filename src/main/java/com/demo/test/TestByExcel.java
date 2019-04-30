package com.demo.test;

import com.demo.helper.ApiHelper;
import com.demo.util.Constants;
import com.demo.util.ExcelUtil;
import com.demo.util.JsonUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class TestByExcel {
    public static String keyword;
    public static String url;
    public static Map data;
    public static String expect;
    public static int testStep;
    public static int testLastStep;

    @Parameters({"sheetName"})
    @Test
    public void operationExcel(String sheetName) throws Exception{
        testStep = ExcelUtil.getFirstRowContainsCaseId(sheetName, sheetName);
        testLastStep = ExcelUtil.getLastRowContainsCaseId(sheetName, sheetName, testStep);
        ApiHelper apiHelper = new ApiHelper();
        for (; testStep < testLastStep; testStep++) {
            keyword = ExcelUtil.getCellData(sheetName, testStep, Constants.COL_METHOD);
            url = ExcelUtil.getCellData(sheetName, testStep, Constants.COL_URL);
            data = JsonUtil.getJsonDataMap(sheetName, testStep);
          Method method =  ApiHelper.class.getMethod(keyword,String.class,Map.class);
                  method.invoke(apiHelper,url,data);


        }
    }
}
