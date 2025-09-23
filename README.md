
# Ecommerce Sports Store - Spring Boot Backend

## Table of Contents

- [Description](#description)
- [Architecture](#architecture)
- [Key Features](#key-features)
- [Getting Started](#getting-started)
	- [Prerequisites](#prerequisites)
		- [Installation & Setup](#installation-setup)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [Contact](#contact)

## Description

The Ecommerce Sports Store is a robust backend service for a modern online retail platform, built with Java and Spring Boot. It provides a complete and scalable foundation for managing products, brands, shopping carts, and orders.

This application is designed with a clean, layered architecture and exposes a comprehensive RESTful API, making it easy to integrate with any frontend client (like React, Angular, or mobile apps) or other microservices.

## Architecture

The application follows a classic three-layer architecture, ensuring a clean separation of concerns between the API, business logic, and data access layers.


## Key Features

- **Product Catalog:** Full CRUD support with advanced filtering (by brand/type), sorting, and pagination.
- **Shopping Basket:** Persistent, user-specific cart functionality (add, update, remove, view items).
- **Order Management:** Complete order lifecycle from creation to retrieval and deletion.
- **Layered Architecture:** Well-defined controller, service, and repository layers.
- **Database Integration:** Seamlessly integrated with MySQL using Spring Data JPA.
- **DTO Pattern:** Uses MapStruct for clean and efficient mapping between entities and API response models.

## Getting Started

### Prerequisites

- Java JDK 21+
- Maven 3.8+
- MySQL 8.x
- Git

### Installation & Setup

1. **Clone the repository:**
	 ```sh
	 git clone https://github.com/surajbadhe/ecommece-store-spring.git
	 cd ecommece-store-spring
	 ```

2. **Configure the database:**
	 - Create a MySQL database (e.g., `sports_store`).
	 - Update the database URL, username, and password in `src/main/resources/application.yaml` or `application.properties`.

3. **Build the project:**
	 ```sh
	 mvn clean install
	 ```

## Usage

### Run the application

- Using Maven:

```sh
	mvn spring-boot:run

```
- Or run the JAR file:

```sh
	java -jar target/ecommerce-store-spring-0.0.1-SNAPSHOT.jar

```

The server will start on port `8088` by default.

## API Endpoints

### Products

- `GET /api/products` — List products (supports pagination, filtering, sorting)
- `GET /api/products/{id}` — Get product by ID
- `GET /api/products/brands` — List all brands
- `GET /api/products/types` — List all types

### Basket

- `GET /api/basket` — Get the current user's basket
- `POST /api/basket/items` — Add/update an item in the basket
- `DELETE /api/basket/items/{productId}` — Remove an item from the basket

### Orders

- `POST /api/orders` — Create a new order from the basket
- `GET /api/orders/{orderId}` — Get an order by its ID
- `GET /api/orders/paged` — Get a paginated list of all orders

**Note:** Authentication is not yet implemented. All basket and order operations currently use a hardcoded `"anonymousUser"` identifier.

## Configuration

Key application settings are managed in `src/main/resources/application.yaml` or `application.properties`:

- **Server Port:** `server.port` (default: 8088)
- **Database:** `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`
- **JPA/Hibernate:** `spring.jpa.hibernate.ddl-auto` (e.g., update, validate)

Modify these files to suit your environment.

## Deployment

### Build a deployable JAR

```sh
mvn clean package
```

The JAR will be located in the `target/` directory.

### Deploy to server or cloud

- Copy the JAR to your server.
- Ensure Java and MySQL are installed and configured.
- Run:
	
```sh
	java -jar ecommerce-store-spring-0.0.1-SNAPSHOT.jar
```



## Contributing

Contributions are welcome! Please follow these guidelines:

- Fork the repository and create your branch.
- Write clear, concise commit messages.
- Adhere to Java and Spring Boot coding standards.
- Submit pull requests with detailed descriptions.
- For bug reports and feature requests, use GitHub Issues.

## Contact

- Maintainer: Suraj Badhe
- GitHub: [surajbadhe](https://github.com/surajbadhe)
