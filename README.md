<h3><B>Smart Fridge Interface</B></h3>

<h4><B>Questions:</B></h4>
1. Do I need to persist data or in memory is fine?
2. In function ForgotItem when an item is forgotten can they be added to the fridge again?
3. In function getItems expectation is to return fillFactor(50%) or less full, including items that are depleted.Unless all available containers are 
empty. I am not sure if I understand this completly if it is depleted means it is empty? 
4. What does it mean with array of arrays containing [ itemType, fillFactor ]


<h4><B>Assumptions:</B></h4>
1. I assumed in memory DB is fine and used H2 java in memory DB
I used
2. I assumed even though item is not tracked it can still be removed from fridge and added back again and the status will be still the same "Ignored". For this reason I created a seperate table instead of creating relation to FridgeItem entity. 
3. I assumed if the fillfactor of container is 0 I didn't calculate it. However, I am getting the total for every thing that have fillfactor smaller or equal to fillfactor parameter and calculate the totals.
4. I assumed array of arrays is array of Object and created an object with itemType, fillFactor attribute

<h4><B>Run the application </B></h4>
    
    Navigate to the project directory and run the command
    mvn spring-boot:run

    For Testing:
    I've created a CommandLineRunner bean for testing. Comment out the lines there and test every method.
    You can also see the result from H2 DB. After running the application go to http://localhost:8080/h2-console
    and connect. There will be two tables called FRIDGE_ITEM and ITEM_TYPE_TRACKING.
    There are tests for the service in SmartFridgeManagerTest and ItemTrackingServiceImplTest  