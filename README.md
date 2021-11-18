# cloudfactory
1	概述Overview
项目简介：
智能制造云平台系统，基于云计算资源构建集中数据管理，实现信息共享、为使用者提供的工厂生产数据以及产能状态。使用web程序，网页浏览，无需安装、操作方便，具有良好的系统扩充能力。完整的生产工作流模式，实现在线智能制造管理功能。可扩展性，后续可对接线上设备操控系统、设备租赁系统、产能交易系统、电商接单平台、产品发货系统。

2	相关技术Relevant Technologies
项目开发环境：
开发工具：IntelliJ IDEA 2020.3.3 
Jdk：1.8
服务器：apache-tomcat-7.0.108
Maven
数据库：Mysql

项目技术：
1、使用mybaits数据库框架对数据库进行持久化操作，使用spring对项目中的类进行管理。使用springMVC web框架。后端使用了SSM框架技术。
2、前端使用了，css、jquerry、js、jsp等相关技术。前端UI使用了layui 前端UI框架。
3、前后端数据交互使用ajax异步数据请求。使用json传递参数，并使用了fastjson第三方json处理组件和springmvc的responsebody响应体来处理json相关数据。

系统设计System Design
1、后端使用了SSM框架技术。使用mybaits数据库框架对数据库进行持久化操作并使用了数据库连接池，使用spring对项目中的类进行管理。使用springMVC web框架。
2、前端UI使用了layui 前端UI框架。
3、前后端数据交互使用ajax异步数据请求并使用json传递参数。
4、数据库使用Mysql
5、MVC三层架构，项目分为DAO层、service层、Control层以及前端jsp界面。
