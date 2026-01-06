# Bajaj Trading SDK – Spring Boot (Mock Trading Platform)

## Overview
This project is a **simplified trading backend SDK** built using **Java Spring Boot**, inspired by real-world stock broking platforms like Bajaj Broking.  
It simulates essential trading workflows such as order placement, trade execution, portfolio management, and instrument discovery.

The goal of this project is to demonstrate:
- REST API design
- Backend system architecture
- Trading domain understanding
- Clean code, validation, and error handling

---

## Features
- View tradable financial instruments
- Place BUY / SELL orders
- Support for MARKET and LIMIT orders
- Order lifecycle handling (NEW, PLACED, EXECUTED, CANCELLED)
- Immediate execution for MARKET orders (simulated)
- View executed trades
- Fetch portfolio holdings
- Portfolio value calculation (currentValue)
- Basic funds management
- Mock authentication (single user)
- In-memory database (H2)

---

## Tech Stack
- Java 17
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Validation
- H2 Database (In-Memory)
- Maven
- Postman (for testing)

---

## Prerequisites
- Java 17 or above
- Maven
- Git
- Postman

---

## Setup & Run Instructions

### 1. Clone Repository
```bash
git clone https://github.com/<your-username>/bajaj-trading-sdk.git
cd bajaj-trading-sdk
2. Build Project
mvn clean package

3. Run Application
mvn spring-boot:run


Application will run at:

http://localhost:8080
Mock Authentication

Authentication is mocked using a request header.

Add this header to every API request:

X-USER-ID: user-1
API Endpoints
1. Instruments API
GET /api/v1/instruments


Returns:

symbol

exchange

instrumentType

lastTradedPrice
2. Order Management APIs
Place Order
POST /api/v1/orders


MARKET Order Example

{
  "symbol": "TCS",
  "side": "BUY",
  "style": "MARKET",
  "quantity": 2
}
LIMIT Order Example

{
  "symbol": "TCS",
  "side": "BUY",
  "style": "LIMIT",
  "quantity": 1,
  "price": 3100
}
Validations

Quantity must be greater than 0

Price is mandatory for LIMIT orders

Get Order Status
GET /api/v1/orders/{orderId}


Order States:

NEW

PLACED

EXECUTED

CANCELLED
Cancel Order
DELETE /api/v1/orders/{orderId}


Only non-executed orders can be cancelled.
3. Trade APIs
GET /api/v1/trades


Returns executed trades for the user.

4. Portfolio APIs
GET /api/v1/portfolio


Portfolio Fields:

symbol

quantity

averagePrice

currentValue
5. Funds API
GET /api/v1/funds

Sample curl Commands
Get Instruments
curl -H "X-USER-ID: user-1" http://localhost:8080/api/v1/instruments

Place MARKET Order
curl -X POST -H "X-USER-ID: user-1" -H "Content-Type: application/json" \
-d '{"symbol":"TCS","side":"BUY","style":"MARKET","quantity":2}' \
http://localhost:8080/api/v1/orders
Get Portfolio
curl -H "X-USER-ID: user-1" http://localhost:8080/api/v1/portfolio

| Test Case              | API                 | Result |
| ---------------------- | ------------------- | ------ |
| Fetch instruments      | GET /instruments    | PASS   |
| Place MARKET order     | POST /orders        | PASS   |
| Place LIMIT order      | POST /orders        | PASS   |
| Get order status       | GET /orders/{id}    | PASS   |
| Cancel order           | DELETE /orders/{id} | PASS   |
| View trades            | GET /trades         | PASS   |
| View portfolio         | GET /portfolio      | PASS   |
| Quantity validation    | POST /orders        | PASS   |
| LIMIT price validation | POST /orders        | PASS   |

Error Handling

400 – Bad Request (validation errors)

401 – Unauthorized (missing user header)

404 – Resource not found

500 – Internal server error

All responses are returned in JSON format.
Assumptions

Single mocked user (user-1)

No real stock exchange connectivity

MARKET orders execute immediately at last traded price

LIMIT orders are stored without full matching engine

In-memory DB resets on restart
| Requirement           | Status |
| --------------------- | ------ |
| View instruments      | ✅      |
| Place buy/sell orders | ✅      |
| Order status          | ✅      |
| Trade book            | ✅      |
| Portfolio holdings    | ✅      |
| RESTful APIs          | ✅      |
| In-memory storage     | ✅      |
| Mock authentication   | ✅      |
Bonus Features

Centralized exception handling

Clean layered architecture

Order execution simulation

Validation using Spring annotations

Author

Amartya Mazumder
B.Tech CSE – 2026 Batch
