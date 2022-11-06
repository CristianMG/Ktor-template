with a lot of work done it.

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
- [x] Scripts for seeding project
- [ ] Dockerized project
- [ ] Continuous integration(Github actions and link with docker)
- [ ] Mailing
- [ ] Some storage(Minio/ Firebase, other alternative)


**Test stack**
- [ ] E2e Testing Ktor
- [ ] Faker
- [ ] Mockk
- [ ] AssertK




## Deployment

We have three environments to make our backend.

**Develop**, commonly used in our local network to deploy and test code.

**Staging**, used as development environment to have a stable environment to deploy changes.

**Production**. Used as production environment, this environment wil receive only the stables updates tested at staging.

Commands to deploy.

**Develop.**
Use command make develop. Docker, this environment have activated refresh on the way.

**Staging.**
Use command make staging. Docker, We have to recreate containers every time to deploy changes.

**Production.**
Use command make production. PM2








> **If you think my work is useful to you, you can buy me a cofee.**

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/cristianmed)
