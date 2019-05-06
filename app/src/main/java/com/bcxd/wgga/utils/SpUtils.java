package com.bcxd.wgga.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    /*

     */
    public static boolean saveSettingNote(Context context, String key, String value) {

        try {
            SharedPreferences.Editor note = context.getSharedPreferences("zhgd", Context.MODE_PRIVATE).edit();
            note.putString(key, value);
            note.commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public static String getSettingNote(Context context, String key) {
        SharedPreferences read = context.getSharedPreferences("zhgd", Context.MODE_PRIVATE);
        return read.getString(key, null);
    }

}
