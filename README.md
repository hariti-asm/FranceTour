# FranceTour API Documentation

## Application Overview
The **FranceTour API** application serves as a backend service for managing cycling competitions, stages, teams, and cyclists. This application facilitates the organization and tracking of major cycling events, such as the Tour de France, by enabling efficient management of competition schedules, individual stage details, team and cyclist information, and real-time event updates.

### Key Features
- **Competition Management**: Allows for creating, updating, and retrieving information on cycling competitions
- **Stage Management**: Enables the addition and management of individual stages within a competition
- **Team and Cyclist Management**: Provides endpoints for handling team creation and assigning cyclists to teams
- **Error Handling and Validation**: Ensures data integrity by handling errors and validating requests

## Base URL
```
http://localhost:8080/FranceTour/api/v1
```

## API Endpoints

### 1. Competitions API

#### Create Competition
- **Method:** POST
- **Endpoint:** `/competitions`
- **Content-Type:** application/json

**Request:**
```bash
curl -X POST "http://localhost:8080/FranceTour/api/v1/competitions" \
-H "Content-Type: application/json" \
-d '{
    "name": "Tour de France 2024",
    "startDate": "2024-06-29",
    "endDate": "2024-07-21",
    "location": "France"
}'
```

#### Get All Competitions
- **Method:** GET
- **Endpoint:** `/competitions`
- **Query Parameters:**
    - page (default: 0)
    - size (default: 10)

**Request:**
```bash
curl "http://localhost:8080/FranceTour/api/v1/competitions?page=0&size=10"
```

#### Get Competition by ID
- **Method:** GET
- **Endpoint:** `/competitions/{id}`

**Request:**
```bash
curl "http://localhost:8080/FranceTour/api/v1/competitions/1"
```

#### Get Competition by Name
- **Method:** GET
- **Endpoint:** `/competitions/name/{name}`

**Request:**
```bash
curl "http://localhost:8080/FranceTour/api/v1/competitions/name/Tour%20de%20France%202024"
```

#### Update Competition
- **Method:** PUT
- **Endpoint:** `/competitions/{id}`
- **Content-Type:** application/json

**Request:**
```bash
curl -X PUT "http://localhost:8080/FranceTour/api/v1/competitions/1" \
-H "Content-Type: application/json" \
-d '{
    "name": "Tour de France 2024 Updated",
    "startDate": "2024-06-30",
    "endDate": "2024-07-22",
    "location": "France"
}'
```

#### Delete Competition
- **Method:** DELETE
- **Endpoint:** `/competitions/{id}`

**Request:**
```bash
curl -X DELETE "http://localhost:8080/FranceTour/api/v1/competitions/1"
```

### 2. Stages API

#### Create Stage
- **Method:** POST
- **Endpoint:** `/stages`
- **Content-Type:** application/json

**Request:**
```bash
curl -X POST "http://localhost:8080/FranceTour/api/v1/stages" \
-H "Content-Type: application/json" \
-d '{
    "name": "Alps Mountain Stage",
    "stageNumber": 1,
    "startLocation": "Annecy",
    "endLocation": "Le Grand-Bornand",
    "distance": 150.8,
    "date": "2024-07-01",
    "competitionId": 1
}'
```

#### Get All Stages
- **Method:** GET
- **Endpoint:** `/stages`
- **Query Parameters:**
    - page (default: 0)
    - size (default: 10)

**Request:**
```bash
curl -X GET "http://localhost:8080/FranceTour/api/v1/stages?page=0&size=10"
```

#### Get Stage by ID
- **Method:** GET
- **Endpoint:** `/stages/{id}`

**Request:**
```bash
curl -X GET "http://localhost:8080/FranceTour/api/v1/stages/1"
```

#### Update Stage
- **Method:** PUT
- **Endpoint:** `/stages/{id}`
- **Content-Type:** application/json

