# CashMan-3001
A cash dispensing application for use in an ATM or similar device.

Assumption:
Java 1.8 and Apache maven 3.5.2 is installed and path for them is set in User machine

Steps for build
1. Clone this repository into local directory
2. Open terminal/Command prompt in the base package where pom.xml is present
3. run "mvn clean package"

Steps to run application
1. Open terminal/Command prompt in the base package where pom.xml is present
2. run "java -jar target/CashMan-3001-0.0.1-SNAPSHOT.jar"

I have User Springboot 2.0.0.RELEASE version with following starter projects
1. web
2. jpa
3. mail
4. h2database (This is a in memory database)
5. test

It is a web application developed in MVC design pattern.
The in memory database H2-console is enabled and available on this URL "http://localhost:3000/h2-console"
When application is up, the live data can be seen in this console.

On startup only I have added one ATM with atmId = 1 with following setup
One ATM with atmId 1 is added in database with following properties.


By default ATM will be in "Working" status.

It will have 24 Note of $20, The ATM slot for $20 can have maximum of 100 note
and minimum threshold value 10. In case of after withdrawal of money $20 note becomes less that 10 then 
ATM status will be "LowBalance" and a notification will go to Bank Admin to refill the ATM.

It will have 15 Note of $50, The ATM slot for $50 can have maximum of 100 note
and minimum threshold value 10. In case of after withdrawal of money $25 note becomes less that 10 then 
ATM status will be "LowBalance" and a notification will go to Bank Admin to refill the ATM.

In case of both slot becomes empty then ATM status will be "OutOfCash"


It expose following REST end points for Bank Admin and Bank clients.

1. To check ATM balance
GET request to "http://localhost:3000/admin/atmBalance/{atmId}"
Example : http://localhost:3000/admin/atmBalance/1
Response: {
    "total": 1230,
    "count20": 24,
    "count50": 15
}

2. To Check ATM status
GET request to "http://localhost:3000/client/atmStatus/{atmId}"
Example: http://localhost:3000/client/atmStatus/1
Response: {
    "status": "Working",
    "description": "ATM is working and sufficient currency available in the ATM"
} 

3. To get ATM Slots available status for refill
GET request to "http://localhost:3000/admin/atmSlotSpace/{atmId}"
Example: http://localhost:3000/admin/atmSlotSpace/1
Response:{
    "atmId": 1,
    "denomination20": 76,
    "denomination50": 85,
    "info": "Available space for refill"
}

4. To request to Withdraw  money
Get Request to: "http://localhost:3000/client/withdraw/{atmId}/{amount}"
Example: http://localhost:3000/client/withdraw/1/110
Response:{
    "atmId": 1,
    "denomination20": 3,
    "denomination50": 1,
    "info": "Congratulation, withdrawal of amount 110 is successful, Please collect your cash"
}

5. To refill the ATM
PUT request to: "http://localhost:3000/admin/refillAtm"
Request Body: {
    "atmId": 1,
    "denomination20": 2,
    "denomination50": 20
}
Headers= Content-Type: application/json

Response:{
    "atmId": 1,
    "denomination20": 26,
    "denomination50": 35,
    "info": "ATM Refilled successfully, This is new ATM balance"
}

In case of requested notes are more than available space
Request body: {
    "atmId": 1,
    "denomination20": 200,
    "denomination50": 20
}
Response:{
    "atmId": 1,
    "denomination20": 26,
    "denomination50": 35,
    "info": "ATM Balance does not updated. Refill failed due to insufficient space in ATM slot, please try maximum note of $20 <= 74  and $50 <= 65"
}

****************************************************************************************************************
Problem Summary
You are required to design/develop a cash dispensing application for use in an ATM or similar device.There is no need to request authorisation or availability of funds. The application should assume that all requests are legitimate; there will be other components of the system that will do things such as communicating with bank accounts and authorising withdrawals.

Mandatory Feature Set
•	The device will have a supply of cash (physical bank notes) available. 
•	It must know how many of each type of bank note it has. It should be able to report back how much of each note it has. 
•	It should be possible to tell it that it has so many of each type of note during initialisation. After initialisation, it is only possible to add or remove notes. 
•	It must support $20 and $50 Australian denominations. 
•	It should be able to dispense legal combinations of notes. For example, a request for $100 can be satisfied by either five $20 notes or 2 $50 notes. It is not required to present a list of options. 
•	If a request can not be satisfied due to failure to find a suitable combination of notes, it should report an error condition in some fashion. For example, in an ATM with only $20 and $50 notes, it is not possible to dispense $30. 
•	Dispensing money should reduce the amount of available cash in the machine. 
•	Failure to dispense money due to an error should not reduce the amount of available cash in the machine. 
For an ATM-style of machine (with $20 and $50 notes), the following dispensed amounts are of particular interest: 
•	$20 
•	$40 
•	$50 
•	$70
•	$80
•	$100 
•	$150 
•	$60 
•	$110 
•	$200, when there is only 3x$50 notes and 8x$20 notes available.
Optional Feature Set
•	Support all other legal Australian denominations and coinage. 
•	The controller should dispense combinations of cash that leave options open. For example, if it could serve up either 5 $20 notes or 2 $50 notes to satisfy a request for $100, but it only has 5 $20 notes left, it should serve the 2 $50 notes. 
•	The controller needs to be able to inform other interested objects of activity. Threshold notification in particular is desirable, so that the ATM can be re-supplied before it runs out of cash. 
•	Persistence of the controller is optional at this time. It can be kept in memory. However, it should go through a distinct initialisation period where it is told the current available amounts prior to being used.
****************************************************************************************************************
