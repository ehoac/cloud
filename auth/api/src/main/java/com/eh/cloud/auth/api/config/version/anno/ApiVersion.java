package com.eh.cloud.auth.api.config.version.anno;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    int value();
}
