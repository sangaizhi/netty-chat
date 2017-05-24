package org.sangaizhi.nettychat.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块注解，标注可处理命令的模块
 * @author sangaizhi
 * @date 2017/5/24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {

    /**
     * 模块号
     * @return
     */
    short module();

}
