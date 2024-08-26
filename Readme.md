Resume of End points

## ClientController Endpoints

| Method | Endpoint               | Description                     |
|--------|-------------------------|---------------------------------|
| POST   | `/api/clientes`          | Creates a new client            |
| DELETE | `/api/clientes/{id}`     | Deletes a client by `id`        |
| PUT    | `/api/clientes/{id}`     | Updates a client by `id`        |
| GET    | `/api/clientes`          | Retrieves all clients           |
| GET    | `/api/clientes/{id}`     | Retrieves a client by `id`      |


## CuentaController Endpoints

| Method | Endpoint                       | Description                               |
|--------|---------------------------------|-------------------------------------------|
| POST   | `/api/cuentas`                  | Creates a new account                     |
| GET    | `/api/cuentas/{id}`             | Retrieves an account by `id`              |
| GET    | `/api/cuentas`                  | Retrieves all accounts                    |
| GET    | `/api/cuentas/client/{clientId}`| Retrieves accounts by `clientId`          |
| PUT    | `/api/cuentas/{id}`             | Updates an account by `id`                |
| DELETE | `/api/cuentas/{id}`             | Deletes an account by `id`                |


## MovimientoController Endpoints

| Method | Endpoint               | Description                                  |
|--------|------------------------|----------------------------------------------|
| POST   | `/api/movimientos`      | Creates a new transaction (movimiento)       |
| GET    | `/api/movimientos`      | Retrieves all transactions (movimientos)     |
| GET    | `/api/movimientos/{id}` | Retrieves a transaction by `id`              |
| DELETE | `/api/movimientos/{id}` | Deletes a transaction by `id`                |


## Important Notes:

- **Java 17**: This project uses Java 17. Ensure that your environment is set up with Java 17 to run the application correctly.
- **Database**: Ensure the database setup is complete before using these APIs.

### API Documentation - ClientController

Base URL: /api/clientes
Prerequisite

Ensure that the database named banco_development_db is created and configured before running this microservice. This microservice interacts with the clientes table in the database.
POST /api/clientes

Description: Creates a new client.

    Request Body:
        ClientDto: The client information to be created.
        Fields:
            name (String): The name of the client. (required)
            email (String): The email of the client. Must be unique. (required)

Example Request:

json

{
  "name": "John Doe",
  "email": "johndoe@example.com"
}

Response:

    Returns the created ClientDto object with the generated id.

Example Response:

json

{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}

DELETE /api/clientes/{id}

Description: Deletes a client by its id.

    Path Parameter:
        id (Long): The ID of the client to be deleted.

Response:

    No content (status 204).

PUT /api/clientes/{id}

Description: Updates an existing client by its id.

    Path Parameter:
        id (Long): The ID of the client to be updated.

    Request Body:
        ClientDto: The client information to update.
        Fields:
            name (String): The updated name of the client. (optional)
            email (String): The updated email of the client. (optional)

Example Request:

json

{
  "name": "Jane Doe",
  "email": "janedoe@example.com"
}

Response:

    Returns the updated ClientDto object.

Example Response:

json

{
  "id": 1,
  "name": "Jane Doe",
  "email": "janedoe@example.com"
}

GET /api/clientes

Description: Retrieves a list of all clients.

Response:

    Returns a list of Cliente objects.

Example Response:

json

[
  {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "janesmith@example.com"
  }
]

GET /api/clientes/{id}

Description: Retrieves a specific client by its id.

    Path Parameter:
        id (Long): The ID of the client to retrieve.

Response:

    Returns the Cliente object with the specified id.

Example Response:

json

{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}


### API Documentation - CuentaController

Base URL: /api/cuentas
Endpoints
POST /api/cuentas

Description: Creates a new account (cuenta).

    Request Body:
        CuentaDto: The account information to be created.
        Fields:
            clientId (Long): The ID of the client to whom the account belongs. (required)
            accountNumber (String): The account number. Must be unique. (required)
            balance (Double): The initial balance of the account. (required)

Example Request:

json

{
  "clientId": 1,
  "accountNumber": "123456789",
  "balance": 500.00
}

Response:

    Returns the created Cuenta object.

Example Response:

json

{
  "id": 1,
  "clientId": 1,
  "accountNumber": "123456789",
  "balance": 500.00
}

