## Redis Caching

`build.gradle`

```build.gradle
// redis & spring cache
implementation('org.springframework.boot:spring-boot-starter-data-redis') 
implementation('org.springframework.boot:spring-boot-starter-cache')
```

`application.yml`

```yaml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379
```

`RedisConfig`

```java
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    public CacheManager cacheManager() {
        return super.cacheManager();
    }
}
```

- `@EnableCaching` 설정



### Annotations

- `@Cacheable`
  - 캐시가 있으면 캐시의 정보를 가져오고, 없으면 등록한다.
- `@CachePut`
  - 무조건 캐시에 저장한다.
- `@CacheEvict`
  - 캐시 삭제





## local properties

```yaml
spring:
  cache:
    type: redis

  redis:
    port: 6379
    host: localhost

  datasource:
    username: sa
    password:
    url: jdbc:h2:mem:local
    driver-class-name: org.h2.Driver

  h2:
    console:
      enabled: true

  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```
..
