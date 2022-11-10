drop-schema:
	@echo "Dropping schema and recreating docker containers"
	docker-compose down -v
	docker-compose up -d

develop:
	docker-compose -f docker-compose.yml -f docker-compose.development.yml down # Remove any existing containers
	docker-compose -f docker-compose.yml -f docker-compose.development.yml up

seed-develop:
	@echo "Seeding development database"
	docker exec -it ktor_server ./gradlew backend:run -Dexec.mainClass=com.example.data.seed.SeedManagerKt

test: test-environment
	./gradlew backend:test

test-environment:
	EXPORT DATABASE_URL=postgres://ktor:ktor@localhost:5432/ktor
	docker-compose -f docker-compose.yml down -v
	docker-compose -f docker-compose.yml up -d
