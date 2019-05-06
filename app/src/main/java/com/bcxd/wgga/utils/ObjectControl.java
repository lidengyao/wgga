package com.bcxd.wgga.utils;

import android.content.Context;
import android.widget.Toast;

import com.bcxd.wgga.widget.Normal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectControl {
    /* 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

    public static HashMap<String, String> ObjectToHashMap(Object object) {
        HashMap<String, String> dic = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (fields[i].isSynthetic())
                continue;
            if (fieldName.equals("serialVersionUID"))
                continue;
            Object oValue = ObjectControl.getFieldValueByName(fieldName, object);
            if (oValue != null) {
                String value = ObjectControl.getFieldValueByName(fieldName, object).toString();
                dic.put(fieldName, value);
            }
        }
        return dic;
    }

    public static boolean IsNullDialog(Context context, ArrayList<Normal> normalArrayList) {
        boolean isnull = true;
        for (int i = 0; i < normalArrayList.size(); i++) {
            if (normalArrayList.get(i).isNull() == false && normalArrayList.get(i).getNormalEditText().getText().toString().equals("")) {
                Toast.makeText(context, "请填写标记为红色*的必填项", Toast.LENGTH_SHORT).show();
                isnull = false;
                break;
            }
        }
        return isnull;
    }


    public static void SetData(ArrayList<Normal> tempNormalList, HashMap<String, String> dic) {

        for (int i = 0; i < tempNormalList.size(); i++) {
            Normal tempNormal = tempNormalList.get(i);

            for (String key : dic.keySet()) {
                if (key.equals(tempNormal.getKey())) {
                    String value = dic.get(key);
                    tempNormal.getNormalEditText().setText(value);
                    break;
                }
            }
        }
    }
}
