package com.eh.cloud.tools.web.config;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.eh.cloud.tools.constants.ResponseConstant;
import com.eh.cloud.tools.util.IllegalStrUtil;
import com.eh.cloud.tools.util.Result;
import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * author: caopeihe
 * date: 2020/3/4 16:39
 * desc: ErrorHandleAndPerformanceLog
 */
@Component
@Aspect
public class ErrorHandleAndPerformanceLog {
    public static final Logger LOG = LoggerFactory.getLogger(ErrorHandleAndPerformanceLog.class);

    @Value("${dc.log.bad.value:3000}")
    private int performanceBadValue;

    @Pointcut("execution(* com.eh.cloud.tools.api..*.*(..))")
    public void webCut(){}

    @Around("webCut() || serviceCut()")
    public Result handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Stopwatch stopwatch = Stopwatch.createStarted();
        Result result;
        try {
            LOG.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
            //处理入参特殊字符和sql注入攻击
            checkRequestParam(pjp);
            //执行访问接口操作
            result = (Result) pjp.proceed(pjp.getArgs());
            try{
                LOG.info("执行Controller结束: " + pjp.getSignature() + "， 返回值：" + JSONObject.toJSONString(result));
                //此处将日志打印放入try-catch是因为项目中有些对象实体bean过于复杂，导致序列化为json的时候报错，但是此处报错并不影响主要功能使用，只是返回结果日志没有打印，所以catch中也不做抛出异常处理
            }catch (Exception ex){
                LOG.error(pjp.getSignature()+" 接口记录返回结果失败！，原因为：{}",ex.getMessage());
            }
            Long consumeTime = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
            LOG.info("耗时：" + consumeTime + "(毫秒).");
            //当接口请求时间大于3秒时，标记为异常调用时间，并记录入库
            if(consumeTime > performanceBadValue){
                // TODO 记录错误日志 RabbitMQ 或其它
//                DcPerformanceEntity dcPerformanceEntity = new DcPerformanceEntity();
//                dcPerformanceEntity.setInterfaceName(pjp.getSignature().toString());
//                dcPerformanceEntity.setRequestParam(Lists.newArrayList(pjp.getArgs()).toString());
//                dcPerformanceEntity.setConsumeTime(consumeTime + "毫秒");
//                RabbitMQMessageTarget mqTarget = RabbitMQMessageTarget.createFanoutTarget(ProjectConstants.DC_KEY_EXCHANGE_PERFORMANCE, new String[] { ProjectConstants.DC_KEY_QUEUE_PERFORMANCE});
//                rabbitMQService.send(mqTarget, JSON.toJSONString(dcPerformanceEntity));
            }
        } catch (Exception throwable) {
            result = handlerException(pjp, throwable);
        }

        return result;
    }

    private Result handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Result result;
        if(e.getClass().isAssignableFrom(BizException.class) ){
            //ProjectException为自定义异常类，项目中Controller层会把所有的异常都catch掉，并手工封装成ProjectException抛出来，这样做的目的是ProjectException会记录抛出异常接口的路径，名称以及请求参数等等，有助于错误排查
            BizException bizException = (BizException)e;
            LOG.error("捕获到ProjectException异常:", bizException.toJsonString());
//            RabbitMQMessageTarget mqTarget = RabbitMQMessageTarget.createFanoutTarget(ProjectConstants.DC_KEY_EXCHANGE_INTERFACE_ERROR, new String[] { ProjectConstants.DC_KEY_QUEUE_INTERFACE_ERROR});
//            rabbitMQService.send(mqTarget, JSON.toJSONString(dataCenterException.getDcErrorEntity()));
            result = new Result(ResponseConstant.ERROR_CODE, bizException.getMsg());
        } else if (e instanceof RuntimeException) {
            LOG.error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            result = new Result(ResponseConstant.ERROR_CODE, e.getMessage());
        } else {
            LOG.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            result = new Result(ResponseConstant.ERROR_CODE, e.getMessage());
        }

        return result;
    }

    private void checkRequestParam(ProceedingJoinPoint pjp){
        String str = String.valueOf(pjp.getArgs());
        if (!IllegalStrUtil.sqlFilter(str)) {
            LOG.info("访问接口：" + pjp.getSignature() + "，输入参数存在SQL注入风险！参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
//            DcErrorEntity dcErrorEntity = interfaceErrorService.processDcErrorEntity(pjp.getSignature() + "",Lists.newArrayList(pjp.getArgs()).toString(),"输入参数存在SQL注入风险!");
//            throw new DataCenterException(dcErrorEntity);
        }
        if (!IllegalStrUtil.isIllegalStr(str)) {
            LOG.info("访问接口：" + pjp.getSignature() + ",输入参数含有非法字符!，参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
//            DcErrorEntity dcErrorEntity = interfaceErrorService.processDcErrorEntity(pjp.getSignature() + "",Lists.newArrayList(pjp.getArgs()).toString(),"输入参数含有非法字符!");
//            throw new DataCenterException(dcErrorEntity);
        }
    }
}
