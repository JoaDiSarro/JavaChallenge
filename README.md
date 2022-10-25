# JavaChallenge

## Requirements

For building and running the application you need:

- JDK 17.0.5
- MAVEN 3.8.6

## Running the application locally

### Steps to run

1. Build the project using `mvn clean install`
2. Run using `mvn spring-boot:run` or execute the `main` method in the `JavaChallengeApplication` class from your IDE
3. The web application is accesible via localhost:8080

## General info

Application that obtains the final price (pvp) and the rate that is applied to a certain product of a brand between
certain dates.

The result obtained is the brand, the product, the final price (PVP), the percentage increase and the start and end
dates.

The pvp is obtained as a result of increasing the price by the percentage value. For example, if the price is 100 and
the percentage is 10, the result of the pvp is 110.

In case there is more than one result for a date, product and brand, the selected one is the one with greater priority.