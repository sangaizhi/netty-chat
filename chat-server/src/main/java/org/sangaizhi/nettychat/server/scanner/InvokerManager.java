package org.sangaizhi.nettychat.server.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 * 命令执行器管理
 *
 * @author sangaizhi
 * @date 2017/5/24
 */
public class InvokerManager {

    /**
     * 命令执行器
     */
    private static Map<Short, Map<Short, Invoker>> invokes = new HashMap<>();

    /**
     * 添加命令执行器
     * @param module 执行的模块
     * @param command 执行的命令
     * @param invoker 执行器
     */
    public static void addInvoke(Short module, Short command, Invoker invoker){
        Map<Short, Invoker> invokerMap = invokes.get(module);
        if(invokerMap == null){
            invokerMap = new HashMap<>();
        }
        invokerMap.put(command, invoker);
    }

    /**
     * 获取命令执行器
     * @param module
     * @param command
     * @return
     */
    public static Invoker getInvoker(Short module, Short command){
        Map<Short, Invoker> invokerMap = invokes.get(module);
        if(null == invokerMap){
            return invokerMap.get(command);
        }
        return null;
    }

}
