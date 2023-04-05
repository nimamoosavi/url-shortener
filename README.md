# url-shortener
This project is a small and expandable project
that allows you to create a shortener link microservices in the development of Spring applications.
It implements a full set of base operations to Create a short link, fetch, and Update ratio.
Currently, it works with JPA databases in the Jdbc project and MongoDB project,
but you can expand it to work with other databases.
And you must implement and expand the common folder and general repository implementation

![ShortLink Diagram](https://github.com/nimamoosavi/url-shortener/wiki/images/link-shortener.jpg)


### Requirements
The library works with Java 11+ and spring boot version 2.7 and higher
Service Requirement: redis, mysql or another rdbms database, mongodb service

### Docker
in the docker folder, I created a sample of service that you want to start the program, and you can run it with installation the docker and run with
docker compose up command

### Key generation service (KGS)
this module in project uses rdbms auth increment to resolve the duplicated key shorter problems.
the best approach for this condition is using some coordinator service such as zookeeper, but it's a sample project for a smaller organization

### Redis
redis service used for all caches in the project for better performance

### mongoDb
the default data storage for store the data in project is mongo db service, we can use change it with any data storage, but I choose it because of easy scalability and fetch performance


### maven dependency
This project used Spring boot version 2.7.10, maven packaging and jdk 11
~~~
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-text -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-text</artifactId>
			<version>1.10.0</version>
		</dependency>
		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
			<version>5.0.1</version>
		</dependency>
	</dependencies>
~~~