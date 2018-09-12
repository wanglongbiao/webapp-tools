
使用方法：
1.配置 jdbc.properties，generatorConfig.xml，添加表
2.在 idea 的命令行执行
    mvn clean & mvn mybatis-generator:generate

添加了两个插件，这样即使在局域网中也能使用
1.编译打包，mvn clean install package
2.解压缩 jar 包
3.执行 java -cp .;lib/* Client