# Calories Calculator

This repository contains the front-end and back-end code of my calories calculator application. The front end was build using React with antd and the back-end was build using Spring Boot.
In order to respect a minimum security requirements the application should use JWT from Spring Security for securing front-end and back-end communication.
The image of the application is build using Jib and uploaded to my dockerhub account. This image is used in order to deploy the application to amazon web services.
I also used Slack in order to keep track of the continuous deployment workflow.

Contents of the readme file:
- usefull links of the application
- application requirements
- database design
- back-end architecture
- front-end architecture
- api documentation
- workflows using github actions
- ss with the front-end application
- ss with the folder structure of the back-end
- ss with all the messages from Slack

AWS link of the calories calculator application:

http://calories-calculator.us-east-1.elasticbeanstalk.com/

DockerHub image link

https://hub.docker.com/r/andrei1999cj/calories-calculator

Application Requirements

- the appliction should allow users to create accounts
- in order to use the calculator users must log in
- the application should contain a database with all aliments -> that can be seen/updated by all users
- the application should permit users to consume aliments
- the application should permit users to add aliments
- the application should permit users to delete aliments from aliments database - in the future, this feature should be possible just for special users(admins)
- the application should track all the consumed calories and macronutrients
- the application should permit to remove consumed aliments
- the application should keep a list of the consumed aliments of each user
- an update of the quantity of a consumed aliment should be possible
- each user should have control(delete/update quantity/see) just for the aliments consumed by them
- an aliment should not be deleted if is already consumed by a person


Below is the ERD for the database:
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/aac85bef-c8df-493a-98b0-934a2b8169dd)

For the back-end I used the N-Tier architecture:

![N-Tier Architecture](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/2e06236e-8f8f-4fe8-a01e-e96e421f7293)




Below is the detailed architecture of the back-end code:
![Detailed_Architecture](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/e3084645-f0e6-43c5-b6fc-618409b04882)



The architecture of the front-end:
![FE_Architecture](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/aec9d330-f789-45f7-a6b2-1fd8319d04f7)



API documentation using swagger:
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/6d3612ef-4330-4e91-80f7-5e7e313b99a5)
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/564b5afb-574f-43c2-87fa-a9b350df90a0)
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/36793f99-1e65-453c-81f3-363599b6f2b9)



The workflows implemented with github actions:

Continuous Integration:
![Workflow](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/b7234ffe-53e7-4936-9141-990adbd3d002)



Continuous Integration/ Continuous Deployment
![CICD](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/7538eca6-b96a-4c8b-8e07-ae21ebce9b01)


Front-End Application Images
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/7cd469e0-527f-4fc4-975a-8b86075ad83e)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/23d8bb03-399f-4c44-9aa8-294162b6e732)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/01d52025-3f25-4da4-8e48-ae1ec83c4bc8)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/cb2d3321-d487-4bcb-a9ba-840ea001fd61)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/aed26722-4896-4671-8963-fd3e129effa0)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/a6363b4d-8327-421f-8ea1-700ddf72b957)


Below are some ss with the folder structure of the back-end application:

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/10f5b55e-a5f6-4040-8e3b-401a7f2361e9)

![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/cfdc5316-3a21-4edb-9768-d30e7b2be98b)

Images from Slack:
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/e4967f04-c49d-4031-8a50-044927f7d9c8)
![image](https://github.com/Andrei1999CJ/CaloriesCalculator/assets/86969370/32ebb5da-3853-4c75-acc1-c8eb8cdbf78a)







