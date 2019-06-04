Link: [**WebSite**](https://www.baeldung.com/spring-yaml) <br/>
Link: [**GitHub**](https://github.com/eugenp/tutorials/tree/master/spring-core)

Spring YAML Configuration
=========================

## 1. Overview

Spring 애플리케이션을 설정하는 방법 중 하나는 YAML 설정 파일을 사용하는 것입니다.

YAML을 사용하여 간단한 Spring Boot 애플리케이션의 여러 프로파일을 구성 할 것입니다.

## 2. Spring YAML File

Spring 프로파일은 Spring 애플리케이션이 서로 다른 환경에 대해 서로 다른 속성을 정의 할 수 있도록 도와줍니다.

다음은 두 개의 프로파일을 포함하는 간단한 YAML 파일입니다. 

두 개의 프로파일을 분리하는 **세 개의 대시(---)는 새 문서의 시작을 나타내므로 모든 프로파일을 동일한 YAML 파일에 설명 할 수 있습니다.**

application.yml 파일의 상대 경로는 /src/main/resources/application.yml 입니다.

Spring 애플리케이션은 달리 명시하지 않는 한 첫 번째 프로파일을 기본 프로파일로 사용합니다.

```````````````````````````
spring:
    profiles: test
name: test-YAML
environment: test
servers:
    - www.abc.test.com
    - www.xyz.test.com
    
---

spring:
    profiles: prod
name: prod-YAML
environment: production
servers:
    - www.abc.com
    - www.xyz.com
```````````````````````````

## 3. Binding YAML to a Config Class

properties 관련 세트를 로드하려면, Bean 클래스를 만들어야 합니다.

```````````````````````````````````````````````````````````
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
  
    private String name;
    private String environment;
    private List<String> servers = new ArrayList<>();
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public List<String> getServers() {
		return servers;
	}
	public void setServers(List<String> servers) {
		this.servers = servers;
	}
    
}
```````````````````````````````````````````````````````````

여기에서 사용된 annotation은 

>* **@Configuration**은 정의한 Bean 의 소스 클래스를 표시합니다.
>* **@ConfigurationProperties**는 외부 구성을 구성 클래스에 바인딩하고 유효성을 검사합니다.
>* **@EnableConfigurationProperties** 어노테이션은 **@ConfigurationProperties Annotated Bean**을 Spring 애플리케이션에서 사용 가능하게하는데 사용된다.

## 4. Accessing the YAML Properties

YAML 속성에 액세스하기 위해 YAMLConfig 클래스의 객체를 만들고 그 객체를 사용하여 속성에 액세스합니다.

properties 파일에서 spring.active.profiles 환경 변수를 prod로 설정합시다. spring.profiles.active를 정의하지 않으면 기본적으로 YAML 파일에 정의 된 첫 번째 profiles 속성으로 설정됩니다.

등록 정보 파일의 상대 경로는 /src/main/resources/application.properties입니다.

`````````````````````````````````
spring.profiles.active=prod
`````````````````````````````````

이 예제에서는 CommandLineRunner를 사용하여 properties 을 표시합니다.

```````````````````````````````````````````````````````````````````````````````````````
@SpringBootApplication
public class MyApplication implements CommandLineRunner {
 
    @Autowired
    private YAMLConfig myConfig;
 
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.run();
    }
 
    public void run(String... args) throws Exception {
    	System.out.println("\n==================================================");
        System.out.println("using environment: " + myConfig.getEnvironment());
        System.out.println("name: " + myConfig.getName());
        System.out.println("servers: " + myConfig.getServers());
        System.out.println("==================================================\n");
    }
}
```````````````````````````````````````````````````````````````````````````````````````

MyApplication 실행 결과

```````````````````````````````````````````````````
==================================================
using environment: production
name: prod-YAML
servers: [www.abc.com, www.xyz.com]
==================================================
```````````````````````````````````````````````````
