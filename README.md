# Tenant Cache

Tenant specific caching implementation that's compatible with Spring Boot 2, Spring 5, and Ehcache 2.

### Build
Command line:

``` bash
./gradlew [clean] [cleanTest] [build] bootJar
```

### Start
Command line:

``` bash
java -jar build/libs/tenant-cache-1.0.0.jar
```

### Stop
Press ctrl+c.

### Experiment
Using any combination of the commands below:

``` bash
curl -X GET|DELETE [-H 'tenant-id: a'] http://localhost:8090/tenant-cache/compute/hello
curl -X GET|DELETE [-H 'tenant-id: a'] http://localhost:8090/tenant-cache/compute/bye
curl -X GET|DELETE [-H 'tenant-id: b'] http://localhost:8090/tenant-cache/compute/hello
curl -X GET|DELETE [-H 'tenant-id: b'] http://localhost:8090/tenant-cache/compute/bye
```

While verifying the (lack of) logs or the caches in-between:

``` bash
curl http://localhost:8090/tenant-cache/actuator/caches | jq
```

