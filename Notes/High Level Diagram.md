### **High-Level Architecture for URL Shortener Project**

```
                 +----------------------+
                 |   Client / Frontend   |
                 | (Browser / Postman)   |
                 +----------+-----------+
                            |
                            |  (HTTP Request - POST /api/shorten or GET /api/{shortUrl})
                            v
               +------------+-------------+
               |     Spring Boot App      |
               | (Running on Embedded     |
               |     Tomcat Server)       |
               +------------+-------------+
                            |
    +-----------------------+-----------------------+
    |                       |                       |
    v                       v                       v
+-----------+         +--------------+       +------------------+
| Controller|         |   Service    |       | application.properties|
| Layer     |         |  Layer       |       | (Config: DB URL,      |
| (API)     |         | (Business    |       |  JPA, Ports, etc.)    |
|           |         |  Logic)      |       +------------------+
+-----------+         +--------------+
                            |
                            v
                     +-------------+
                     | Repository  |
                     | (JPA Layer) |
                     +-------------+
                            |
                            v
                     +-------------+
                     |   Database   |
                     | (H2 / MySQL) |
                     +-------------+
```

---

### **Explanation of Each Component**

1. **Client / Frontend**
   - Sends HTTP requests (POST to shorten a URL or GET to retrieve the original URL).
   - Could be a browser, Postman, or a front-end app (React, Angular, etc.).

2. **Spring Boot Application**
   - Runs the entire application on an embedded server (e.g., Tomcat).

3. **Controller Layer**
   - Exposes REST API endpoints like `/api/shorten` and `/api/{shortUrl}`.
   - Handles incoming HTTP requests.

4. **Service Layer**
   - Contains business logic, such as:
     - Generating the short URL string.
     - Validating input URLs.
     - Any additional processing (e.g., logging, analytics).

5. **Repository Layer**
   - Handles all interactions with the database using **Spring Data JPA**.
   - Automatically provides CRUD methods (save, find, delete, etc.).

6. **Database**
   - Stores data like:
     - Original URL
     - Short URL
     - Timestamps, click counts (optional)
   - Can be **H2** (for dev/testing) or **MySQL/Postgres** (for production).

7. **application.properties**
   - Stores your app’s configuration:
     - DB connection string
     - Server port
     - JPA settings (DDL auto create/update)
     - Logging levels

---

### **Data Flow Example**

- **POST /api/shorten** with original URL:
   1. Controller receives URL.
   2. Passes to Service → generates short URL.
   3. Service calls Repository to save both URLs to DB.
   4. Returns short URL to Client.

- **GET /api/{shortUrl}**:
   1. Controller receives short URL.
   2. Service checks DB via Repository.
   3. Returns original URL or "404 Not Found" if not present.

---