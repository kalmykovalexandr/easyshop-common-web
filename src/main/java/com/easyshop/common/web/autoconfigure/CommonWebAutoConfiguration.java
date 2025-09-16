package com.easyshop.common.web.autoconfigure;

import com.easyshop.common.web.GlobalExceptionHandler;
import com.easyshop.common.web.ReactiveGlobalExceptionHandler;
import com.easyshop.common.web.TraceIdFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
public class CommonWebAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Import(GlobalExceptionHandler.class)
    static class ServletConfig {
        @Bean
        @ConditionalOnMissingBean
        public TraceIdFilter traceIdFilter() {
            return new TraceIdFilter();
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @Import(ReactiveGlobalExceptionHandler.class)
    static class ReactiveConfig {
        // No explicit beans; advice class is imported
    }
}

