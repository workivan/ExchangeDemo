package com.ikuzin.exchangeDemo.utils;

import org.springframework.core.env.Environment;

public abstract class CommonService {
    protected final Environment env;
    protected CommonService(Environment env){
        this.env = env;
    }
}
