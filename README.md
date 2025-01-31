# IoT Ecommerce Plateform  

## Features:  
1. User Management Service
- Handles user registration, authentication, and profile management.
- Implements security with JWT and OAuth2.
2. Product Catalog Service
- Manages product details such as names, descriptions, prices, and stock levels.
- Includes functionality for adding, updating, and removing IoT devices.
3. Order Management Service
- Processes orders, maintains order history and handles payment status.
- Integrates with external payment gateways (mocked for training).
4. Cart Service
- Manages user shopping carts with add/remove functionality.
- Uses caching for quick access.
5. Inventory Service
- Tracks stock levels for IoT devices.
- Synchronizes with the Product Catalog Service.
6. Notification Service
- Sends order confirmations and updates via email/SMS (mocked using Apache Kafka).
7. Search Service
- Implements a search feature using Elasticsearch.
- Allows users to search for products by name, category, or features.
8. Payment Service
- Handles mock payment processing.
- Demonstrates secure transactions.
9. Analytics Service
- Tracks user behavior, order trends, and system performance metrics.
- Uses Prometheus and Grafana for monitoring.

## Tech Stack
### Backend
- Spring:
  - SpringBoot, SpringSecurity, OAuth2, SpringDataJPA, SpringDataMongodb, Spring Cloud
- Database:
  - PostgreSQL, MongoDB
- MessageQueue:
  - Kafka
- Testing:
  - Swagger, JUnit
- Middleware:
  - Elasticsearch
- Analytics:
  - Prometheus, Grafana

### Frontend
React, Vite, Chakra UI



