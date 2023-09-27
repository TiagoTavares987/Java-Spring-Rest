# Development of a REST API for sending SMS

# Technologies used:
Java Spring Framework
PostgreSQL
ActiveMQ

# How to test endpoints

Retrieve all records

GET	localhost:8080/recipient/all

Parameters

Body

Response 200
{
    "id": 1,
    "name": "tiago",
    "phoneNumber": "+351911234567"
}
{
    "id": 2,
    "name": "diana",
    "phoneNumber": "+351961294567"
}
{
    "id": 3,
    "name": "tiago",
    "phoneNumber": "+351912137300"
}

--------------------

Retrieve the data based on the "id"

GET	localhost:8080/recipient/{id}

Parameters
id: 3

Body

Response 200
{
    "id": 3,
    "name": "tiago",
    "phoneNumber": "+351911234567"
}

--------------------

Add data

POST	localhost:8080/recipient

Parameters

Body
{
    "name":"tiago",
    "phoneNumber":"+351911234567"
}

Response 200
{
    "id": 1,
    "name": "tiago",
    "phoneNumber": "+351911234567"
}

--------------------

Sending Sms

POST	localhost:8080/recipient/send

Parameters

Body
{
    "ids":[1],
    "message":"Isto é um teste"
}

Response 200
{
    Sent
}

--------------------

Update a specific data based on "id"

PUT	localhost:8080/recipient/{id}

Parameters
id: 2

Body
{
    "id":2,	
    "name":"tiago",
    "phoneNumber":"+351921567567"
}

Response 200
{
    "id": 2,
    "name": "tiago",
    "phoneNumber": "+351921567567"
}

--------------------

Delete a specific data based on "id"

DELETE	localhost:8080/recipient/{id}

Parameters
id: 2

Body

Response 200
{
    true
}

