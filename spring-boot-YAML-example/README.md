Spring Boot YAML example
=========================

스프링 부트의 properties 파일 대신 YAML을 사용하는 방법을 설명한다.

간단히 말해, **src/resources** 폴더에 **application.yml**을 만들고, Spring Boot는 .yml 파일을 자동으로로드하고 파싱하여 

**@ConfigurationProperties**로 주석 처리 한 클래스에 값을 바인딩합니다

YAML 파일은 **@ PropertySource**를 사용하여로드 할 수 없습니다.

스프링 부트는 SnakeYAML 라이브러리를 사용하여 YAML 파일을 구문 분석하고, SnakeYAML 라이브러리는 spring-boot-startter가 제공합니다

스프링 부트(Spring Boot)는 YAML 파일을로드하고 파싱하고 다음 **@ConfigurationProperties** 클래스의 값을 바인딩합니다.

## References

>● [Mkyong.com - Example](https://www.mkyong.com/spring-boot/spring-boot-yaml-example/)

>● [YAML](https://yaml.org/)

>● [Wikipedia - YAML](https://en.wikipedia.org/wiki/YAML)

>● [SnakeYAML](https://bitbucket.org/asomov/snakeyaml/src/default/)

>● [Using YAML Instead of Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-yaml)

>● [Spring Boot @ConfigurationProperties example](https://www.mkyong.com/spring-boot/spring-boot-configurationproperties-example/)
