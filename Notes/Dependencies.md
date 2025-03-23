
---

### ✅ **1️⃣ Spring Web (Type: WEB)**

> **Description:**  
Build web applications (including REST APIs) using **Spring MVC**. 

- **Spring MVC** is a web framework for building web apps and REST APIs.
- **Tomcat** is provided as the **default embedded web server**, so you don't need to install Tomcat separately.

👉 **Use cases:**
- Build REST APIs (`@RestController`, `@GetMapping`, etc.)
- Serve web pages using **Thymeleaf**, **JSP**, or other template engines.
- Accept incoming HTTP requests on ports like **8080**.

**Example usage:**
```java
@RestController
public class UrlController {
   @GetMapping("/api/shorten")
   public String shortenUrl() {
       return "shortened URL here";
   }
}
```

---

### ✅ **2️⃣ Lombok (Type: DEVELOPER TOOLS)**

> **Description:**  
A Java library that automatically generates boilerplate code like **getters, setters, constructors, `toString()`, `equals()`, etc.** at compile time.

👉 **Use cases:**
- Eliminate repetitive code
- Cleaner entity classes & DTOs

**Common Lombok annotations:**
- `@Data`: Generates getters, setters, `toString()`, `equals()`, `hashCode()`.
- `@NoArgsConstructor`: Generates a no-arg constructor.
- `@AllArgsConstructor`: Generates an all-args constructor.
- `@Builder`: Creates builder pattern for object creation.

**Without Lombok:**
```java
private String name;
public String getName() {...}
public void setName(String name) {...}
```

**With Lombok:**
```java
@Data
private String name;
```

---

### ✅ **3️⃣ Spring Data JPA (Type: SQL)**

> **Description:**  
Simplifies **data access layers** by providing a repository abstraction for **CRUD operations**, using **Hibernate** under the hood.

- Works with relational databases like **MySQL**, **PostgreSQL**, **H2**, etc.
- Uses **JPA (Java Persistence API)** standards + Spring goodies.
- Allows you to auto-generate SQL queries based on method names (`findByEmail()`, `findById()`, etc.).

👉 **Use cases:**
- Easily connect your **Java entities** (`@Entity`) to SQL tables.
- Automatic CRUD functionality using **Spring Data Repositories**.

**Example:**
```java
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortUrl(String shortUrl);
}
```

---

### ✅ **4️⃣ MySQL Driver**

> **Description:**  
The JDBC driver required to **connect to MySQL databases**.

👉 **Use cases:**
- Enables **Spring Boot** to talk to your MySQL database through JDBC.
- Works with **Spring Data JPA** to store/retrieve records in your MySQL tables.

**Without this, you can't connect to MySQL databases.**

---

### 🚀 **TL;DR Summary**

| Dependency           | Purpose                                         |
|----------------------|--------------------------------------------------|
| **Spring Web**       | Build REST APIs / web apps with Spring MVC + Tomcat |
| **Lombok**           | Auto-generate boilerplate code (getters, setters, etc.) |
| **Spring Data JPA**  | ORM layer for CRUD on SQL databases using JPA/Hibernate |
| **MySQL Driver**     | JDBC driver to connect Spring Boot to MySQL     |

---