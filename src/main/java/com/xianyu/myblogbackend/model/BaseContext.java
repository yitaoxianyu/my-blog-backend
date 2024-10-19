package com.xianyu.myblogbackend.model;


//封装一个threadLocal
public class BaseContext {

    //threadLocal只能存储一种数据结构，但是一种数据结构中可以存储多个数据例如：数组等等
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

    public static void removeCurrentId(){
        threadLocal.remove();
    }
}
