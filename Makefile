drop-schema:
	@echo "Dropping schema and recreating docker containers"
	docker-compose -f docker-compose.yml -f docker-compose.development.yml --env-file .env down -v

develop:
	docker-compose -f docker-compose.yml -f docker-compose.development.yml --env-file .env down # Remove any existing containers
	docker-compose -f docker-compose.yml -f docker-compose.development.yml --env-file .env up

development-seed:
	@echo "Seeding development database"
	docker exec -it ktor_server ./gradlew backend:run -Dexec.mainClass=com.example.data.seed.SeedManagerKt

test-environment:
	docker-compose -f docker-compose.yml --env-file .env down -v
	docker-compose -f docker-compose.yml --env-file .env up


test:
	docker network create backend
	docker-compose -f docker-compose.yml --env-file .env down -v
	docker-compose -f docker-compose.yml --env-file .env up -d
	export POSTGRES_URL=jdbc:postgresql://localhost:5432/ktor_database && \
	export MINIO_URL=http://localhost:9000 && \
	./gradlew backend:test
