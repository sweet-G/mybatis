

## mybatis

#### MyBatis 是一个简化和实现了 Java 数据持久化层(persistence layer)的开源框架，它抽象了大量的 JDBC 冗余代码，并提供了一个简单易用的 API 和数据库交互。MyBatis 的前身是 iBATIS，iBATIS 于 2002 年由 Clinton Begin 创建。MyBatis 3 是 iBATIS 的全新设计，支持注解和 Mapper。MyBatis 流行的主要原因在于它的简单性和易使用性。在 Java 应用程序中，数据持久化层涉及到的工作有：将从数据库查询到的数据生成所需要的 Java 对象；将 Java 对象中的数据通过 SQL 持久化到数据库中。MyBatis  通过抽象底层的 JDBC 代码，自动化 SQL 结果集产生 Java 对象、Java 对象的数据持久化数据库中的过程使得对 SQL 的使用变得容易。

#### 官网 http://www.mybatis.org/mybatis-3/zh/getting-started.html

```
[![Build Status](https://travis-ci.org/mybatis/generator.svg?branch=master)](https://travis-ci.org/mybatis/generator)
[![Coverage Status](https://coveralls.io/repos/mybatis/generator/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/generator?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/561964c6a193340f2800033c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/561964c6a193340f2800033c)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
```

 [![Build Status](https://travis-ci.org/mybatis/generator.svg?branch=master)](https://travis-ci.org/mybatis/generator)

### 安装和配置

#### 在此使用maven完成

* 创建maven项目
* 添加依赖的jar

``` xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.40</version>
</dependency>

<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.5</version>
</dependency>
```

* 创建mybatis的主配置文件(maybatis.xml)

  #### 可以加载多个mapper文件

* 创建mapper映射配置文件

  

