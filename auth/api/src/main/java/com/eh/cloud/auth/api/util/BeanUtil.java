package com.eh.cloud.auth.api.util;

import com.eh.cloud.auth.model.entity.common.ErrorResponse;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caopeihe
 * @Type BeanUtil.java
 * @Desc
 * @date 2020/2/28 9:07
 */
public class BeanUtil {

    public static Map<String, Object> beanToMap (ErrorResponse errorResponse) {
        try {
            Map<String,Object> map = new HashMap<>(6);
            //获取JavaBean的描述器
            BeanInfo b = Introspector.getBeanInfo(errorResponse.getClass(),Object.class);
            //获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();
            //对属性迭代
            for (PropertyDescriptor pd : pds) {
                //属性名称
                String propertyName = pd.getName();
                //属性值,用getter方法获取
                Method m = pd.getReadMethod();
                //用对象执行getter方法获得属性值
                Object properValue = m.invoke(errorResponse);
                //把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }
            return map;
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException inte){
            inte.printStackTrace();
        }
        return null;
    }

    public static <T> T mapToBean(Map<String,Object> map,Class<T> clz) throws Exception{
        //创建一个需要转换为的类型的对象
        T obj = clz.newInstance();
        //从Map中获取和属性名称一样的值，把值设置给对象(setter方法)

        //得到属性的描述器
        BeanInfo b = Introspector.getBeanInfo(clz,Object.class);
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //得到属性的setter方法
            Method setter = pd.getWriteMethod();
            //得到key名字和属性名字相同的value设置给属性
            setter.invoke(obj, map.get(pd.getName()));
        }
        return obj;
    }
}
