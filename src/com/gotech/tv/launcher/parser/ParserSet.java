package com.gotech.tv.launcher.parser;

import com.gotech.tv.launcher.util.Constant;
import com.gotech.tv.launcher.vo.ParseResultVo;
import com.gotech.tv.launcher.vo.UserInfoVo;

import org.json.JSONObject;

public class ParserSet {
    public static class StateParser extends BaseJsonParser<Integer> {
        public static final String KEY_STATE = "returnCode";

        @Override
        public Integer parse(JSONObject data) throws DataParseException {
            int state = data.optInt(KEY_STATE, -1);
            return state;
        }
    }

    public static class JsonBeanParser extends BaseJsonParser<JSONObject> {
        @Override
        public JSONObject parse(JSONObject data) throws DataParseException {
            return data;
        }
    }

    public static class UserInfoParser extends BaseJsonParser<ParseResultVo> {

        @Override
        public ParseResultVo parse(JSONObject data) throws DataParseException {
            ParseResultVo vo = new ParseResultVo();
            vo.result = data.optInt("errorCode", -1);
            vo.reMsg = data.optString("errorInfo");
            vo.obj = new UserInfoVo(data.optString("accountid", ""), data.optString("accountid", "password"));
            return vo;
        }

    }

    /**
     * @author benson CN：登录随机值解析器
     */
    public static class RandomCodeParser extends BaseJsonParser<String> {
        @Override
        public String parse(JSONObject data) throws DataParseException {
            return data.optString("loginCode");
        }
    }

    public static class TokenUpdateParser extends BaseJsonParser<String> {
        @Override
        public String parse(JSONObject data) throws DataParseException {
            return data.optString("newUserToken");
        }
    }

    public static class HeartRunParser extends BaseJsonParser<Integer> {
        @Override
        public Integer parse(JSONObject data) throws DataParseException {
            return data.optInt("status");
        }
    }

    public static class FindUserIdParser extends BaseJsonParser<String> {
        @Override
        public String parse(JSONObject data) throws DataParseException {
            return data.optString("userid");
        }
    }

    public static class HistoryParser extends BaseJsonParser<ParseResultVo> {

        private static final String KEY_DES = "description";

        @Override
        public ParseResultVo parse(JSONObject data) throws DataParseException {
            ParseResultVo vo = new ParseResultVo();
            if (data.has(StateParser.KEY_STATE)) {
                vo.result = Constant.ParseData_TimeOut;
                vo.reMsg = data.optString(KEY_DES);
            }
            return null;
        }
    }

}
