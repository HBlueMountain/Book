package com.book.utils;

import com.book.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by YongXin Xue on 2020/04/16 15:09
 */
public class WebUtils {
    /**
     * 将字符串转换成 int 类型
     * @param intStr
     * @param defaultValue 如果转换失败,就返回 defaultValue,使用这个默认值
     * @return
     */
    public static int parseInt(String intStr, int defaultValue){
        try {
            return Integer.parseInt(intStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 编写一个方法将所有参数都一次性注入到javaBean属性中
     * 这里写成 Map 可以更好的利用这个工具类,在 WEB Service dao 层都可使用
     * @param bean
     * @param value
     */
    public static <T> T copyParamToBean(T bean, Map value){
        /**
         * BeanUtils 工具类可以省去调用大量的setXXX方法
         * populate() 是把指定数据源中的值一次性注入到 Bean 的属性中
         *  第一个参数是要赋值的 bean 对象
         *  第二个参数是数据源
         */
        try {
            System.out.println("注入属性值之前:" + bean);
            BeanUtils.populate(bean, value );
            System.out.println("注入属性值之后:" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
