drop-schema:
	@echo "Dropping schema and recreating docker containers"
	docker-compose down -v
	docker-compose up -d

staging:
	@echo "Updating the staging environment"
	docker-compose down -v --force-recreate  # Remove any existing containers
	docker-compose up --force-recreate # Start the containers

develop:
	docker-compose -f docker-compose.yml -f docker-compose.development.yml down # Remove any existing containers
	docker-compose -f docker-compose.yml -f docker-compose.development.yml up

seed-develop:
	@echo "Seeding development database"
	docker exec -it ktor_server ./gradlew backend:run -Dexec.mainClass=com.example.data.seed.SeedManagerKt

test:
	@echo "Executin test suite"
	docker exec -it ktor_server ./gradlew test