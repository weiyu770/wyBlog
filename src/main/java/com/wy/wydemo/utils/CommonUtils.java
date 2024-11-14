package com.wy.wydemo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 公共工具类
 * @class: CommonUtils
 * @author: yu_wei
 * @create: 2024/11/08 17:29
 */
public class CommonUtils {
    
    /**
     * 转换List
     *
     * @param obj   obj
     * @param clazz clazz
     * @return {@link List <T>}
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return result;
    }
    
}