package com.bcxd.wgga.utils;

import android.os.Environment;
import android.widget.EditText;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class Tools {

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static void EditTextFouseToEnd(EditText editText) {
        editText.setSelection(editText.getText().length());
    }
}