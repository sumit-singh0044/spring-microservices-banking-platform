## project summary

### tech stack

- java, spring boot
- spring cloud: eureka (service discovery), api gateway, openfeign (sync inter-service calls)
- spring security + jwt for authentication
- mysql for persistence, spring data jpa
- redis for caching
- apache kafka for async messaging
- resilience4j for rate limiting
- docker (for redis, and can be extended to run the services themselves)

### services

- eureka-server: service registry all other services register with
- api-gateway: single entry point, routes requests and validates jwt before forwarding
- auth-service: handles login and issues jwt tokens
- userinfo: manages user + address data, orchestrates registration
- userpayment: creates and stores the bank account linked to a user
- payment-service: handles money transfer between two bank accounts (in progress)
- notification-service: listens for events and notifies users
- data-service / datatest-service: supporting/learning services

### apis used

- rest apis (spring web) for all client-facing and inter-service endpoints
- feign client for synchronous service-to-service calls
- kafka for asynchronous, event-driven communication
- jwt for stateless authentication across services

### sync communication between services

userinfo talks to userpayment synchronously using an openfeign client:

- `POST /accounts` — create a bank account for a newly registered user
- `DELETE /accounts/{id}` — remove the bank account when a user is deleted

this is a request/response call, so userinfo waits for userpayment to reply before it can finish the registration request.

### async communication between services

userinfo also publishes a `UserCreatedEvent` to a kafka topic (`user`) after a user is saved. notification-service subscribes to this topic and consumes the event independently, so userinfo does not wait on notification-service to respond — it just fires the event and moves on.

### registration flow

1. user sends a register request to userinfo.
2. userinfo saves the user in its database.
3. userinfo calls userpayment (sync, via feign) to create a bank account for that user.
4. userpayment generates the account number and sends it back to userinfo.
5. userinfo returns the user's details along with the account number to the client.
6. userinfo publishes a "user created" message to kafka.
7. notification-service consumes that message (async) and sends/logs a notification to the user.

### payment-service (money transfer)

payment-service is responsible for processing a payment between two bank accounts. to do this it:

1. takes the user id and gets user details from userinfo.
2. fetches the required bank account details from userpayment for both the sender and the receiver.
3. processes the transfer in an acid-compliant way — debiting bank account a and crediting bank account b happen inside a single transaction, so either both updates succeed or neither does, keeping the balances consistent even if something fails midway.
4. 
### How to Push an Existing Project to GitHub

#### Step 1: Create a Repository on GitHub

1. Sign in to GitHub.
2. Click the **+** icon (top-right) → **New repository**.
3. Enter a repository name.
4. Choose **Public** or **Private**.
5. **Do not** initialize the repository with:
   - README
   - .gitignore
   - License
6. Click **Create repository**.

#### Step 2: Open Git Bash

Navigate to your project directory.

```bash
cd path/to/your/project
```

Example:

```bash
cd C:/Users/Sumit/Desktop/SpringBootProject
```

#### Step 3: Initialize Git

```bash
git init
```

#### Step 4: Check the Repository Status

```bash
git status
```

#### Step 5: Add All Files

```bash
git add .
```

#### Step 6: Commit the Changes

```bash
git commit -m "Initial commit"
```

#### Step 7: Rename the Branch to `main`

```bash
git branch -M main
```

#### Step 8: Add the Remote Repository

Replace `<repository-url>` with your GitHub repository URL.

```bash
git remote add origin <repository-url>
```

Example:

```bash
git remote add origin https://github.com/your-username/your-repository.git
```

#### Step 9: Verify the Remote

```bash
git remote -v
```

#### Step 10: Push the Project to GitHub

```bash
git push -u origin main
```

After the first push, future pushes only require:

```bash
git push
```

Future pulls only require:

```bash
git pull
```



# Redis

Redis is an **in-memory key-value data store**.

It is commonly used for:

* Caching
* Rate limiting
* Session storage
* Counters
* Temporary data
* Distributed locking

## Running Redis with Docker

Make sure Docker Desktop is running.

Start Redis:

```bash
docker run --name redis -p 6379:6379 -d redis
```

Check if Redis is running:

```bash
docker ps
```

Redis will be available on:

```text
localhost:6379
```

Open Redis CLI:

```bash
docker exec -it redis redis-cli
```
## Basic Commands

### SET

Store data:

```text
SET name Sumit
```

### GET

Retrieve data:

```text
GET name
```

Output:

```text
"Sumit"
```

### DEL

Delete data:

```text
DEL name
```

### KEYS

See stored keys:

```text
KEYS *
```

> **Note:** `KEYS *` is useful for learning, but should be avoided in production with large datasets.

## Expiration

### EXPIRE

Set expiration time in seconds:

```text
SET name Sumit
EXPIRE name 30
```

The key will automatically be deleted after 30 seconds.

You can also set expiration while creating the key:

```text
SET name Sumit EX 30
```

### TTL

Check the remaining expiration time:

```text
TTL name
```

Example:

```text
(integer) 25
```

This means approximately **25 seconds** are remaining.

## Counters

### INCR

Increase a number by 1:

```text
SET counter 10
INCR counter
```

Output:

```text
(integer) 11
```

If the key doesn't exist, Redis creates it:

```text
INCR counter
```

Result:

```text
(integer) 1
```

### DECR

Decrease a number by 1:

```text
DECR counter
```

## Basic Commands to Remember

| Command  | Purpose                    |
| -------- | -------------------------- |
| `SET`    | Store data                 |
| `GET`    | Retrieve data              |
| `DEL`    | Delete data                |
| `KEYS *` | View keys                  |
| `EXPIRE` | Set expiration             |
| `TTL`    | Check remaining expiration |
| `INCR`   | Increase a number          |
| `DECR`   | Decrease a number          |

