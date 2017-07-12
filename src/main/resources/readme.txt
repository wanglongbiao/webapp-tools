
hibernate 模块实现的功能：
输入：
	模块名
	
输出：
	Java文件，包括 entity，dao，service，controller，
	Jsp文件，包括 list，addEdit

需要配置项：
	jdbc.properties,数据库连接信息
	config.properties,基础包名，输出文件路径
	
使用方法：
	在 client 包下找到 HibernateClient 类的 main 方法，输出包名后即可。