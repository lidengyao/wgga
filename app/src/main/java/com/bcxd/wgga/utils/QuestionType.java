package com.bcxd.wgga.utils;

public class QuestionType {
    public static String getTypeValue(int status) {
        String value = "";
        switch (status) {
            case 0:
                value = "待处理";
                break;
            case 1:
                value = "已处理";
                break;
            case 2:
                value = "已回复";
                break;
            case 3:
                value = "已提交";
                break;
            case 4:
                value = "已完结";
                break;
            case 5:
                value = "已打回";
                break;
        }

        return value;
    }
}
