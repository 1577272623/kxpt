package com.rongyungov.kxpt.api;

import com.rongyungov.framework.baseapi.aop.RedisCacheAop2;
import com.rongyungov.framework.baseapi.config.MybatisPlusConfig;
import com.rongyungov.framework.ryform.beans.annotation.BusinessScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rongyungov"}
        , excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MybatisPlusConfig.class
})}
)
@MapperScan(basePackages = {"com.rongyungov.framework.ryform.mapper","com.rongyungov.framework.schedule.mapper",
        "com.rongyungov.kxpt.dao", "com.rongyungov.framework.dao"})
@BusinessScan({"com.rongyungov.framework.ryform.vo", "com.rongyungov.framework.ryform.entity"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableTransactionManagement
@EnableAsync
@Lazy(false)
@EnableScheduling
@ComponentScan(basePackages = {"com.rongyungov.kxpt"})
public class ApiApplication {

    //内存缓存用户公司信息ID:Account,CompanyId
    public static Map<String, Long> loginedUserCompany = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
