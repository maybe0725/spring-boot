Spring Boot – Profile based properties and yaml example
========================================================

In Spring Boot, it picks .properties or .yaml files in the following sequences :

application-{profile}.properties and YAML variants
application.properties and YAML variants

Spring Boot에서는 **.properties** 나 **.yaml** 파일을 다음 순서로 선택합니다 :

>● application-{profile}.properties 및 YAML <br/>
>● application.properties 및 YAML 

[Externalized Configuration documentation.](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)

Multiple profiles **.properties** example.

Multiple profiles **.yml** example. In YAML, we can create multiple profiles by using a **"—"** separator.

Spring Boot, the default profile is default, we can set the profile via **spring.profiles.active** property.

## References

>● [mkyong - Example](https://www.mkyong.com/spring-boot/spring-boot-profile-based-properties-and-yaml-example/) <br/>
>● [Spring Boot – Properties & configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html) <br/>
>● [Spring Boot – Profile-specific properties](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-profile-specific-properties) <br/>
>● [Spring Boot Profiles example](https://www.mkyong.com/spring-boot/spring-boot-profiles-example/) <br/>
>● [Spring Profiles example](https://www.mkyong.com/spring/spring-profiles-example/) <br/>
>● [Spring Boot Profiles example](https://www.mkyong.com/spring-boot/spring-boot-profiles-example/)
