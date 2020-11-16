package com.rongyungov.kxpt.api.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.rongyungov.framework.common.SpringUtil;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.redis.JedisUtil;
import com.rongyungov.framework.service.UserService;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.api.ApiApplication;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @program: socins
 * @description: 多租户设置
 * @author: zhan
 * @create: 2019-11-06 09:28
 */
@Configuration
public class MybatisPlusConfig {
    //去掉租户筛选表
    public static final List<String> IGNORE_TABLE_NAMES = Arrays.asList(new String[]{
            "sys_dictionary", "sys_organization", "user_role", "role_menu", "menu", "organization", "sysmaterial", "forms", "TABLES", "COLUMNS", "user", "sys_config"
    });

    public MybatisPlusConfig() {
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor page = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            public Expression getTenantId() {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();
                String token = request.getHeader("Token");

                //机构切换
                String headerCompanyId = request.getHeader("currentCompanyId");
//                String headerCompanyId = "13";
                if (headerCompanyId != null) {
                    return new LongValue(headerCompanyId);
                }

                //管理后台登录
                if (token != null) {
                    String account = JwtUtil.getClaim(token, "account");
                    Long companyId = (Long) ApiApplication.loginedUserCompany.get(account);
                    if (companyId != null) {
                        return new LongValue(companyId);
                    } else {
                        User user = (User) JedisUtil.getObject("shiro:login:" + account);
                        if (user != null) {
                            if (user.getCompanyId() != null) {
                                return new LongValue(user.getCompanyId());
                            } else {
                                UserService userService = (UserService) SpringUtil.getBean(UserService.class);
                                User user0 = new User();
                                user0.setAccount(account);
                                User curUser = (User) userService.getOne(new QueryWrapper(user0));
                                ApiApplication.loginedUserCompany.put(account, curUser.getCompanyId());
                                JedisUtil.setObject("shiro:login:" + account, curUser);
                                return new LongValue(curUser.getCompanyId());
                            }
                        } else {
                            UserService userService = (UserService) SpringUtil.getBean(UserService.class);
                            User user0 = new User();
                            user0.setAccount(account);
                            User curUser = (User) userService.getOne(new QueryWrapper(user0));
                            ApiApplication.loginedUserCompany.put(account, curUser.getCompanyId());
                            JedisUtil.setObject("shiro:login:" + account, curUser);
                            return new LongValue(curUser.getCompanyId());

                        }
                    }
                } else {
                    return new LongValue(0L);
                }
            }

            @Override
            public String getTenantIdColumn() {
                return "company_id";
//                return "";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                return true;
//                if (MybatisPlusConfig.IGNORE_TABLE_NAMES.contains(tableName)) {
//                    return true;
//                } else if (tableName.contains("act_")) {
//                    return true;
//                } else {
//                    try {
//                        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                        HttpServletRequest request = requestAttributes.getRequest();
//                        String token = request.getHeader("Token");
//
//                        String student = request.getHeader("XSTUDENT");
//                        String coach = request.getHeader("XCOACH");
//                        if (student != null) {
//                            return false;
//                        }
//                        if (coach != null) {
//                            return false;
//                        }
//
//                        if (token != null) {
//                            if (UserUtils.isSuperAdmin(null, request)
//                                    || UserUtils.isYouke(null, request)) {
//                                return true;
//                            }
//                        }
//                        return request.getRequestURI().contains("login");
//                    } catch (Exception e) {
//                        return true;
//                    }
//
//                }
            }
        });
        sqlParserList.add(tenantSqlParser);
        page.setSqlParserList(sqlParserList);
        page.setDialectType("mysql");
        return page;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 乐观锁
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


}
