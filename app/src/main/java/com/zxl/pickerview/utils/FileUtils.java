package com.zxl.pickerview.utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.InputStream;

/**
 * Description
 * Created by zxl on 2017/1/5 下午2:53.
 * Email:444288256@qq.com
 */
public class FileUtils {

    public static JSONObject getJsonData(Context context,String name){
        JSONObject jsonObj = null;
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "UTF-8"));
            }
            is.close();
            jsonObj = new JSONObject(sb.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
