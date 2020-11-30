package com.rongyungov.kxpt.api;

/**
 * @author ：David.Yan
 * @date ：Created in 2020-07-24 10:03
 * @description：
 * @modified By：
 * @version:
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author ：David.Yan
 * @date ：Created in 2019/3/4 10:01
 * @description：
 * @modified By：
 * @version:
 */


public class MybatisGen2 {


    private static String prefix = "";                     //table前缀
    private static String parentPackagePath = "";


    public static void gen(String path, String authorname, String basepackagename,
                           String[] tables, String username, String password, String url) {
        parentPackagePath = basepackagename + ".";
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(path + "/src/main/java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setAuthor(authorname)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)// 数据库类型
                        .setTypeConvert(new MySqlTypeConvert()
                        )
                        .setDriverName("com.mysql.cj.jdbc.Driver")
                        .setUsername(username)
                        .setPassword(password)
                        .setUrl(url)
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .entityTableFieldAnnotationEnable(true)
                        .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(tables) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表

                        .setSuperEntityClass("com.rongyungov.framework.base.BaseEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        .setTableFillList(tableFillList)
                        // 自定义 mapper 父类
                        .setSuperMapperClass("com.rongyungov.framework.base.BaseDao")
                        // 自定义 service 父类
                        .setSuperServiceClass("com.rongyungov.framework.base.BaseService")
                        // 自定义 service 实现类父类
                        .setSuperServiceImplClass("com.rongyungov.framework.base.BaseServiceImpl")
                        // 自定义 controller 父类
                        .setSuperControllerClass("com.rongyungov.framework.base.BaseController")
                        .setLogicDeleteFieldName("isdeleted")
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        //.setModuleName("User")
                        .setParent("")// 自定义包路径
                        .setController(parentPackagePath + ".api.controller")// 这里是控制器包名，默认 web
                        .setEntity(parentPackagePath + "entity")
                        .setMapper(parentPackagePath + "dao")
                        .setService(parentPackagePath + "service")
                        .setServiceImpl(parentPackagePath + "service.impl")

        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/tTestlates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return path + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/tTestlate 使用 copy
                        // 至您项目 src/main/resources/tTestlate 目录下，模板名称也可自定义如下配置：
                        .setController("/tTestlate/controller.java.vm")
                        .setEntity("/tTestlate/entity.java.vm")
                        .setMapper("/tTestlate/mapper.java.vm")
                        .setXml("/tTestlate/mapper.xml.vm")
                        .setService("/tTestlate/service.java.vm")
                        .setServiceImpl("/tTestlate/serviceImpl.java.vm")
        );

        // 执行生成
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}