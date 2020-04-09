package com.eh.cloud.auth.model.constants;

/**
 * @author caopeihe
 * @Type BaseErrors.java
 * @Desc
 * @date 2020/2/27 17:23
 */
public interface BaseErrors {
    int status();

    String getCode();

    String getMsg();

    String timestamp();
}
