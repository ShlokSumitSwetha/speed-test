# Internet Speed Test

This project evaluates the internet speed by calling https://fast.com

It polls fast.com's API once every 10 minutes and stores the evaluated results in postgres database.
https://api.fast.com/netflix/speedtest?https=true&token=YXNkZmFzZGxmbnNkYWZoYXNkZmhrYWxm&urlCount=5


https://www.npmjs.com/package/fast-speedtest-api

npm install --global fast-speedtest-api
npm install --save fast-speedtest-api

# Pre-requists
1.  #####PostgreSQL Database docker image
    ```
    docker run -e POSTGRES_PASSWORD=postgres -p 5432:5432  -d postgres:9-alpine -c "listen_addresses=*"
    ```    
 2. Run below commands
    ```
    npm install --global fast-speedtest-api
    npm install --save fast-speedtest-api
     ```  

# Build
   ```
1. mvn clean install
2. Run the Spring boot application using below command
    mvn spring-boot:run
   ```
 # Postgres Details
 
    
     spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
     spring.datasource.username=postgres
     spring.datasource.password=postgres
    
## API

       http://localhost:8080/getInternetSpeed
       
       http://localhost:8080/getAllInternetSpeeds






