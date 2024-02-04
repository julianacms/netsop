### Exercise

### Create a calculator
- The task is to create a small price engine and calculator. The engine should calculate the price for one or more parcels based on weight. We will not consider postal codes in this calculation.
- There should be a GUI that lists the prices in a two-dimensional table based on 1-10 kilo on one axis and 1-5 parcels on the other axis.
- There should be a GUI that presents a simple calculator that lets the user choose the number of parcels and number of kilos. For simplicity, we will assume that all the parcels weigh the same amount.
- The application should be created test driven.
- At least one element of the price must be stored in a database, but simplifications and hardcoding the price structure for the rest is ok.
- The functionality must be implemented in Java.

Price is calculated as follows:
- For each order there is a base service fee of 10,- regardless of number of parcels.
- Price per parcel starts at 50 kroners for 1 kilo and increases by 5 kroners per kilo. Max weight is 35 kilos. Assume that all orders are made in integers.

Tips:
- `mvn clean install` (builds the app and runs tests)
- `mvn spring-boot:run` (starts a servlet container with the app)
- Request application on <http://localhost:8080/>

### Upload the solution
Provide your solution well before the interview (the day before). If the solution is provided somewhere on the internet
the source code should be readable by the whole recruitment team.

### Presentation
Bring a laptop and prepare to show the code, which frameworks you've used (how and why) and how you chose to structure the code.

