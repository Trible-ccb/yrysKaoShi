该web工程是个性化推荐考试系统的后台Java代码工程，各个目录说明如下：
src 存放Java源代码和开源框架配置文件
	-com.ccc.test  存放Java源代码
		-controller 存放控制器类 处理jsp页面请求
			*.java 
		-hibernate 持久层相关代码
			-dao	数据访问代码
				-impl 数据访问接口实现代码
					*.java
				-interfaces 数据库访问接口定义代码
					*.java
			*.java 使用Hibernate操作数据库相关代码
		-pojo 存放业务实体类
			*.java
		-service 存放业务服务类
			*.java
		-utils 存放工具类
			*.java
	-config 存放配置文件
		*.dtd hibernate xml dtd验证文件，主要用于没有网络情况下可以使用hibernate服务
		hibernate.cfg.xml hibernate框架配置文件
		log4j.properties log4j配置文件
		spring-biz.xml 业务bean配置文件
		spring-hibernate.xml spring框架和hibernate框架集成配置文件
		spring-servlet.xml spring mvc框架配置文件
Webroot
	-META-INF
	-WEB-INF web工程资源
		-css 网页样式代码
		-docs 存放工程相关文档
		-html 存放静态网页
		-img 存放图片
		-js 存放JavaScript脚本
		-jsp 存放jsp页面
		-lib 存放工程使用带的第三方jar包
		-sql 存放数据库sql
		web.xml web工程配置文件
	index.jsp 主页
.gitignore git排除同步文件配置
.project web project描述
Readme.txt 本文件