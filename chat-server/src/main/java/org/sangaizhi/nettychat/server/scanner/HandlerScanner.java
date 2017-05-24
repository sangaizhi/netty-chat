package org.sangaizhi.nettychat.server.scanner;

import org.sangaizhi.nettychat.core.annotation.SocketCommand;
import org.sangaizhi.nettychat.core.annotation.SocketModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * 可执行的模块及方法扫描，
 * 根据扫描的模块及方法生成执行器
 * @author sangaizhi
 * @date 2017/5/24
 */
public class HandlerScanner implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(HandlerScanner.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clazz = bean.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        if(null != interfaces && interfaces.length > 0){
            // 扫描类的所有接口父类
            for(Class<?> interFace : interfaces){
                // 判断该类是否被 SocketModule 注解修饰，如果被修饰，表示该类是可处理命令的类
                SocketModule socketModule = interFace.getAnnotation(SocketModule.class);
                if(null == socketModule){
                    continue;
                }
                // 扫描该类下的所有方法
                Method[] methods = interFace.getMethods();
                if(methods != null && methods.length > 0){
                    for(Method method : methods){
                        // 判断该方法是否被 SocketCommand 注解修饰，如果被修饰，表示该方法是可处理命令的方法
                        SocketCommand socketCommand = method.getAnnotation(SocketCommand.class);
                        if(null == socketCommand){
                            continue;
                        }
                        final short module = socketModule.module();
                        final short command = socketCommand.command();
                        if(InvokerManager.getInvoker(module, command) == null){
                            InvokerManager.addInvoke(module, command, Invoker.valueOf(method, bean));
                        }else{
                            logger.error("发现重复命令,module:{},command:{}", module, command);
                        }
                    }
                }
            }
        }
        return bean;
    }
}
