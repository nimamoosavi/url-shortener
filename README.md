# url-shortener
This project is a small and expandable project
that allows you to create a shortener link microservices in the development of Spring applications.
It implements a full set of base operations to Create a short link, fetch, and Update ratio.
Currently, it works with JPA databases in the Jdbc project and MongoDB project,
but you can expand it to work with other databases.
And you must implement and expand the common folder and general repository implementation
### Requirements
The library works with Java 11+, redis, mysql or another rdbms database, mongodb service

### Docker
in the docker folder, I created a sample of service that you want to start the program, and you can run it with installation the docker and run with
docker compose up command

### Key generation service (KGS)
this module in project uses rdbms auth increment to resolve the duplicated key shorter problems.
the best approach for this condition is using some coordinator service such as zookeeper, but it's a sample project for a smaller organization

### Redis
redis service used for all caching in a project for better performance

### mongoDb
the default data storage for store the data in project is mongo db service, we can use change it with any data storage, but I choose it because of easy scalability and fetch performance