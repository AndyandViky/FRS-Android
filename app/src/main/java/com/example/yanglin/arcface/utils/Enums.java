package com.example.yanglin.arcface.utils;

/**
 * Created by yanglin on 18-4-3.
 */

public class Enums {
    public enum Camera {
        UPLOADFACE, UPLOADBUG, UPLOADVISITOR, UPLOADAVATAR
    }


    public enum Article {
        RECOMMOND("recommond", -1), ALL("普通文章", 0), OTHER("other", 1);
        private String name;
        private int index;

        Article(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static String getName(int index){
            for (Article a : Article.values()) {
                if (a.getIndex() == index) {
                    return a.name;
                }
            }
            return null;
        }
        public int getIndex() {
            return index;
        }

    }
}
