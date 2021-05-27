package com.lys.publicpro.framework;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 李无敌
 * @date 2021年03月19日 9:50
 */
public class MybatisPlusGenerator {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/publicpro?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
    public static final String PARENT_PATH = "com.lys.publicpro.common.db";
    public static final String BASE_CONTROLLER_PATH = "com.lys.publicpro.common.BaseController";
    public static final String CEDE_TB = "tb_account_info";

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();

        //得到当前项目的路径
        String oPath = System.getProperty("user.dir");

        //生成文件输出根目录
        gc.setOutputDir(oPath + "/src/main/java");

        //生成完成后不弹出文件框
        gc.setOpen(false);

        //文件覆盖
        gc.setFileOverride(true);

        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);

        // XML 二级缓存
        gc.setEnableCache(false);

        // XML ResultMap
        gc.setBaseResultMap(true);

        // XML columList
        gc.setBaseColumnList(false);

        // 作者
        gc.setAuthor("liwudi");
        gc.setSwagger2(true);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        //设置数据库类型
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName(DRIVER);

        //用户名
        dsc.setUsername(USER_NAME);

        //密码
        dsc.setPassword(PASSWORD);

        //指定数据库
        dsc.setUrl(DB_URL);
        autoGenerator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表
        strategy.setInclude(new String[]{CEDE_TB});
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        strategy.setSuperControllerClass(BASE_CONTROLLER_PATH);
        strategy.setEntityLombokModel(true);
        //类名生成策略：驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //字段名生成方式：驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //去除表前缀
        strategy.setTablePrefix("tb_");
        //去除字段前缀
        strategy.setFieldPrefix("");
        autoGenerator.setStrategy(strategy);


        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包路径
        pc.setParent(PARENT_PATH);
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        autoGenerator.setPackageInfo(pc);
        // 执行生成
        autoGenerator.execute();
    }
}
