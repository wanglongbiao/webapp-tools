# 快速生成基于 ssh 的 Java web 项目
主要作用是补充了默认生成没有包含的文件夹，默认的 web.xml 使用的版本，以及默认的 spring ，log4j 等框架的支持

## 主要功能
- 基于 Spring，SpringMVC，Spring-data-jpa，Hibernate
- 支持 BaseRepository，BaseService，BaseController
- 支持 log4j
- 默认使用 MySql 的驱动，c3p0 连接池
- 支持基于 cxf 的 webservice，需要额外配置
- 自带一个默认的字典模块

## 前端功能支持
- 支持 jQuery，Bootstrap
- 生成 taglib.jsp
- 暂时不考虑使用 sitemesh

## 使用方法
在文件所在目录下双击 gen-ssh.py 即可。暂时不能使用快捷方式。
双击 gen-ssh.py 即可。

运行前可先配置项目目标目录。

## TODO List
支持双击快捷方式执行程序

