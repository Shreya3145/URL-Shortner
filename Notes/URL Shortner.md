# Designing a Scalable URL Shortening Service

Designing a scalable URL shortening service involves far more than simply mapping long URLs to short ones. It requires careful planning around system scale, data management, and fault tolerance to ensure high performance under heavy traffic. **This concept is tinyurl/bitly system design.**

## 📊 Understanding Scale and Assumptions

The design starts with defining key assumptions such as expected traffic volume and URL length.

- The system should be capable of handling around **10% of Twitter's user base**, or approximately **30 million users per month**.
- Each shortened URL is expected to be **7 characters long**.
- Estimated data storage per URL is about **2.031 KB**.
- This equates to approximately **60.7 GB per month** or around **3.6 TB over five years**.

## 🔗 URL Generation Logic

The core functionality centers on generating unique 7-character short URLs. Two primary methods include:

- **Base62 Encoding:**  
  Converts numerical IDs to alphanumeric strings, supporting around **3.5 trillion combinations** for 7-character IDs. It is highly scalable and collision-resistant.

- **MD5 Hashing:**  
  Produces a hash from the long URL, but is more prone to collisions and typically requires truncating the hash to 7 characters. Only 10 million combinations are possible.

> **Note:** Base62 is generally preferred due to its higher efficiency and lower risk of collision compared to base10 or hash-based methods.

## 🗄️ Database Design and Challenges

### Database Options:
- **Relational Databases (RDBMS):**  
  Offer ACID properties and strong consistency but may struggle with scaling under high write volumes.

- **NoSQL Databases:**  
  Provide better horizontal scalability and performance under large workloads but often rely on eventual consistency.

### Collision Handling:
- In a distributed setup, multiple servers might generate the same short URL for different long URLs.
- To avoid this, safeguards like `"insert if not present"` are necessary, more commonly available in RDBMS than some NoSQL systems.

## ⚙️ Achieving Scalability

### Centralized Counters:
- A counter-based approach guarantees unique ID generation but requires coordination across multiple servers.

### Zookeeper:
- **Zookeeper** is introduced as a distributed coordination service to manage and synchronize counter ranges across app servers, preventing overlap and conflicts.

## 🏗️ Distributed System Architecture

A distributed architecture should include:

- **Load Balancer:**  
  Distributes incoming requests to multiple application servers.

- **Application Servers:**  
  Each server is assigned a counter range by Zookeeper to independently generate unique short URLs without constant database queries.

- **Zookeeper Coordination:**  
  Ensures counter ranges are managed and reassigned during server failures or when ranges are exhausted.

- **Database Layer:**  
  Stores the mapping of short URLs to long URLs, backed by a scalable NoSQL solution with a replication factor and consistency level (typically set to **3** for strong consistency).

- **Caching Layer:**  
  Frequently accessed URLs are cached (e.g., using **Redis** or **Memcached**) for faster retrieval and reduced database load.

---

## 🖥️ Explanation of the Diagram Components

### **Load Balancer**
- Distributes user requests (shorten URL / retrieve URL) evenly across all available application servers.

### **Application Servers**
- Hosts the core URL shortening service logic (short URL generation, API handling).
- Each server is assigned a unique counter range by Zookeeper (e.g., X, Y, Z).
- Servers generate short URLs independently within their assigned range to avoid conflicts.

### **Zookeeper (Coordination Layer)**
- Manages distributed counters.
- Assigns unique counter ranges to each server.
- Handles leader election and counter reassignments in case of server failure.

### **Cache (Optional Layer)**
- Frequently accessed short URL -> long URL mappings are cached to reduce database reads.
- Could be Redis or Memcached for low-latency lookups.

### **Database Layer (NoSQL)**
- Stores long URL, short URL, creation date, expiration, and analytics metadata.
- Can be configured with replication factor = **3** for high availability and eventual consistency.
- Handles collision checks and inserts.

---

## 🌟 Essential Features

For a production-grade system, include:

- **APIs:**  
  RESTful APIs for creating short URLs and retrieving the original long URLs.

- **Analytics:**  
  Integration with analytics tools (e.g., Google Analytics, Elasticsearch) to track system performance and usage statistics.

---

> ✅ This system is designed to handle high traffic while ensuring data integrity, low latency, and scalability.


```
                       +-------------------+
                       |    Load Balancer   |
                       +--------+----------+
                                |
       +------------------------+------------------------+
       |                        |                        |
+------v------+          +------v------+          +------v------+
| App Server 1 |          | App Server 2 |          | App Server 3 |
| (Counter X)  |          | (Counter Y)  |          | (Counter Z)  |
+------+-------+          +------+-------+          +------+-------+
       |                         |                        |
       +------------+------------+------------+-----------+
                    |                         |
          +---------v--------+       +--------v---------+
          |   Zookeeper      |       |     Cache (e.g., |
          | (Coordination    |       |   Redis/Memcached)|
          |   & Counter Mgr) |       +-------------------+
          +---------+--------+                |
                    |                         |
      +-------------v-------------+           |
      | Database Layer (NoSQL)    |<----------+
      | (e.g., Cassandra/MongoDB) |
      +---------------------------+
```
