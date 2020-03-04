package com.eh.cloud.tools.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: caopeihe
 * date: 2020/3/4 16:50
 * desc: IllegalStrUtil
 */
public class IllegalStrUtil {
    private static final Logger LOG = LoggerFactory.getLogger(IllegalStrUtil.class);

    private static final String REGX = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";

    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param sql
     * @return
     *  true 表示参数不存在SQL注入风险
     *  false 表示参数存在SQL注入风险
     */
    public static Boolean sqlFilter(String sql) {
        if (sql == null || sql.trim().length() == 0) {
            return false;
        }
        sql = sql.toUpperCase();

        if (sql.contains("DELETE") || sql.contains("ASCII") || sql.contains("UPDATE")
                || sql.contains("SELECT") || sql.contains("'") || sql.contains("SUBSTR(")  
                || sql.contains("COUNT(")  || sql.contains(" OR ") || sql.contains(" AND ")  
                || sql.contains("DROP")  || sql.contains("EXECUTE")  || sql.contains("EXEC") 
                || sql.contains("TRUNCATE")  || sql.contains("INTO")  || sql.contains("DECLARE") 
                || sql.contains("MASTER")) {
            LOG.error("该参数怎么SQL注入风险：sql=" + sql);
            return false;
        }
        LOG.info("通过sql检测");
        return true;
    }

    /**
     * 对非法字符进行检测
     *
     * @param str
     * @return
     *  true 表示参数不包含非法字符
     *  false 表示参数包含非法字符
     */
    public static Boolean isIllegalStr(String str) {

        if (str == null || str.trim().length() == 0) {
            return false;
        }
        str = str.trim();
        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(str);
        LOG.info("通过字符串检测");
        return matcher.find();
    }
}
