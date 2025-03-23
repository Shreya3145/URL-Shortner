# 📌 URL Shortener Service
🚧 Still in progress!

A scalable and distributed URL shortening service built with **Spring Boot**. This project converts long URLs into shortened links while maintaining analytics and high availability.

## 🚀 Features

- 🔗 Generate short URLs for long URLs
- 🔄 Redirect short URLs to the original long URL
- 📊 Track click analytics (timestamps, count)
- ⚡ High-performance design with caching layer (Redis/Memcached)
- ☁️ Distributed system design (Zookeeper coordination)
- 🔐 User management (optional - registration, login)
- 🛠️ REST APIs for easy integration

---

## 🏗️ Tech Stack

- **Backend:** Spring Boot, Java 17+
- **Database:** MySQL
- **Cache:** Redis or Memcached
- **Coordination:** Zookeeper (for distributed counters)
- **Build Tool:** Maven / Gradle
- **Deployment:** Docker + (Optional) Kubernetes / AWS / GCP

---

## ⚙️ System Design Overview

### 🧩 Components:
- **Load Balancer:** NGINX / AWS ELB
- **App Servers:** Spring Boot instances
- **Zookeeper:** Manages distributed counters for unique short URL generation
- **NoSQL DB:** Stores URL mappings + analytics
- **Cache:** Stores hot URL mappings to reduce DB load

### 🔄 Data Flow:
1. Client sends URL shortening request.
2. App server generates a unique short URL using Base62 encoding and counter.
3. Mapping is saved to DB and cached.
4. Redirection and analytics captured on short URL hit.

---
