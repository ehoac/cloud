package com.eh.cloud.auth.api.util;

import java.util.UUID;

/**
 * @author caopeihe
 * @Type UuidUtil.java
 * @Desc
 * @date 2020/2/25 10:49
 */
public class UuidUtil {
    private UuidUtil() {}

    public static String nextId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
