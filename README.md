我们采用 RESTful API 接口完成前端数据交互，选择 Spring Boot 2.0.0.RELEASE 和 MyBatis 3.4 作为项目框架，并且采用 MySQL 存储活动报名信息，通过 MongoDB 来存储活动评论数据，保证表结构的扩展性，将要进行搜索的活动数据冗余到 ElasticSearch 上便于活动搜索，此外通过 Redis 存储“活动点赞”数据，提高查询性能。同时，我们还尝试使用 RabbitMQ 实现用户报名活动后进行推送邮件的功能。最后，我们使用 Spring Security Oauth2 来实现多个微服务的统一认证授权，用户在客户端使用用户名和密码在用户中心获得授权，然后客户端在访问应用是附上 Token 令牌。
案例虽小，五脏俱全。希望读者结合前面 16 章的内容，以及最后第 17 章的案例实战，可以完整地了解到服务端所有技术面，并收获解决问题的思路和方法，能够快速在实践中加以运用。

## 版本说明
- Spring Boot 2.0.0.RELEASE
- MyBatis 3.4
- MySQL 5.7
- MongoDB 3.x
- ElasticSearch 5.6.8
- Redis 3.x
- RabbimtMQ 3.6.2
- Spring Security Oauth 2.3.0.RELEASE

## 文章内容
1. 详细设计中提炼 API 接口
1. 技术选型决定具体方案
1. MySQL 案例实战 – 实现活动报名
1. MongoDB 案例实战 – 实现活动评论
1. ElasticSearch 案例实战 – 实现活动搜索
1. Redis 案例实战 – 实现活动点赞
1. RabbitMQ 案例实战 – 发送邮件
1. Oauth2 案例实战 – 实现安全认证
1. 案例小结