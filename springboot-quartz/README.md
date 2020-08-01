一、启动配置参数
env配置：
server.port=8080;spring.quartz.scheduler-name=50
1、配置端口
server.port=8080
2、配置版本，针对不同的项目版本，需要进行不同的定时任务配置
spring.quartz.scheduler-name=50
spring.quartz.scheduler-name=51

二、配置页面
http://localhost:port/quartz
1、里面的查询是查找所有的任务，这里需要修改一下。只查找当前版本

2、任务添加的类，必须存在，不然报错。
com.xkcoding.task.quartz.job.HelloJob