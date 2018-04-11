package com.example.yanglin.arcface.utils.data;

import android.content.Context;
import android.widget.ImageView;

import com.example.yanglin.arcface.models.Community;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.models.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanglin on 17-8-15.
 */

public class DataUtil {
    //添加数据

    public static List<Record> getRecord(int[] types, int[] counts, String[] times) {
        List<Record> datas = new ArrayList<>();
        for (int i=0; i<types.length; i++) {
            Record record = new Record(types[i], counts[i], times[i]);
            datas.add(record);
        }
        return datas;
    }

    public static List<Visitor> getVisitor(int[] types, String[] reasons, String[] times, String[] names, String[] phones) {
        List<Visitor> datas = new ArrayList<>();
        for(int i=0; i<types.length; i++) {
            Visitor visitor = new Visitor(names[i], times[i], reasons[i], phones[i], types[i]);
            datas.add(visitor);
        }
        return datas;
    }

    public static List<Community> getCommunity(int[] images, String[] titles, String[] contents, String[] times, String tags[], String categorys[]) {
        List<Community> datas = new ArrayList<>();
        for(int i=0; i<titles.length; i++) {
            Community community = new Community(images[i], titles[i], contents[i], times[i], tags[i], categorys[i]);
            datas.add(community);
        }
        return datas;
    }
}

