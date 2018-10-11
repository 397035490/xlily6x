package com.xlily6x.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by xiaowenlong on 18/8/2017.
 */
public class ServiceStart {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = extracted();
        context.start();
//        LilyBankInfoService b =(LilyBankInfoService)context.getBean(LilyBankInfoService.class);
//        System.out.println(b.getClass());
        System.err.println("启动成功！");
        System.in.read();

    }

    private static ClassPathXmlApplicationContext extracted() {
        return new ClassPathXmlApplicationContext(new String[] { "Spring-config.xml" });
    }


}
