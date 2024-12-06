# Market Auction API - README

## Introduction
Welcome to the Market Auction API.

## Getting Started
To get started, clone this repository and follow the instructions below.

### Prerequisites
- Java (version 11 or above)
- Spring Boot
- Maven (for dependency management)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/lucascampolina/market-auction-api.git
   ```
2. Navigate to the project directory:
   ```bash
   cd market-auction-api
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### API Endpoints
- **/api/equipment** (GET): Retrieve all current equipments.
- **/api/equipment/{modelId}/{year}** (POST): Retrieve all current equipments by model and year.

## Usage
Once the application is running, you can interact with the API using tools like Postman or cURL.