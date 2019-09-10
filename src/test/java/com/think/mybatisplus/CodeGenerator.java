/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * Refer to https://mp.baomidou.com/guide/generator.html
 * Generator code by mybatis-plus generator and use velocity templates
 * It will generate entity, mapper, mapper system, service and controller files.
 * @author map6
 */
public class CodeGenerator {

    /**
     * @args command line arguments
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("C://temp2");
        globalConfig.setAuthor("Paul Ma");
        globalConfig.setOpen(true);
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(true); // 开启 activeRecord 模式
        globalConfig.setEnableCache(false); // XML 二级缓存
        globalConfig.setBaseResultMap(true); // XML ResultMap
        globalConfig.setBaseColumnList(true); // XML columList
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // globalConfig.setMapperName("%sDao");
        // globalConfig.setXmlName("%sDao");
        // globalConfig.setServiceName("MP%sService");
        // globalConfig.setServiceImplName("%sServiceDiy");
        // globalConfig.setControllerName("%sAction");
        // 设置idType
        globalConfig.setIdType(IdType.UUID);
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("phpwind.net");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/sparrowadmin?characterEncoding=utf8");
        autoGenerator.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        //strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
        //strategy.setTablePrefix(new String[] { "wsale_"});// 此处可以修改为您的表前缀
        //strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        //strategy.setInclude(new String[] { "user" }); // 需要生成的表
        //strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        //strategy.setFieldNaming(NamingStrategy.nochange);
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        //strategyConfig.setSuperControllerClass("com.think.warehouse.common.controller.SuperController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);


        autoGenerator.setStrategy(strategyConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("system");
        pc.setParent("com.think.sparrowadmin");// 自定义包路径
        pc.setController("controller");// 这里是控制器包名，默认 web
        //pc.setEntity("entity");
        autoGenerator.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        //		InjectionConfig cfg = new InjectionConfig() {
        //			@Override
        //			public void initMap() {
        //				Map<String, Object> map = new HashMap<String, Object>();
        //				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
        //				this.setMap(map);
        //			}
        //		};

        // 自定义 xxList.jsp 生成
        //		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //		focList.add(new FileOutConfig("/templates/list.jsp.vm") {
        //			@Override
        //			public String outputFile(TableInfo tableInfo) {
        //				// 自定义输入文件名称
        //				return "D://my_" + tableInfo.getEntityName() + ".jsp";
        //			}
        //		});

        //cfg.setFileOutConfigList(focList);
        //		mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/generator/entity.java");  // override toString method
        autoGenerator.setTemplate(templateConfig);

        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        autoGenerator.execute();
    }
}
