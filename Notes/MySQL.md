---

````markdown
# MySQL Setup with Docker & Spring Boot Integration

## Docker: MySQL Setup

### Pull MySQL Image

```bash
docker pull mysql:8.0
```
````

### Run MySQL Container

```bash
docker run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=anypassword \
  -e MYSQL_DATABASE=url_shortner \
  -p 3306:3306 \
  -d mysql:8.0
```

### Verify Running Container

```bash
docker ps
```

---

## Spring Boot & MySQL Integration

### Add Dependency (pom.xml)

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortner
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Start Spring Boot

```bash
./mvnw spring-boot:run
```

---

## MySQL CLI via Docker

### Connect to MySQL CLI

```bash
docker exec -it mysql-container mysql -u root -p
```

---

## Basic MySQL Commands

### Show Databases

```sql
SHOW DATABASES;
```

### Select Database

```sql
USE url_shortner;
```

### Show Tables

```sql
SHOW TABLES;
```

### Describe Table

```sql
DESCRIBE url_mapping;
-- or --
SHOW COLUMNS FROM url_mapping;
```

### View Rows

```sql
SELECT * FROM url_mapping;
```

### Find NULL Values

```sql
SELECT * FROM url_mapping WHERE column_name IS NULL;
```

### Check Multiple NULLs

```sql
SELECT * FROM url_mapping WHERE column1 IS NULL OR column2 IS NULL;
```

---

## Postman Testing

### POST - Create Short URL

```
POST http://localhost:8080/api/shorten
Headers: Content-Type: application/json
Body:
{
  "originalUrl": "https://example.com"
}
```

### GET - Redirect Short URL

```
GET http://localhost:8080/api/{shortCode}
```

### GET - Fetch All URLs

```
GET http://localhost:8080/api/all
```

```
---
```
