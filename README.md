## 简介
**FastBoot**是采用SpringBoot、SpringSecurity、Mybatis-Plus等框架，开发的一套企业级脚手架系统，使用门槛极低，且采用MIT开源协议，完全免费开源，可免费用于**商业项目**等场景。

## 使命
致力于打造成一套高质量、低BUG、易于上手、可维护性强的低代码开发脚手架系统。

## 环境搭建
- 下载项目源码，一般通过git clone命令
- idea、eclipse需安装lombok插件，不然会提示找不到get set方法
- 创建数据库fast_boot，数据库编码为utf8mb4
- 执行db/mysql.sql文件，初始化数据库脚本
- 修改application-dev.yml文件，配置MySQL、Redis账号信息
- 在fast-boot目录下，执行mvn clean install

## 本地启动
- 在fast-server工程里面，运行ServerApplication.java，则可启动项目
- 接口文档路径：http://localhost:8080/doc.html
- 再启动前端项目：https://gitee.com/makunet/fast-admin
- 账号密码：admin/admin
