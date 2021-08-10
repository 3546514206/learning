/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.tracer.boot.springcloud.configuration;

import com.alipay.sofa.tracer.boot.springcloud.configuration.hystrix.SofaTracerHystrixConcurrencyStrategy;
import com.alipay.sofa.tracer.boot.springcloud.configuration.hystrix.SofaTracerHystrixFeignBuilder;
import feign.Client;
import feign.Feign;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/13 6:04 PM
 * @since:
 **/
@Configuration
@ConditionalOnClass(Client.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@ConditionalOnProperty(name = "com.alipay.sofa.tracer.feign.enabled", havingValue = "true", matchIfMissing = true)
public class SofaTracerFeignClientAutoConfiguration {
    @Bean
    public SofaTracerFeignContextBeanPostProcessor feignContextBeanPostProcessor(BeanFactory beanFactory) {
        return new SofaTracerFeignContextBeanPostProcessor(beanFactory);
    }

    @Bean
    public FeignTracerRequestInterceptor feignTracerRequestInterceptor() {
        return new FeignTracerRequestInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    SofaTracerHystrixConcurrencyStrategy sleuthHystrixConcurrencyStrategy() {
        return new SofaTracerHystrixConcurrencyStrategy();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnClass(name = {"com.netflix.hystrix.HystrixCommand", "feign.hystrix.HystrixFeign"})
    @ConditionalOnProperty(name = "feign.hystrix.enabled", havingValue = "true")
    Feign.Builder feignHystrixBuilder() {
        return SofaTracerHystrixFeignBuilder.builder();
    }
}
