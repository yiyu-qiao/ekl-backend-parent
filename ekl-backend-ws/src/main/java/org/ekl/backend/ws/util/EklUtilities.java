package org.ekl.backend.ws.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class EklUtilities {

    public static void printEnvironment(ApplicationContext ctx){
        System.out.println("Name environment implementation : " + ctx.getEnvironment().getClass().getName());
        System.out.println("evn : " + ctx.getEnvironment().getProperty("env"));
        System.out.println("user.home:" + ctx.getEnvironment().getProperty("user.home"));
        System.out.println("java.home:" + ctx.getEnvironment().getProperty("java.home"));
        System.out.println("JAVA_HOME:" + ctx.getEnvironment().getProperty("JAVA_HOME"));
    }
}
