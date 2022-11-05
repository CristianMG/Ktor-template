drop-schema:
	@echo "Dropping schema and recreating docker containers"
	docker-compose down -v
	docker-compose up -d

develop:
	@echo "Updating the development environment"
	docker-compose down -v  # Remove any existing containers
	docker-compose up # Start the containers
development-seed:
	@echo "Seeding development database"
	./gradlew backend:run -Dexec.mainClass=com.example.data.seed.SeedTestKt