This is a dockerized backend application using Java 17, Spring boot, MySQL, Docker and RESTAPIs.
It manages sports venues, time slots, availability, and bookings with strict validation and conflict prevention. 
The system integrates with an external sports API to avoid hardcoded data, ensures safe booking with concurrency 
control, and provides RESTful APIs for venue management, slot handling, availability checks, and booking operations.
I handle all the edge cases and exception like in add venue api fields puincode should be 6 digit and other should be not null,
All the exceptions are handled and given correct error code and error message on postman.
In this application I use docker which is easily start up and MySQL for database.


Below are the APIs list with all assumptions with sample curl and postman screenshots(find in screenshots folder).
Just copy paste below CURLs to your postman.

1. VENUES- add venues
   below is the curl
   i. success :
    postman request POST 'http://localhost:8081/v1/venues/' \
  --header 'Content-Type: application/json' \
  --body '{
    "arena_name":"Sportiqo Sports Arena",
    "location":"Pune",
    "pinCode":411046,
    "sportId": 7031809
}'

ii. failed(empty fields):
postman request POST 'http://localhost:8081/v1/venues/' \
  --header 'Content-Type: application/json' \
  --body '{
    "arena_name":"Sportiqo Sports Arena",
    "location":"",
    "pinCode":4110,
    "sportId": 
}'

2. VENUS- GET ALL VENUES

   postman request 'http://localhost:8081/v1/venues/venues'

3. GET VENUE BY ID
   postman request 'http://localhost:8081/v1/venues/1'

4. DELETE VENUE
   postman request DELETE 'http://localhost:8081/v1/venues/2'

5. SLOT BOOKING
 i. SUCCESS:
postman request POST 'http://localhost:8081/v1/venues/1/slots' \
  --header 'Content-Type: application/json' \
  --body '{
    "startTime": "09:00",
    "endTime": "12:00"
}'  

   ii. FAILED : to see failed api you same use above CURL to see overlap.
   
6. AVAILABILITY-ALL AVAILABLE VENUES
   i. SUCCESS:
   postman request 'http://localhost:8081/v1/venues/available' \
  --header 'Content-Type: application/json' \
  --body '{
    "sportId":7061555,
    "startTime": "09:00",
    "endTime": "12:00"
}'

ii. FAILED :
  postman request 'http://localhost:8081/v1/venues/available' \
  --header 'Content-Type: application/json' \
  --body '{
    "sportId":7061555,
    "startTime": "10:00",
    "endTime": "08:00"
}'

7. BOOKING-CREATE BOOKING
   i. SUCCESS:
   postman request POST 'http://localhost:8081/v1/bookings/2'

   ii. FAILED:
   postman request POST 'http://localhost:8081/v1/bookings/150'

8. BOOKING-CANCEL BOOKING
   i. SUCCESS:
   postman request PUT 'http://localhost:8081/v1/bookings/1'

   ii. FAILED: you can use same curl to see failure that booking is already cancelled.
   



