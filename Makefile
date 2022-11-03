drop-schema:
	@echo "Dropping schema and recreating docker containers"
	docker-compose down -v
	docker-compose up -d

develop: develop
	@echo "Updating the development environment"
	docker-compose down -v  # Remove any existing containers
	docker-compose up -d # Start the containers
	./gradlew run  # Run the application

development-seed:
	@echo "Seeding development database"
	./gradlew backend:run -Dexec.mainClass=com.example.data.seed.SeedTestKt