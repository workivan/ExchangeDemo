package com.ikuzin.exchangeDemo.utils;

public class Utils {
    public static final String EMPTY_URL = "/";

    public static void checkValutaPathVariable(String valuta){
        if(valuta.length() != 3)
            throw new IllegalArgumentException("invalid argument valuta");
        if(!valuta.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("argument valuta consists not only of letters");
    }
    private Utils() {}
}
