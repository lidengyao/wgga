package com.bcxd.wgga.widget;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Normal implements Serializable {
    public String Key;
    public String Value;
    public EditText normalEditText;
    public String Type;
    public boolean IsNull;
    public ArrayList<CheckBox> checkBoxArrayList;

    public ArrayList<CheckBox> getCheckBoxArrayList() {
        return checkBoxArrayList;
    }

    public void setCheckBoxArrayList(ArrayList<CheckBox> checkBoxArrayList) {
        this.checkBoxArrayList = checkBoxArrayList;
    }

    public boolean isNull() {
        return IsNull;
    }

    public void setNull(boolean aNull) {
        IsNull = aNull;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public EditText getNormalEditText() {
        return normalEditText;
    }

    public void setNormalEditText(EditText normalEditText) {
        this.normalEditText = normalEditText;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
