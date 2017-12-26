# 使用 Maven，快速生成基于 ssh 的 Java web 项目
主要补充了默认生成没有包含的源文件夹，修改默认的 web.xml 使用的版本，以及默认的 spring ，log4j 等框架的支持

## 主要功能
- 补充默认Maven生成的项目中不包含的源文件夹
- 增加常用框架支持，包括Spring，SpringMVC，Spring-data-jpa，Hibernate
- 增加常用服务支持，包括 cxf，mq，log4j
- 修改 web.xml，使用3.0版本，并添加spring监听器配置，springMVC配置，cxf 配置
- 支持 BaseRepository，BaseService，BaseController
- 使用 MySql 的驱动，c3p0 连接池
- 自带一个默认的字典模块

## 前端功能支持
- 支持 jQuery，Bootstrap
- 生成 taglib.jsp
- 暂时不考虑使用 sitemesh

## 使用方法
### 前提
需要有一个叫做 gen_ssh 的数据库
在文件所在目录下双击 gen-ssh.py 即可。暂时不能使用快捷方式。
双击 gen-ssh.py 即可。

运行前可先配置项目目标目录。

## TODO List
支持双击快捷方式执行程序

