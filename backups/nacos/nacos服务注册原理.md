# Nacos服务注册原理

## Nacos相关包的作用

使用Nacos分别需要导入如下两个包:

```pom
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

- spring-cloud-starter-alibaba-nacos-discovery：该项目通过自动配置以及其他 Spring 编程模型的习惯用法为 Spring Boot 应用程序在服务注册与发现方面提供和 Nacos 的无缝集成。 
- spring-cloud-starter-alibaba-nacos-config：Nacos 提供用于存储配置和其他元数据的 key/value 存储，为分布式系统中的外部化配置提供服务器端和客户端支持。

## 服务注册原理分析

注册注册的入口是org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration#bind监听org.springframework.boot.web.context.WebServerInitializedEvent事件，当WebServer初始化完毕之后发生回调bind（）方法。

分析关键类AbstractAutoServiceRegistartion(由于篇幅原因，仅分析大概流程，不详细讲解每个方法)：

```java
public abstract class AbstractAutoServiceRegistration<R extends Registration>
		implements AutoServiceRegistration, ApplicationContextAware {
    private ApplicationContext context;
    private Environment environment;
    private final ServiceRegistry<R> serviceRegistry;
	private AutoServiceRegistrationProperties properties;
    
    @EventListener(WebServerInitializedEvent.class)
    public void bind(WebServerInitializedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        // TODO 为什么要这样处理？
		if (context instanceof ConfigurableWebServerApplicationContext) {
			if ("management".equals(
					((ConfigurableWebServerApplicationContext) context).getServerNamespace())) {
				return;
			}
		}
		this.port.compareAndSet(0, event.getWebServer().getPort());
		this.start();
    }
    
    public void start() {
        // 如果未开启服务注册，直接处理完成
        if (!isEnabled()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Discovery Lifecycle disabled. Not starting");
			}
			return;
		}

		// only initialize if nonSecurePort is greater than 0 and it isn't already running
		// because of containerPortInitializer below
		if (!this.running.get()) {
             // 依托serviceRegistry实现注册
			register();
             // TODO？
			if (shouldRegisterManagement()) {
				registerManagement();
			}
             // 自身注册之后发布事件
			this.context.publishEvent(new InstanceRegisteredEvent<>(this, getConfiguration()));
             // 修改运行状态
			this.running.compareAndSet(false, true);
		}
    }
    public void stop() {}
}
```

AbstractAutoServiceRegistration中有两个属性AutoServiceRegistrationProperties、ServiceRegistry，我们从字面基本上能猜测出，AbstractAutoServiceRegistration通过AutoServiceRegistrationProperties的属性依托ServiceRegistry从而实现自动注册，所以真正实现自动注册的应该是ServiceRegistry中。

我们回到bind()方法也是就是我们的入口，它进行上下文的namespace判断之后，初始化我们启动的端口之后调用start()方法。

start()的处理逻辑，首先判断我们是否开启服务注册，否则直接跳过，然后依托serviceRegistry完成注册之后，发布注册之后的事件。

