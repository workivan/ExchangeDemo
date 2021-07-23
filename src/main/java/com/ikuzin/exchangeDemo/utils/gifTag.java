package com.ikuzin.exchangeDemo.utils;

public enum gifTag {
    UP("rich"),
    DOWN("broke");

    private final String tag;
    gifTag(String tag) { this.tag = tag; }
    public String getTag(){ return tag; }
}
