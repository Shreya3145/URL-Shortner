Database Structure - represented using Entity-Relationship Diagram
```
                       +------------------------------+
                       |            USER              |
                       +------------------------------+
                       | 💡  id BIGINT                |
                       | 🔹  email VARCHAR(255)       |
                       | 🔹  password VARCHAR(255)    |
                       | 🔹  role VARCHAR(255)        |
                       | 🔹  username VARCHAR(255)    |
                       +--------------+---------------+
                                      |
                                      |
                                      |
                       +--------------v---------------+
                       |         URL MAPPING          |
                       +------------------------------+
                       | 💡  id BIGINT                |
                       | 🔸  click_count INT          |
                       | 🔹  original_url VARCHAR(255)|
                       | 🔹  short_url VARCHAR(255)   |
                       | 🔹  user_id BIGINT           |
                       | 🔹  created_date DATETIME(6) |
                       +--------------+---------------+
                                      |
                                      |
                       +--------------v---------------+
                       |        CLICK EVENT           |
                       +------------------------------+
                       | 💡  id BIGINT                |
                       | 🔹  click_date DATETIME(6)   |
                       | 🔹  url_mapping_id BIGINT    |
                       +------------------------------+
```
                                                                   
