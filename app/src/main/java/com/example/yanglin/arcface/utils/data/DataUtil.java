package com.example.yanglin.arcface.utils.data;

import android.content.Context;
import android.widget.ImageView;

import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.models.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanglin on 17-8-15.
 */

public class DataUtil {
    //添加数据

    /***
     *
     * @param context
     * @param icons 图片Id
     * @return
     */
    public static List<ImageView> getHeaderAdInfo(Context context,int icons[]) {
        List<ImageView> datas = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i]);
            datas.add(icon);
        }

        return datas;
    }

    public static List<Record> getRecord(int[] types, int[] counts, String[] times) {
        List<Record> datas = new ArrayList<>();
        for (int i=0; i<types.length; i++) {
            Record record = new Record(types[i], counts[i], times[i]);
            datas.add(record);
        }
        return datas;
    }

//    public static List<Visitor> getVisitor(int[] types, String[] reasons, String[] times, String[] names, String[] phones) {
//        List<Visitor> datas = new ArrayList<>();
//        for(int i=0; i<types.length; i++) {
//            Visitor visitor = new Visitor(names[i], times[i], reasons[i], phones[i], types[i]);
//            datas.add(visitor);
//        }
//        return datas;
//    }
    public static List<Visitor> getVisitor() {
        List<Visitor> datas = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Visitor visitor = new Visitor("杨林", "2018-12-10 12:10:24", "业务洽谈", "17805850721", 0);
            datas.add(visitor);
        }
        return datas;
    }
}

