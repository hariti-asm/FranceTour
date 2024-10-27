
# 1. Create a Competition (POST)
curl -X POST "http://localhost:8080/FranceTour/api/v1/competitions" \
-H "Content-Type: application/json" \
-d '{
"name": "Tour de France 2024",
"startDate": "2024-06-29",
"endDate": "2024-07-21",
"location": "France"
}'

# 2. Get All Competitions (GET)
curl "http://localhost:8080/FranceTour/api/v1/competitions?page=0&size=10"

# 3. Get Competition by ID (GET)
curl "http://localhost:8080/FranceTour/api/v1/competitions/1"

# 4. Get Competition by Name (GET)
curl "http://localhost:8080/FranceTour/api/v1/competitions/name/Tour%20de%20France%202024"

# 5. Update Competition (PUT)
curl -X PUT "http://localhost:8080/FranceTour/api/v1/competitions/1" \
-H "Content-Type: application/json" \
-d '{
"name": "Tour de France 2024 Updated",
"startDate": "2024-06-30",
"endDate": "2024-07-22",
"location": "France"
}'

# 6. Delete Competition (DELETE)
curl -X DELETE "http://localhost:8080/FranceTour/api/v1/competitions/1"



# Stage API Test
# Stage API Test

# 1. Create a Stage (POST /FranceTour/api/v1/stages)
curl -X POST 'http://localhost:8080/FranceTour/api/v1/stages' \
-H 'Content-Type: application/json' \
-d '{
"name": "Alps Mountain Stage",
"stageNumber": 1,
"startLocation": "Annecy",
"endLocation": "Le Grand-Bornand",
"distance": 150.8,
"date": "2024-07-01",
"competitionId": 1
}'

# 2. Create another Stage
curl -X POST 'http://localhost:8080/FranceTour/api/v1/stages' \
-H 'Content-Type: application/json' \
-d '{
"name": "Paris Time Trial",
"stageNumber": 2,
"startLocation": "Versailles",
"endLocation": "Paris",
"distance": 42.5,
"date": "2024-07-03",
"competitionId": 1
}'

# 3. Get All Stages with Pagination (GET /FranceTour/api/v1/stages)
curl -X GET 'http://localhost:8080/FranceTour/api/v1/stages?page=0&size=10'

# 4. Get Stage by ID (GET /FranceTour/api/v1/stages/{id})
curl -X GET 'http://localhost:8080/FranceTour/api/v1/stages/1'

# 5. Update Stage (PUT /FranceTour/api/v1/stages/{id})
curl -X PUT 'http://localhost:8080/FranceTour/api/v1/stages/1' \
-H 'Content-Type: application/json' \
-d '{
"name": "Updated Alps Mountain Stage",
"stageNumber": 1,
"startLocation": "Annecy",
"endLocation": "Morzine",
"distance": 155.2,
"date": "2024-07-01",
"competitionId": 1
}'

# 6. Delete Stage (DELETE /FranceTour/api/v1/stages/{id})
curl -X DELETE 'http://localhost:8080/FranceTour/api/v1/stages/2'


# CYCLISTS AND TEAMS API ENDPOINTS
# 1. Get all cyclists (paginated)
# Default page (0) and size (10)
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists'

# With specific page and size
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists?page=0&size=5'

# 2. Get cyclist by ID
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists/1'

# 3. Get cyclist by name
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists/name/John%20Doe'

# 4. Create new cyclist
curl -X POST 'http://localhost:8080/FranceTour/api/v1/cyclists' \
-H 'Content-Type: application/json' \
-d '{
"name": "John Doe",
"nationality": "French",
"number": 42,
"teamId": 1
}'

# 5. Update existing cyclist
curl -X PUT 'http://localhost:8080/FranceTour/api/v1/cyclists/1' \
-H 'Content-Type: application/json' \
-d '{
"name": "John Doe Updated",
"nationality": "Belgian",
"number": 43,
"teamId": 1
}'

# 6. Delete cyclist
curl -X DELETE 'http://localhost:8080/FranceTour/api/v1/cyclists/1'

# Complete test sequence with different scenarios:

# 1. Create a new team first (assuming team endpoint exists)
curl -X POST 'http://localhost:8080/FranceTour/api/v1/teams' \
-H 'Content-Type: application/json' \
-d '{
"name": "Team Sky"
}'

# 2. Create a new cyclist in that team
curl -X POST 'http://localhost:8080/FranceTour/api/v1/cyclists' \
-H 'Content-Type: application/json' \
-d '{
"name": "Chris Froome",
"nationality": "British",
"number": 1,
"teamId": 1
}'

# 3. Create another cyclist
curl -X POST 'http://localhost:8080/FranceTour/api/v1/cyclists' \
-H 'Content-Type: application/json' \
-d '{
"name": "Geraint Thomas",
"nationality": "British",
"number": 2,
"teamId": 1
}'

# 4. Get all cyclists (should see both)
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists'

# 5. Update first cyclist
curl -X PUT 'http://localhost:8080/FranceTour/api/v1/cyclists/1' \
-H 'Content-Type: application/json' \
-d '{
"name": "Chris Froome",
"nationality": "British",
"number": 3,
"teamId": 1
}'

# 6. Get cyclist by name
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists/name/Chris%20Froome'

# 7. Get cyclist by ID
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists/1'

# 8. Delete cyclist
curl -X DELETE 'http://localhost:8080/FranceTour/api/v1/cyclists/1'

# Error scenario tests:

# 1. Try to get non-existent cyclist
curl -X GET 'http://localhost:8080/FranceTour/api/v1/cyclists/999'

# 2. Try to create cyclist with invalid data
curl -X POST 'http://localhost:8080/FranceTour/api/v1/cyclists' \
-H 'Content-Type: application/json' \
-d '{
"name": "",
"nationality": "",
"number": -1,
"teamId": null
}'

# 3. Try to update non-existent cyclist
curl -X PUT 'http://localhost:8080/FranceTour/api/v1/cyclists/999' \
-H 'Content-Type: application/json' \
-d '{
"name": "Non Existent",
"nationality": "Unknown",
"number": 999,
"teamId": 1
}'

# 4. Try to delete non-existent cyclist
curl -X DELETE 'http://localhost:8080/FranceTour/api/v1/cyclists/999'

# Windows CMD version (if using Windows):
# Note: Use double quotes instead of single quotes and escape inner quotes

# Create cyclist (Windows CMD)
curl -X POST "http://localhost:8080/FranceTour/api/v1/cyclists" ^
-H "Content-Type: application/json" ^
-d "{\"name\": \"John Doe\", \"nationality\": \"French\", \"number\": 42, \"teamId\": 1}"


