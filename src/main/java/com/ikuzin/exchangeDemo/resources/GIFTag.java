package com.ikuzin.exchangeDemo.resources;

public enum GIFTag {
    UP("rich"),
    DOWN("broke");

    private final String tag;
    GIFTag(String tag) { this.tag = tag; }
    public String getTag(){ return tag; }
}
