package cn.org.dianjiu.job.common.util;

import com.alibaba.fastjson.JSONObject;

public class JsonValidUtil {

    /**
     * 判断字符串是否为json格式
     *
     * @param jsonStr
     * @return
     */
    public static boolean isJson(String jsonStr) {
        try {
            JSONObject.parseObject(jsonStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
