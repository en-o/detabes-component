package com.detabes.list.core;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 复制相关
 * @author tn
 * @version 1
 * @ClassName ListTo
 * @description 复制相关
 * @date 2020/8/12 20:13
 */
public class ListTo {

    /**
     * 复制bean
     * @param tClass 返回类型
     * @param list 数据
     * @param <T> 泛型
     * @param <K> 泛型
     * @return tClass
     */
    public static <T, K> List<T> to(Class<T> tClass, List<K> list){
        return list.stream().map((entity) -> beanTo(tClass, entity)).collect(Collectors.toList());
    }

    /**
     * 复制bean
     * @param tClass 需求bean
     * @param t 被复制的bean
     * @return tClass
     */
    private static <T> T beanTo(Class<T> tClass,Object t){
        try {
            T tag = tClass.newInstance();
            beanCopy(t, tag);
            return tag;
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private static <T> void beanCopy(T source, T target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        Set<String> emptyNames = getNullPropertyNameSet(source);
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    private static Set<String> getNullPropertyNameSet(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames;
    }


}