Error Response:

    If there is an error, returns an error message with 400 Bad Request.

GET /api/cuentas/{id}

Description: Retrieves an account (cuenta) by its id.

    Path Parameter:
        id (Long): The ID of the account to retrieve.

Response:

    Returns the Cuenta object with the specified id.

Example Response:

json

{
  "id": 1,
  "clientId": 1,
  "accountNumber": "123456789",
  "balance": 500.00
}

GET /api/cuentas

Description: Retrieves a list of all accounts (cuentas).

Response:

    Returns a list of Cuenta objects.

Example Response:

json

[
  {
    "id": 1,
    "clientId": 1,
    "accountNumber": "123456789",
    "balance": 500.00
  },
  {
    "id": 2,
    "clientId": 2,
    "accountNumber": "987654321",
    "balance": 300.00
  }
]

GET /api/cuentas/client/{clientId}

Description: Retrieves a list of accounts (cuentas) by the clientId.

    Path Parameter:
        clientId (Long): The ID of the client whose accounts you want to retrieve.

Response:

    Returns a list of Cuenta objects associated with the specified clientId.

Example Response:

json

[
  {
    "id": 1,
    "clientId": 1,
    "accountNumber": "123456789",
    "balance": 500.00
  }
]

PUT /api/cuentas/{id}

Description: Updates an existing account (cuenta) by its id.

    Path Parameter:
        id (Long): The ID of the account to be updated.

    Request Body:
        Cuenta: The account information to update. Fields:
            accountNumber (String): The updated account number. (optional)
            balance (Double): The updated balance of the account. (optional)

Example Request:

json

{
  "accountNumber": "123456789",
  "balance": 600.00
}

Response:

    Returns the updated Cuenta object.

Example Response:

json

{
  "id": 1,
  "clientId": 1,
  "accountNumber": "123456789",
  "balance": 600.00
}

Error Response:

    If the account is not found, returns an error message with 404 Not Found.

DELETE /api/cuentas/{id}

Description: Deletes an account (cuenta) by its id.

    Path Parameter:
        id (Long): The ID of the account to be deleted.

Response:

    No content (status 204 No Content).

Error Response:

    If the account is not found, returns an error message with 404 Not Found.

### API Documentation - MovimientoController

Base URL: /api/movimientos
Endpoints
POST /api/movimientos

Description: Creates a new transaction (movimiento) for an account.

    Request Body:
        MovimientoDto: The transaction details.
        Fields:
            accountNumber (String): The account number associated with the transaction. (required)
            amount (Double): The amount of the transaction. Negative values represent debits, positive values represent credits. (required)

Example Request:

json

{
  "accountNumber": "123456789",
  "amount": -100.00
}

Response:

    Returns the created Movimiento object.

Example Response:

json

{
  "id": 1,
  "accountNumber": "123456789",
  "amount": -100.00,
  "type": "DEBITO",
  "date": "2024-08-26T00:00:00Z"
}

Error Response:

    If the account has insufficient funds for a debit, returns an error message with 400 Bad Request and the message "Saldo no disponible".

GET /api/movimientos

Description: Retrieves a list of all transactions (movimientos).

Response:

    Returns a list of Movimiento objects.

Example Response:

json

[
  {
    "id": 1,
    "accountNumber": "123456789",
    "amount": -100.00,
    "type": "DEBITO",
    "date": "2024-08-26T00:00:00Z"
  },
  {
    "id": 2,
    "accountNumber": "123456789",
    "amount": 50.00,
    "type": "CREDITO",
    "date": "2024-08-26T01:00:00Z"
  }
]

GET /api/movimientos/{id}

Description: Retrieves a specific transaction (movimiento) by its id.

    Path Parameter:
        id (Long): The ID of the transaction to retrieve.

Response:

    Returns the Movimiento object with the specified id.

Example Response:

json

{
  "id": 1,
  "accountNumber": "123456789",
  "amount": -100.00,
  "type": "DEBITO",
  "date": "2024-08-26T00:00:00Z"
}

DELETE /api/movimientos/{id}

Description: Deletes a transaction (movimiento) by its id.

    Path Parameter:
        id (Long): The ID of the transaction to be deleted.

Response:

    No content (status 204 No Content).


