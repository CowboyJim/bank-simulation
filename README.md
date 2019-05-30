# Carolina Fintech Hub RTP Hackathon Demo




We have been assigned one customer and two accounts for each bank--NYC and CIB. I have affectionatly named them
Alpha Bank and Beta Bank

|   Bank  | Customer # | Account Name | Accounts | RTN | 
| ------- | ---------- | ------------ | -------- | ---- |
| NYC Bank (Alpha) | NYCTEAM12 | Jason Davis | NYCTEAM120001,2 |  021000018 |
| CIB Bank (Beta) | CIBTEAM12 | Martin Everhart | CIBTEAM120001,2 | 021000020 | 


## Overview
The RTP Buildathon submission includes two instances of a Spring Boot apps that implement connectivity
to the Oracle payment gateway used in the competetion. There are two Spring provides used to represent 
each bank: *AlphaBank and BetaBank.*  Each are initialized differently since the profiles encapsulate
data unique to each bank. Therefore, the Boot applications must be launched with one bank provile like this:

-**Dspring.profiles.active=AlphaBank** or
-**Dspring.profiles.active=BetaBank**

In order to start a server to test REST client services, initialze the application in test mode by adding the 
 *test* Spring profile like this:

-**Dspring.profiles.active=AlphaBank,test**

This will allow you to call the APIs and get dummy data back for testing purposes.

The Bank simulation OpenAPI api definition file can be viewed and downloaded [HERE](https://app.swaggerhub.com/apis/ZelleCloud/RTP_Buildathon/1.0.0)




