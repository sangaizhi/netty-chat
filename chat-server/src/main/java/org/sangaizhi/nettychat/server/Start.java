package org.sangaizhi.nettychat.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Server server = context.getBean(Server.class);
        server.start();
    }
}
