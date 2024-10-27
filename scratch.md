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