Link: [WebSite](https://www.baeldung.com/spring-boot-configure-data-source-programmatic) <br/>
Link: [GitHub](https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-boot-persistence)

Spring Boot DataSource 설정하기.
==============================

# 1. Overview

Spring Boot 는 독창적 인 알고리즘을 사용하여 데이터 소스를 검색하고 구성합니다. 

이를 통해 기본적으로 완전히 구성된 DataSource 구현을 쉽게 얻을 수 있습니다.

또한 Spring Boot는 classpath에있는 순서에 따라 HikariCP, Apache Tomcat 또는 Commons DBCP 순으로 초고속 연결 풀을 자동으로 구성합니다.

Spring Boot의 자동 DataSource 설정은 대부분의 경우 잘 작동하지만 때로는 더 높은 수준의 제어가 필요하기 때문에 자체 DataSource 구현을 설정해야하므로 자동 구성 프로세스가 생략됩니다.

이 튜토리얼에서는 스프링 부트에서 프로그래밍 방식으로 데이터 소스를 구성하는 방법을 학습합니다.



# 2. The Maven Dependencies

프로그래밍 방식으로 DataSource 구현을 작성하는 것은 간단합니다.

이를 수행하는 방법을 배우기 위해 JPA 엔티티 중 일부에서 CRUD 작업을 수행하는 간단한 저장소 계층을 구현합니다.

데모 프로젝트의 종속성을 살펴 보겠습니다.

``````````````````````````````````````````````````````````````
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.4.1</version> 
    <scope>runtime</scope> 
</dependency>
``````````````````````````````````````````````````````````````

위에 표시된 것처럼 우리는 메모리 내 H2 데이터베이스 인스턴스를 사용하여 저장소 계층을 실행합니다. 

그렇게함으로써 값 비싼 데이터베이스 작업을 수행하는 비용없이 프로그래밍 방식으로 구성된 DataSource를 테스트 할 수 있습니다.

또한 Maven Central에서 spring-boot-startter-data-jpa의 최신 버전을 확인하십시오.



# 3. Configuring a DataSource Programmatically

Spring Boot의 자동 DataSource 설정을 고수하고 현재 상태로 프로젝트를 실행하면 예상대로 작동합니다.

스프링 부트는 우리에게 모든 중장비 배관을 맡길 것입니다. 

여기에는 HikariCP, Apache Tomcat 또는 Commons DBCP에 의해 자동으로 처리되고 인 메모리 데이터베이스 인스턴스를 설정하는 H2 DataSource 구현이 포함됩니다.

또한 Spring Boot는 기본 데이터베이스 설정을 제공하기 때문에 application.properties 파일을 생성 할 필요가 없습니다.

앞에서 언급했듯이 더 높은 수준의 사용자 정의가 필요할 때가 있으므로 우리는 자체적으로 DataSource 구현을 프로그래밍 방식으로 구성해야합니다.

이를 수행하는 가장 간단한 방법은 DataSource 팩토리 메소드를 정의하고 @Configuration 주석으로 주석 된 클래스 내에 배치하는 것입니다.

`````````````````````````````````````````````````````````````````````````````
@Configuration
public class DataSourceConfig {
     
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
`````````````````````````````````````````````````````````````````````````````

이 경우 우리는 사용자 정의 DataSource 객체를 프로그래밍 방식으로 생성하기 위해 Joshua Bloch의 빌더 패턴의 유창하지 않은 버전 인 편리한 DataSourceBuilder 클래스를 사용했습니다.

이 방법은 빌더가 몇 가지 공통된 특성을 사용하여 DataSource를 쉽게 구성 할 수 있기 때문에 정말 좋습니다. 또한 기본 연결 풀도 사용합니다.



# 4. Externalizing DataSource Configuration with the application.properties File

물론 DataSource 구성을 부분적으로 외부화 할 수도 있습니다. 예를 들어 팩토리 메서드에서 기본적인 DataSource 속성을 정의 할 수 있습니다.

`````````````````````````````````````````````````````````````````````````````
@Bean
public DataSource getDataSource() { 
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); 
    dataSourceBuilder.username("SA"); 
    dataSourceBuilder.password(""); 
    return dataSourceBuilder.build(); 
}
`````````````````````````````````````````````````````````````````````````````

그리고 application.properties 파일에 몇 가지 추가 파일을 지정하십시오.

`````````````````````````````````````````````````````````````````````````````
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driver-class-name=org.h2.Driver
`````````````````````````````````````````````````````````````````````````````

위의 application.properties 파일이나 @ConfigurationProperties로 주석 된 클래스를 통해 외부 소스에 정의 된 특성은 Java API에 정의 된 특성을 대체합니다.

이 접근법을 통해 우리는 더 이상 DataSource 구성 설정을 한 곳에서 보관하지 않게됩니다.

다른 한편으로, 우리는 컴파일 타임과 런타임 설정을 서로 잘 분리하여 유지할 수 있습니다.

구성 바인딩 지점을 쉽게 설정할 수 있으므로 정말 좋습니다. 그렇게하면 bean factory 메소드를 리팩토링하지 않고도 다른 소스의 다른 DataSource 설정을 포함 할 수 있습니다.



# 5. Testing the DataSource Configuration

사용자 정의 DataSource 구성을 테스트하는 것은 매우 간단합니다. 전체 프로세스는 JPA 엔티티 작성, 기본 저장소 인터페이스 정의 및 저장소 계층 테스트로 이어집니다.



## 5.1. Creating a JPA Entity

사용자를 모델링 할 샘플 JPA 엔티티 클래스를 정의 해 보겠습니다.

`````````````````````````````````````````````````````````````````````````````
@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
 
    // standard constructors / setters / getters / toString
     
}
`````````````````````````````````````````````````````````````````````````````



## 5.2. A Simple Repository Layer

다음으로 위에서 정의한 User 엔티티 클래스의 인스턴스에 대해 CRUD 작업을 수행 할 수있는 기본 리포지토리 계층을 구현해야합니다.

우리가 Spring Data JPA를 사용하고 있기 때문에 우리는 자체 DAO 구현을 처음부터 만들 필요가 없습니다. CrudRepository 인터페이스를 확장하여 작동하는 저장소 구현을 얻으면됩니다.

`````````````````````````````````````````````````````````````````````````````
@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
`````````````````````````````````````````````````````````````````````````````



## 5.3. Testing the Repository Layer

마지막으로 프로그래밍 방식으로 구성된 DataSource가 실제로 작동하는지 확인해야합니다. 통합 테스트를 통해이를 쉽게 수행 할 수 있습니다.

`````````````````````````````````````````````````````````````````````````````
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
     
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        userRepository.save(new User("Bob", "bob@domain.com"));
        List<User> users = (List<User>) userRepository.findAll();
         
        assertThat(users.size()).isEqualTo(1);
    }    
}
`````````````````````````````````````````````````````````````````````````````

UserRepositoryIntegrationTest 클래스는 꽤 자명합니다. 엔티티를 유지하고 찾을 수있는 저장소 인터페이스의 두 가지 CRUD 메소드를 수행합니다.

프로그래밍 방식으로 DataSource 구현을 구성할지 아니면 Java 구성 메소드와 application.properties 파일로 프로그래밍할지 여부에 관계없이 작업 데이터베이스 연결을 항상 가져야합니다.



## 5.4. Running the Sample Application

마지막으로 표준 main () 메소드를 사용하여 데모 애플리케이션을 실행할 수 있습니다.

`````````````````````````````````````````````````````````````````````````````
@SpringBootApplication
public class Application {
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
    @Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            User user1 = new User("John", "john@domain.com");
            User user2 = new User("Julie", "julie@domain.com");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.findAll().forEach(user -> System.out.println(user);
        };
    }
}
`````````````````````````````````````````````````````````````````````````````

우리는 이미 저장소 계층을 테스트 했으므로 DataSource가 성공적으로 구성되었는지 확신합니다. 

따라서 샘플 애플리케이션을 실행하면 콘솔 출력에서 데이터베이스에 저장된 사용자 엔티티의 목록을 볼 수 있습니다.



# 6. Conclusion

이 튜토리얼에서는 스프링 부트에서 프로그래밍 방식으로 DataSource 구현을 구성하는 방법을 배웠다.

평소대로이 튜토리얼에 표시된 모든 코드 샘플은 GitHub에서 사용할 수 있습니다.
