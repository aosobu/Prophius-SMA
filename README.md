# Prophius SMA
is a java based application that simulates basic operations of 
a social media application. Operations simulated include authentication, post creation,
post liking and user following.

## Pre-requisites
- Java 17
- Apache Maven 3.8.7
- IDE : Netbeans or IntelliJ

## Database
H2 in-memory database is used for this project.
You can adapt to any SQL database of your choosing.

## How to run locally
- Pull code with git via
 
        https://github.com/aosobu/prophius-worksheet


- Import into any IDE of your choice and run project.


- Project should be built with maven for dependency resolution.

#### (Check the prerequisites section for information on java and maven versions)

## Postman Collection

- The collection can be accessed at

        https://documenter.getpostman.com/view/3387356/2s9Ykq7LNu


## Architecture Documentatin

  The TRD can be accessed at
  
    https://seed-earthquake-9a1.notion.site/Social-Media-TRD-86d3e531bf09497b9e173fab3dd90844

    
## Technical Limitations

- As at the time of development, docker compose was not installed on development machine due to operating system incompatibility - Microsoft Windows 10 pro - preventing the dockerizing of the application and, also,  building as a fully distributed system.


- Atomic commits was not used as expected as a result of personal oversight.


- Lean Technical Requirement documentation due to time constraints.


## Technical Choices
##### - Image Upload: An hybrid of file retrieval and database storage was implemented - to guarantee the dual requirements of performance and availability.


##### - Due to technical challenge experienced on development environment, that prevented the installation of docker compose,  this project could not be set up as fully distributed system - hence the following workaround was implemented in the creation of a POC, proof of concept.

1. The use of Spring Cache instead of Redis - to implement caching functionality, as application utilizes both cache aside and cache invalidation strategies.


2. The use of cron jobs to create workers for batch processing requirements, that should  typically  be housed in their own services or functions.


3. Eventual Consistency  and Event Sourcing algorithm for post like management.


## Support 

##### - For support when running this application - you can reach me via aosobu.dev@gmail.com or 08022221058.

