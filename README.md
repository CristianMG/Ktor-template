
<h1 align='center'>Definitive Ktor Example</h1>

> REST API developed with Kotlin.

## What's included?

- [x] Koin Injection dependencies
- [x] SQL Exposed
- [x] JWT
- [x] Clean arquitecture
- [x] Swagger-UI
- [x] Dot-Env
- [x] Faker
- [x] Valiktor(Request validations)
- [x] Hikary connection(JDBC Handler)
- [x] Gradle Kts.
- [x] KtLint
- [x] PostgresSql
- [x] BCrypt
- [x] Scripts for seeding projecta
- [x] Dockerized project
- [x] Continuous integration(Github actions and link with docker), code coverage and deploy.
- [x] Some storage(Minio/ Firebase, other alternative)
- [x] Ktlint format
- [x]  Mailing by MailGun
- [x] Crud user
- [x] Confirm mail
- [ ] Roles Management
- [ ] Pm2 for production
- [ ] Renovate bot
- [ ] Migrations



**Test stack**
- [x] Faker
- [x] Kotest
- [x] Kotest assertion
- [x] Koin test injection

## Environment variables

```dotenv
ENVIRONMENT=DEV  
# Server instance  
PORT_LISTEN=3000  
IP_LISTEN=0.0.0.0  
# JWT params  
JWT_AUDIENCE=general  
JWT_REALM=realm  
JWT_SECRET=berlin-truth-scandal-glow-mobilize-reappoint  
JWT_EXPIRATION_TIME=600000  
REFRESH_TOKEN_EXPIRATION_TIME=21600000  
# Swagger instance  
SWAGGER_ENDPOINT=docs  
SWAGGER_URL=http://localhost:3000  
# Databse instance  
POSTGRES_URL=jdbc:postgresql://database:5432/ktor_database  
POSTGRES_DB=ktor_database  
POSTGRES_USERNAME=root  
POSTGRES_PASSWORD=root  
  
# Docker settings  
USER_UUID_DEV=1000:1000  
  
#Minio settings  
MINIO_ROOT_USER=minio_user  
MINIO_ROOT_PASSWORD=berlin-truth-scandal-glow-mobilize-reappoint  
MINIO_URL=http://s3:9000  
  
# Mail settings  
MAIL_GUN_API=Your api key  
EMAIL_API= Your email  
  
# Validations settings email and password how many time are actives  
TEMP_AUTH_EXPIRE_TIME=600000
```

## Get started

Clone project, change the .env variables

Rename `example`  with your project name.

### Development

Create `.env` file in the root of the project.

```shell
$ make development
```

## Seeds

To seed the database you can use one of the following commands depending on the environment. Keep in mind that the database will be truncated before the seed is run.

⚠️ For `development` and `staging` environments `db` service must be up.

```shell
$ make development-seed
$ make staging-seed
```

## Tests

- **make test**, this command up services and execute testing

⚠️ To run e2e tests, docker services `db` and `minio` must be up.





<br><br><br><br>

> **If you think my work is useful to you, you can buy me a cofee.**

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/cristianmed)


