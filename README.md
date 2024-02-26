# ”天南地北“旅游攻略系统

## 介绍

项目命名为travel_strategy，本项目是希望细化旅游平台的功能，将旅游攻略拆分出来形成一个单独的系统。项目为“天南地北”旅游攻略系统，是基于Spring Cloud Alibaba + vue的在线旅游攻略查询平台，集成树洞发布、攻略浏览、游记发布和后台管理等功能。



## 技术选型

### 后端技术:

| 技术                   | 名称                      | 官网                                                         |
| :--------------------- | ------------------------- | ------------------------------------------------------------ |
| `Maven`                | 项目构建管理              | [ http://maven.apache.org/](http://maven.apache.org/)        |
| `Spring Boot`          | 框架                      | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| `Spring Cloud`         | Hoxton.SR9                | [https://spring.io/projects/spring-cloud](https://spring.io/projects/spring-cloud) |
| `Spring Cloud Alibaba` | Cloud Alibaba组件         | [https://github.com/alibaba/spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba) |
| `Spring Cloud Gateway` | 网关组件                  | [ https://spring.io/projects/spring-cloud-gateway](https://spring.io/projects/spring-cloud-gateway) |
| `MySQL`                | MySQL数据库               | [https://www.mysql.com/](https://www.mysql.com/)             |
| `Redis`                | 分布式缓存数据库          | [ https://redis.io/](https://redis.io/)                      |
| `MyBatis-Plus`         | MyBatis增强工具           | [https://www.baomidou.com/](https://www.baomidou.com/)       |
| `Swagger`              | 项目API文档生成及测试工具 | [https://swagger.io](https://swagger.io)                     |

### 前端技术:

| 技术        | 名称                   | 官网                                                         |
| :---------- | :--------------------- | ------------------------------------------------------------ |
| `node`      | js的服务器运行环境     | [https://nodejs.org/en](https://nodejs.org/en)               |
| `vue`       | 前端框架               | [https://cn.vuejs.org/](https://cn.vuejs.org/)               |
| `Nuxt`      | 专注于UI渲染的前端框架 | [https://nuxtjs.org/](https://nuxtjs.org/)                   |
| `ElementUI` | 前端UI框架             | [https://element.eleme.cn/#/zh-CN](https://element.eleme.cn/#/zh-CN) |



## 开发环境版本说明

- JDK：OpenJDK 8

- SpringBoot：2.3.7.RELEASE

- Spring Cloud：Hoxton.SR9

- Spring Cloud Alibaba：2.2.2.RELEASE

- MyBatis-Plus：3.4.2

- Swagger：2.7.0

- node：v14.5.0

- npm：6.14.5

- vue：vue-admin-template模板

- Nuxt：Nuxt 2

- 使用的Docker镜像

  | 镜像  | 版本   | 端口 |
  | ----- | ------ | ---- |
  | MySQL | 5.7    | 3306 |
  | Redis | 6.0.8  | 6379 |
  | Nacos | latest | 8848 |

  

## 项目结构介绍

### 后端:

```
travel_strategy_demo
|-- common                          		|| 通用工具包
|   |-- common_service                      || 通用服务配置类
|   |-- common_utils                        || 通用工具类
|-- model                          			|| 实体类
|-- service                  				|| 微服务
|   |-- service_city                        || 后台微服务，提供后台的API接口
|   |-- service_dict                        || 数据字典微服务，提供数据字典相关的API接口
|   |-- service_user                        || 前台微服务，提供前台的API接口
|-- service-gateway                        	|| 微服务网关，支持动态路由加载
```

### 前端:

```
travel_strategy
|-- travel-strategy-site                    || 前台
|   |-- .nuxt                       		|| 提供服务API接口
|   |-- api                       			|| 提供服务API接口
|   |-- assets                 				|| 主题 字体等静态资源
|   |-- components             				|| 全局公用组件
|   |-- layout                 				|| 全局 layout
|   |-- middleware                       	|| 提供中间件
|   |-- pages                       		|| 所有页面
|   |-- plugins                       		|| 插件配置
|   |-- static                       		|| 静态文件
|   |-- store                       		|| 全局 store管理
|   |-- utils                       		|| 全局公用方法
|   |-- .eslintrc.js                       	|| eslint 配置项
|   |-- nuxt.config.js                      || nuxt配置
|   |-- package.json                       	|| package.json
|-- vue-admin-template-master               || 用于完成MySQL数据库binlog收集的canal客户端程序
|   |-- build                      			|| 构建相关
|   |-- mock                       			|| 项目mock 模拟数据
|   |-- public                     			|| 静态资源
|   |-- |-- favicon.ico            			|| favicon图标
|   │   └-- index.html             			|| html模板
|   |-- src                        			|| 源代码
|   │   |-- api                    			|| 所有请求
|   │   |-- assets                 			|| 主题 字体等静态资源
|   │   |-- components             			|| 全局公用组件
|   │   |-- icons                  			|| 项目所有 svg icons
|   │   |-- layout                 			|| 全局 layout
|   │   |-- router                 			|| 路由
|   │   |-- store                  			|| 全局 store管理
|   │   |-- styles                 			|| 全局样式
|   │   |-- utils                  			|| 全局公用方法
|   │   |-- views                  			|| views 所有页面
|   │   |-- App.vue                			|| 入口页面
|   │   |-- main.js                			|| 入口文件 加载组件 初始化等
|   │   |-- permission.js          			|| 权限管理
|   |-- tests                      			|| 测试
|   |-- .env.xxx                   			|| 环境变量配置
|   |-- .eslintrc.js               			|| eslint 配置项
|   |-- .babelrc                   			|| babel-loader 配置
|   |-- .travis.yml                			|| 自动化CI配置
|   |-- vue.config.js              			|| vue-cli 配置
|   |-- postcss.config.js          			|| postcss 配置
|   └-- package.json               			|| package.json
```



## 快速开始

1. 默认端口启动nacos、redis、mysql。找到 `travel_strategy.sql` 文件，在mysql数据库中建立 `travel_strategy` 仓库并执行完成数据初始化操作。

2. 使用IDEA打开后端源代码，maven会根据 `pom.xml` 文件自动导入需要的依赖。根据`service`中`service-user`下 `application.yml` 的图片的存储路径 `travel-strategy.path` ，找到 `img.rar` 压缩包的文件并在本地对应的路径下创建文件夹解压到该路径下。

   ```
   travel-strategy:
     path: D:\upload\travel-strategy\
   ```

3. 修改各个微服务中的application.yml文件中的nacos的访问地址`spring.cloud.nacos.discovery.server-addr` 和redis的访问地址`spring.redis.host` 和端口号`spring.redis.port` 。

   ```
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: 110.41.136.209:8848
     redis:
       host: 110.41.136.209
       port: 6379
   ```

4. 修改mysql的访问地址`spring.datasource.url`和用户名`spring.datasource.username` 密码`spring.datasource.password` 。

   ```
   spring:
     datasource:
       driver-class-name: com.mysql.jdbc.Driver
       url: jdbc:mysql://110.41.136.209:3306/travel_strategy?characterEncoding=utf-8&useSSL=false
       username: root
       password: 12345
   ```

5. 在IDEA输出栏的`services`选项中启动 `ServiceCityApplication(后台API接口服务)`、`ServiceUserApplication(前台API接口服务)`、`ServiceGatewayApplication(网关服务)`、`ServiceDictApplication(数据字典服务)`四个微服务。

6. 使用vscode打开前端源代码，通过集成终端打开`travel-strategy-site` 和 `vue-admin-template-master` ，分别执命令下载需要的依赖包。

   ```
   npm install
   ```

7. 下载完成之后，使用启动命令分别启动前端服务的前台项目 `vue-admin-template-master` (启动之后会自动启动浏览器并跳转到 `http:localhost:9528` ，但未登录和非管理员账号登录的情况下没有权限访问该路径)和后台项目 `travel-strategy-site`。

   ```
   npm run dev
   ```

8. 启动完成后访问登录页面 `http://localhost:3000`。



## 服务网关说明

- `http://localhost/*/city/**` 对应访问 `service-city` 服务
- `http://localhost/*/dict/**` 对应访问 `service-dict` 服务
- `http://localhost/*/user/**` 对应访问 `service-user` 服务
- `http://localhost/images/**` 对应访问 `service-user` 服务

> 可通过修改 service-gateway 中的 application.yml 改变路由的路径

```yml
spring:
  cloud:
    gateway:
      routes[0]:
        id: service-city
        uri: lb://service-city
        predicates: Path=/*/city/**
      routes[1]:
        id: service-dict
        uri: lb://service-dict
        predicates: Path=/*/dict/**
      routes[2]:
        id: service-user
        uri: lb://service-user
        predicates: Path=/*/user/**
      routes[3]:
        id: service-user
        uri: lb://service-user
        predicates: Path=/images/**
```

