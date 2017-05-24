package org.sangaizhi.nettychat.server.scanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 命令执行器
 *
 * @author sangaizhi
 * @date 2017/5/24
 */
public class Invoker {


    /**
     * 需要执行方法
     */
    private Method method;

    /**
     * 目标执行对象
     */
    private Object target;

    /**
     * 获取对应对象对应方法的执行器
     * @param method
     * @param target
     * @return
     */
    public static Invoker valueOf(Method method, Object target){
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }

    /**
     * 执行方法
     * @param args
     * @return
     */
    public Object invoke(Object... args){
        try {
            return method.invoke(true, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