**Request:**
```bash
curl -X PUT "http://localhost:8080/FranceTour/api/v1/stages/1" \
-H "Content-Type: application/json" \
-d '{
    "name": "Updated Alps Mountain Stage",
    "stageNumber": 1,
    "startLocation": "Annecy",
    "endLocation": "Morzine",
    "distance": 155.2,
    "date": "2024-07-01",
    "competitionId": 1
}'
```

#### Delete Stage
- **Method:** DELETE
- **Endpoint:** `/stages/{id}`

**Request:**
```bash
curl -X DELETE "http://localhost:8080/FranceTour/api/v1/stages/2"
```

### 3. Cyclists API

#### Create Cyclist
- **Method:** POST
- **Endpoint:** `/cyclists`
- **Content-Type:** application/json

**Request:**
```bash
curl -X POST "http://localhost:8080/FranceTour/api/v1/cyclists" \
-H "Content-Type: application/json" \
-d '{
    "name": "John Doe",
    "nationality": "French",
    "number": 42,
    "teamId": 1
}'
```

#### Get All Cyclists
- **Method:** GET
- **Endpoint:** `/cyclists`
- **Query Parameters:**
    - page (default: 0)
    - size (default: 10)

**Request:**
```bash
curl -X GET "http://localhost:8080/FranceTour/api/v1/cyclists"
# With pagination:
curl -X GET "http://localhost:8080/FranceTour/api/v1/cyclists?page=0&size=5"
```

#### Get Cyclist by ID
- **Method:** GET
- **Endpoint:** `/cyclists/{id}`

**Request:**
```bash
curl -X GET "http://localhost:8080/FranceTour/api/v1/cyclists/1"
```

#### Get Cyclist by Name
- **Method:** GET
- **Endpoint:** `/cyclists/name/{name}`

**Request:**
```bash
curl -X GET "http://localhost:8080/FranceTour/api/v1/cyclists/name/John%20Doe"
```

#### Update Cyclist
- **Method:** PUT
- **Endpoint:** `/cyclists/{id}`
- **Content-Type:** application/json

**Request:**
```bash
curl -X PUT "http://localhost:8080/FranceTour/api/v1/cyclists/1" \
-H "Content-Type: application/json" \
-d '{
    "name": "John Doe Updated",
    "nationality": "Belgian",
    "number": 43,
    "teamId": 1
}'
```

#### Delete Cyclist
- **Method:** DELETE
- **Endpoint:** `/cyclists/{id}`

**Request:**
```bash
curl -X DELETE "http://localhost:8080/FranceTour/api/v1/cyclists/1"
```

### 4. Teams API

#### Create Team
- **Method:** POST
- **Endpoint:** `/teams`
- **Content-Type:** application/json

**Request:**
```bash
curl -X POST "http://localhost:8080/FranceTour/api/v1/teams" \
-H "Content-Type: application/json" \
-d '{
    "name": "Team Sky"
}'
```

## Error Handling

The API implements comprehensive error handling for various scenarios:

- Invalid data submissions
- Non-existent resource requests
- Invalid parameter values
- Missing required fields

### Example Error Scenarios

```bash
# 1. Request non-existent resource
curl -X GET "http://localhost:8080/FranceTour/api/v1/cyclists/999"

# 2. Create resource with invalid data
curl -X POST "http://localhost:8080/FranceTour/api/v1/cyclists" \
-H "Content-Type: application/json" \
-d '{
    "name": "",
    "nationality": "",
    "number": -1,
    "teamId": null
}'
```

## Platform Compatibility Note

### Windows CMD
For Windows Command Prompt users, use double quotes and escape inner quotes in curl commands:

```bash
curl -X POST "http://localhost:8080/FranceTour/api/v1/cyclists" ^
-H "Content-Type: application/json" ^
-d "{\"name\": \"John Doe\", \"nationality\": \"French\", \"number\": 42, \"teamId\": 1}"
```