
## **Kotlin backend Template.**

It is a template for a Kotlin backend project. It is based on the [ktor](https://ktor.io/) framework and uses [Exposed]  
This template I created for myself and my personal projects, but I decided to share it with you. I hope it will be useful to you.


## **Deploy server.**

We must install first Docker and Docker compose.

- First we have to define .env file.
- Execute make develop

This have to deploy server and swagger documentation with the information defined in the .env

**Example environment file.**

> ENVIRONMENT=DEV >         
> PORT_LISTEN=3000 > IP_LISTEN=0.0.0.0 >         
> JWT_AUDIENCE=general > JWT_REALM=realm > JWT_SECRET=berlin-truth-scandal-glow-mobilize-reappoint > SWAGGER_ENDPOINT=docs > SWAGGER_URL=http://0.0.0.0:3000 >         
> JWT_EXPIRATION_TIME=600000 > POSTGRES_URL=jdbc:postgresql://database:5432/ktor_database > POSTGRES_USERNAME=root > POSTGRES_PASSWORD=root >         
> REFRESH_TOKEN_EXPIRATION_TIME=21600000 >     USER_UUID_DEV=1000:1000

## **Execute test.**

- **make environment-test**, this command execute environment test

Execute with android studio test you want to execute.

You have to define a environment var to point database url connection to localhost, due to container is not accesible from host machine by a name.

![Example of how to configure a run test](https://github.com/CristianMG/Ktor-template/blob/develop/screenshoots/img.png?raw=true)

What did taken into account to choose this tech stack?
1) Stability of library.
2) Library must be preferably DSL or DSL friendly.
3) Compatible with Kotlin Native if possible.
4) Open software.
5) Easy to use.

## Tech stack
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
- [ ] Continuous integration(Github actions and link with docker), code coverage and deploy.
- [ ] Mailing
- [ ] Some storage(Minio/ Firebase, other alternative)
- [ ] Staging environment
- [ ] Modularized.
- [ ] Production environment, with PM2, only documentation and example of how to deploy in production.


**Test stack**
- [x] Faker
- [x] Kotest
- [x] Kotest assertion
- [x] Koin test injection
- [ ] Mockk



## Deployment

We have three environments to make our backend.

**Develop**, commonly used in our local network to deploy and test code.

**Staging**, used as development environment to have a stable environment to deploy changes.

**Production**. Used as production environment, this environment wil receive only the stables updates tested at staging.

Commands to deploy.

**Develop.** Use command make develop. Docker, this environment have activated refresh on the way.

**Staging.** Use command make staging. Docker, We have to recreate containers every time to deploy changes.

**Production.** Use command make production. PM2








> **If you think my work is useful to you, you can buy me a cofee.**

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/cristianmed)