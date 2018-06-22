
使用方法：
1.配置 jdbc.properties，generatorConfig.xml，添加表
2.在 idea 的命令行执行
    mvn clean
    mvn clean & mvn mybatis-generator:generate -D mybatis.generator.overwrite=true