package com.gotech.tv.launcher.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.gotech.tv.launcher.util.Constant;

public abstract class BaseJsonParser<Result> {
    public abstract Result parse(JSONObject data) throws DataParseException;

    public static JSONObject convert(String data) throws DataParseException {
        if (data == null || data.length() <= 0) {
            throw new DataParseException(Constant.ParseData_FormatWrong, "no data back");
        }
        String jsonString = data.trim();
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new DataParseException(Constant.ParseData_FormatWrong, jsonString);
        }
    }

    public static JSONObject convert(byte[] bytes) throws DataParseException {
        if (bytes == null || bytes.length <= 0) {
            throw new DataParseException(Constant.ParseData_FormatWrong, "no data back");
        }
        return convert(new String(bytes));
    }
}