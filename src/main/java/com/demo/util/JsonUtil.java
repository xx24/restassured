package com.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtil {
    /**
     * @return ：以string形式放回json文件中期望值
     * @throws IOException
     */
    public static String getJsonDataString(String sheetName, int row) throws IOException {
        String input = FileUtils.readFileToString(new File(Constants.PATH_JSON), "UTF-8");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(input, JsonObject.class);
        JsonElement element = jsonObject.get(ExcelUtil.getCellData(sheetName, row, Constants.COL_EXPECT));
        return element.toString();
    }

    /**
     * @return 以map形式返回json文件中请求值
     * @throws IOException
     */
    public static Map getJsonDataMap(String sheetName, int row) throws IOException {
        String input = FileUtils.readFileToString(new File(Constants.PATH_JSON), "UTF-8");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(input, JsonObject.class);
        JsonElement element = jsonObject.get(ExcelUtil.getCellData(sheetName, row, Constants.COL_DATA));
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(element.toString(), type);
        return map;
    }
}
