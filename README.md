

## MyBatis

#### MyBatis is an open - source framework for simplifying and implementing the Java data persistence layer (persistence layer), which abstracts a large amount of JDBC redundant code and provides a simple and easy to use API and database interaction. The predecessor of MyBatis was iBATIS, and iBATIS was created by Clinton Begin in 2002. MyBatis 3 is the new design of iBATIS, supporting annotations and Mapper. The popularity of MyBatis is mainly due to its simplicity and ease of use. In Java applications, the data persistence layer involves the work of generating the required Java objects from the data querying from the database; persisting the data in the Java object to the database through SQL. By abstracting the underlying JDBC code, MyBatis automate the process in which the SQL result set produces the data persistence database of the Java object and the Java object that makes the use of SQL easier.

 [![Build Status](https://travis-ci.org/mybatis/generator.svg?branch=master)](https://travis-ci.org/mybatis/generator)[![Coverage Status](https://coveralls.io/repos/mybatis/generator/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/generator?branch=master)[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator)[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)[![Dependency Status](https://www.versioneye.com/user/projects/561964c6a193340f2800033c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/561964c6a193340f2800033c)

 ![mybatis-generator](http://mybatis.github.io/images/mybatis-logo.png)

 #### This is created using the Maven project, with a version of 3.4.5 

### Configuration

#### Mater configurstion file(mybatis.xml)

#### Mapping configuration file (studentmapr.xml)

#### In mapper can be loaded in the main configuration file

### Other configuration

#### settings

``` xml
<!-- Convert the underscore to the hump nomenclature -->
<setting name="mapUnderscoreToCamelCase" value="true"/>
```

#### typeAliases

``` xml
<typeAlias type="com.kaishengit.entity.Product" alias="Product"/>

<!-- All classes in the package have a class name with a lowercase alias -->
<package name="com.kaishengit.entity"/>
```

### Use the CRUD operation of the database

### Use the CRUD operation of the interface

### Multi-table LianZha

#### association: one to one;for one more

#### collection: one to many







