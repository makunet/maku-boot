## 说明
maku-boot 是采用组件模式，扩展不同的业务功能，可以很方便的实现各种业务需求，且不会导致系统臃肿，若想使用某个组件，按需引入即可，反之亦然。

## 引入
如果需要使用对应的组件，如：`maku-boot-quartz`，则需要在`maku-boot/maku-server/pom.xml`里面引入，如下所示：

```xml
<dependency>
    <groupId>net.maku</groupId>
    <artifactId>maku-boot-quartz</artifactId>
    <version>${revision}</version>
</dependency>
```

## SQL语句
引入组件时，还需要执行对应的SQL文件，初始化表结构和菜单等。
如果使用的是MySQL数据库，则需要执行以下SQL文件：

```
maku-boot/db/mysql/module/maku-module-quartz.sql
```