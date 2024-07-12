> The objective of this endeavor is to streamline the development process of a backend administration project, facilitating a more seamless software engineering experience.

The technologies employed in this project include:
- Spring Boot 3.0
- Spring Data JPA
- Vue 3.0
- MySQL 8.0
- Redis

Deployment of the server-side components
```shell 
gradle build
java -jar start/build/libs/start-0.0.1-SNAPSHOT.jar
```

Deployment of the front-end interface
```shell
cd app-web/
yarn install
yarn run build
```

Configuration file for Nginx
```conf
 server {
    listen       80;
    server_name  localhost;
    charset utf-8;

    location / {
        root   /home/app/app/app-web/dist;
        try_files $uri $uri/ /index.html;
        index  index.html index.htm;
    }

    location /api/ {
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header REMOTE-HOST $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://localhost:8085/;
    }

    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   html;
    }
}
```


Efficient generation of CRUD operations based on entity classes
Example
```java
@RestController
@RequestMapping("/test")
public class TestController extends CurdController<TestService, TestDo, Long> {

}
```

```java
public interface TestService extends CurdService<TestDo, Long> {
}
```

```java
@Service
public class TestServiceImpl extends CurdServiceImpl<TestRepository, TestDo, Long> implements TestService {
}
```

```java
public interface TestRepository extends JpaRepository<TestDo, Long> {
}
```


The CRUD interfaces for managing tests are now implemented. Further adjustments can be made based on specific requirements.
