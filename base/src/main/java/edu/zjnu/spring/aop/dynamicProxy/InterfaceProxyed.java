package edu.zjnu.spring.aop.dynamicProxy;

import edu.zjnu.SpringLoader;
import org.apache.log4j.Logger;

/**
 * @author 杨海波
 * @description 被代理的接口
 * @date 2021-02-17 18:25
 */
public interface InterfaceProxyed {

    Logger log = Logger.getLogger(SpringLoader.class);

    void doSomething();
}