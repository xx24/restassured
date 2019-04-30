package com.demo.test;

import com.demo.util.Constants;
import com.demo.util.ExcelUtil;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBySuite {
    public static String caseName;
    public static String caseRunFlag;

    public static void main(String args[]) {
        XmlSuite suite = new XmlSuite();
        ExcelUtil.setExcelFile(Constants.PATH_EXCEL);
        int sheetCount = ExcelUtil.getRowCount(Constants.SHEET_SUITNAME);
        for (int sheetNo = 1; sheetNo <= sheetCount; sheetNo++) {
            caseName = ExcelUtil.getCellData(Constants.SHEET_SUITNAME, sheetNo, Constants.COL_CASENAME);
            caseRunFlag = ExcelUtil.getCellData(Constants.SHEET_SUITNAME, sheetNo, Constants.COL_RUNFLAG);
            if (caseRunFlag.equalsIgnoreCase("y")) {
                XmlTest test = new XmlTest();
                suite.addTest(test);
                test.setName(caseName);
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("sheetName", caseName);
//                parameters.put("testCaseNo", String.valueOf(sheetNo));
                test.setParameters(parameters);
                List<XmlClass>classes=new ArrayList<XmlClass>();
                classes.add(new XmlClass(Constants.PATH_TESTBYEXCEL));
                test.setClasses(classes);
            }
        }
        List<XmlSuite>suites=new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG testNG=new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }


}
