package com.bcxd.wgga.utils;

import android.content.Context;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.DicDataInfo;
import com.bcxd.wgga.widget.Normal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DicUtils {
    public static ArrayList<DicDataInfo> getDicData(int dicKey, Context context) {

        ArrayList<DicDataInfo> dicDataInfos = new ArrayList<>();

        try {
            InputStream is = context.getResources().getAssets().open("dicdata.xml");// getResources().getAssets().open(fileName);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(is, "utf-8");
            int event = xmlParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("Item".equals(xmlParser.getName())) {

                            String Key = xmlParser.getAttributeValue(null, "DicKey");
                            String Value = xmlParser.getAttributeValue(null, "Value");
                            String ID = xmlParser.getAttributeValue(null, "ID");
                            if (Key.equals(dicKey + "")) {
                                DicDataInfo dicDataInfo = new DicDataInfo();
                                dicDataInfo.setId(Integer.parseInt(ID));
                                dicDataInfo.setLby_type_name(Value);
                                dicDataInfos.add(dicDataInfo);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = xmlParser.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dicDataInfos;
    }


}
