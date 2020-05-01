package com.io;

import java.io.Serializable;
/*
第二步，编写一个javaBean
把所有调用相关的信息封装在里面，把该类的对象序列化后通过网络传给服务器
 */
public class RemoteCaller implements Serializable {

    private static final long serialVersionUID = 1L;

    private String className;// 表示要调用的服务类名或接口名
    private String methodName;// 表示要调用的功能方法名
    private Class<?>[] paramTypes;//表示要调用的方法所需的参数类型
    private Object[] params;//表示方法的参数值/如果方法正常执行，则result 为方法返回值，如果方法抛出异常，则result 为该异常
    private Object result;//存储返回结果

    public RemoteCaller(){}
    public RemoteCaller(String className,String methodName,Class<?>[] paramTypes,Object[] params){
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String toString(){
        return "ClassName=" + className + " MethodName=" + methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
