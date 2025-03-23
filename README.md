# 📌 URL Shortener Service
🚧 Work in Progress!

A **scalable and distributed URL shortening service** built with **Spring Boot** and **MySQL**. This project transforms long URLs into shortened links, provides click analytics, and is designed for high availability and cloud deployment.

---

## 🚀 Features

- 🔗 Generate short URLs from long URLs
- 🔄 Redirect short URLs to original long URLs
- 📊 Track click analytics (timestamps, click count)
- ⚡ High-performance design with caching (Redis/Memcached)
- ☁️ Distributed architecture with Zookeeper coordination
- 🔐 Optional user management (registration, login)
- 🛠️ RESTful APIs for easy integration
- 📈 Ready for containerization and cloud deployment

---

## 🏗️ Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Database:** MySQL
- **Cache:** Redis / Memcached
- **Coordination Service:** Zookeeper (for distributed ID generation)
- **Build Tool:** Maven
- **Deployment:** Docker (optional: Kubernetes / AWS / GCP)
- **API Documentation:** Swagger / OpenAPI (Planned)

---

## ⚙️ System Design Overview

### 🧩 Core Components:
- **Load Balancer:** NGINX / AWS ELB
- **Application Layer:** Spring Boot microservice instances
- **Zookeeper:** Manages distributed counters for unique short URL generation
- **MySQL Database:** Stores URL mappings and click analytics
- **Cache Layer:** Redis or Memcached to reduce DB load on frequent lookups

---

### 🔄 Data Flow:
1. Client submits a URL shortening request via REST API.
2. App server generates a unique short URL using Base62 encoding + distributed counter (Zookeeper).
3. Mapping is persisted in MySQL and cached in Redis/Memcached.
4. On short URL hit, app redirects to the long URL and logs analytics (timestamp, click count).
5. Cached mappings serve high-traffic URLs for faster redirects.

---

## 🚀 Getting Started

### Prerequisites:
- Java 17+
- Maven
- Docker (for containerization)
- MySQL running locally or on docker
- (Optional) Redis, Zookeeper

## 🐳 Docker Support (Coming Soon)
- Dockerfile
- Docker Compose (MySQL + Redis + Zookeeper + App Service)
- Jenkins CI/CD pipeline (in progress)

---
