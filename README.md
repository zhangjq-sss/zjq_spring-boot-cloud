1.使用jasypt进行加密
当我使用了jasypt进行配置文件加解密后，如果再使用refresh 去刷新配置，则自动加解密会失效。

原因分析：刷新不是我之前想象的直接调用config获取最新配置的，而是通过重新创建一个SpringBoot环境（非WEB），等到SpringBoot环境启动时就相当于重新启动了一个非web版的服务器。此时config会自动加载到最新的配置。这个过程类似于启动服务器。相当于是重新启动了一个springboot环境，而这个环境中没有加解密功能。拿回的配置，都是原始字符串。

2.项目框架说明
eureka-server：注册中心
eureka-config：配置中心
eureka-payment：支付，加密生成
user：redis和redis集群，mybatis+mysql多数据源，
priduct：产品
hystrix：容错保护机制
gateway：网关。

3.注册中心出现UNKNOWN错误
原因：eureka.client.healthcheck.enabled=true放入了bootstrap.properties中导致提前检查
解决方案：放入application.properties即可解决。

4.config bus rabbitmq bus/refresh动态刷新，更改不成功
解决方案：需要加入@RefreshScope注解，目前不支持动态刷新端口号和数据源连接等等。

5.出现RejectedExecutionException问题
需要关闭：eureka.client.healthcheck.enabled=false
目前没有更好的办法 欢迎推荐。

6.spring cloud zuul
基于Netflix Zuul实现的API网关组建。

7.spring cloud ribbon
基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。

8.spring cloud stream
消息驱动的微服务

9.spring cloud hystrix
服务容错保护

10.spring cloud sleuth
服务跟踪管理
ELK
与logstash整合：日志输出
与zipkin整合：提供ui组件更直观跟踪和分析



