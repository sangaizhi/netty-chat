package org.sangaizhi.nettychat.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解，注可处理命令的方法
 * @author sangaizhi
 * @date 2017/5/24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketCommand {
    /**
     * 请求的命令编号
     * @return
     */
    short command();
}
