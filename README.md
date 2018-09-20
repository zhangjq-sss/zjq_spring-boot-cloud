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
gateway：网关
