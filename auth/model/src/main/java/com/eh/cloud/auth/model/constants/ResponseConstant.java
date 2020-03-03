package com.eh.cloud.auth.model.constants;

/**
 * @author caopeihe
 * @Type ResponseConstant.java
 * @Desc
 * @date 2020/2/28 13:46
 */
public class ResponseConstant {
//    public final static Integer ERROR_CODE = 5000;
//    public final static String ERROR_MESSAGE = "Inner server error!";
//
//    public final static Integer USER_NO_ACCESS_CODE = 40003;
//    public final static String USER_NO_ACCESS_MESSAGE = "Inner server error!";
//
//    public final static Integer SUCCESS_CODE = 2000;
//    public final static String SUCCESS_MESSAGE = "Inner server error!";
//
//    public final static Integer TOKEN_EXPIRED_CODE = 4000;
//    public final static String TOKEN_EXPIRED_MESSAGE = "Inner server error!";
//
//    public final static Integer USER_LOGIN_EXPIRED_CODE = 2000;
//    public final static String USER_LOGIN_EXPIRED_MESSAGE = "Inner server error!";

    // 成功码
    public static final int SUCCESS_CODE = 200;
    // 成功信息
    public static final String SUCCESS_MESSAGE = "操作成功";

    // 通用 错误码
    public static final int ERROR_CODE = 500;

    // 通用 错误信息
    public static final String ERROR_MESSAGE = "系统异常";
    /**
     * 50X Token异常
     */
    // TOKEN无效 错误码
    public static final int TOKEN_INVALID_CODE = 501;

    // TOKEN无效 错误信息
    public static final String TOKEN_INVALID_MESSAGE = "TOKEN 无效";

    // TOKEN过期 错误码
    public static final int TOKEN_EXPIRED_CODE = 502;

    // TOKEN过期 错误信息
    public static final String TOKEN_EXPIRED_MESSAGE = "TOKEN 过期";
    /**
     * 51X 用户异常编码
     */
    // 用户登录失败 错误码
    public static final int USER_LOGIN_ERROR_CODE = 510;

    // 用户登录失败 错误信息
    public static final String USER_LOGIN_ERROR_MESSAGE = "用户名或者密码错误 请重新登录";

    // TOKEN无效 错误码
    public static final int USER_LOGIN_EXPIRED_CODE = 511;

    // TOKEN无效 错误信息
    public static final String USER_LOGIN_EXPIRED_MESSAGE = "用户未登录，请重新登录";

    // 用户无权限 错误码.
    public static final int USER_NO_ACCESS_CODE = 512;

    // 用户无权限 错误信息
    public static final String USER_NO_ACCESS_MESSAGE = "无权限访问";

    // 错误码：参数非法
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

    // 错误信息：参数非法
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数错误";
}
