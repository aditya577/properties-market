This system comprises of 3 independent web applications:

Listing service: Stores all the information about properties that are available to rent and buy
User service: Stores information about all the users in the system
Public API layer: Set of APIs that are exposed to the web/public
The listing service and user service are backed by relevant databases to persist data. The services are essentially a wrapper around their respective databases to manipulate the data stored in them. For this reason, the services are not intended to be directly accessible by any external client/application.

Services are free to store the data in any format they wish (in a SQL table, or as a document in a NoSQL db, etc.). The only requirement is for them to expose a set of REST APIs that return data in a standardised JSON format. Services are the guardians/gatekeepers for their respective databases. Any other application/service that wishes to access the data must go solely through the REST APIs exposed by the service. It cannot access the data directly from the database at any cost.

How does the mobile app or user-facing website access the data in the system? This is where the public API layer comes in. The public API layer is a web application that contains APIs that can be called by external clients/applications. This web application is responsible for interacting with the listing/user service through its APIs to pull out the relevant data and return it to the external caller in the appropriate format.

1) Listing Service
The listing service stores information about properties that are available to rent or buy. These are the fields available in a listing object:

id (int): Listing ID (auto-generated)
user_id (int): ID of the user who created the listing (required)
price (int): Price of the listing. Should be above zero (required)
listing_type (str): Type of the listing. rent or sale (required)
created_at (int): Created at timestamp. In microseconds (auto-generated)
updated_at (int): Updated at timestamp. In microseconds (auto-generated)
APIs
Get all listings
Returns all the listings available in the db (sorted in descending order of creation date). Callers can use page_num and page_size to paginate through all the listings available. Optionally, you can specify a user_id to only retrieve listings created by that user.

URL: GET /listings

Parameters:
page_num = int # Default = 1
page_size = int # Default = 10
user_id = str # Optional. Will only return listings by this user if specified
Response:
{
    "result": true,
    "listings": [
        {
            "id": 1,
            "user_id": 1,
            "listing_type": "rent",
            "price": 6000,
            "created_at": 1475820997000000,
            "updated_at": 1475820997000000,
        }
    ]
}
Create listing
URL: POST /listings
Content-Type: application/x-www-form-urlencoded

Parameters: (All parameters are required)
user_id = int
listing_type = str
price = int
Response:
{
    "result": true,
    "listing": {
        "id": 1,
        "user_id": 1,
        "listing_type": "rent",
        "price": 6000,
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000,
    }
}
2) User Service
The user service stores information about all the users on the system. Fields available in the user object:

id (int): User ID (auto-generated)
name (str): Full name of the user (required)
created_at (int): Created at timestamp. In microseconds (auto-generated)
updated_at (int): Updated at timestamp. In microseconds (auto-generated)
APIs
Get all users
Returns all the users available in the db (sorted in descending order of creation date).

URL: GET /users

Parameters:
page_num = int # Default = 1
page_size = int # Default = 10
Response:
{
    "result": true,
    "users": [
        {
            "id": 1,
            "name": "Suresh Subramaniam",
            "created_at": 1475820997000000,
            "updated_at": 1475820997000000,
        }
    ]
}
Get specific user
Retrieve a user by ID

URL: GET /users/{id}
Response:
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000,
    }
}
Create user
URL: POST /users
Content-Type: application/x-www-form-urlencoded

Parameters: (All parameters are required)
name = str
Response:
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000,
    }
}
3) Public APIs
These are the public facing APIs that can be called by external clients such as mobile applications or the user facing website.

Get listings
Get all the listings available in the system (sorted in descending order of creation date). Callers can use page_num and page_size to paginate through all the listings available. Optionally, you can specify a user_id to only retrieve listings created by that user.

URL: GET /public-api/listings

Parameters:
page_num = int # Default = 1
page_size = int # Default = 10
user_id = str # Optional
{
    "result": true,
    "listings": [
        {
            "id": 1,
            "listing_type": "rent",
            "price": 6000,
            "created_at": 1475820997000000,
            "updated_at": 1475820997000000,
            "user": {
                "id": 1,
                "name": "Suresh Subramaniam",
                "created_at": 1475820997000000,
                "updated_at": 1475820997000000,
            },
        }
    ]
}
Create user
URL: POST /public-api/users
Content-Type: application/json
Request body: (JSON body)
{
    "name": "Lorel Ipsum"
}
Response:
{
    "user": {
        "id": 1,
        "name": "Lorel Ipsum",
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000,
    }
}
Create listing
URL: POST /public-api/listings
Content-Type: application/json
Request body: (JSON body)
{
    "user_id": 1,
    "listing_type": "rent",
    "price": 6000
}
Response:
{
    "listing": {
        "id": 143,
        "user_id": 1,
        "listing_type": "rent",
        "price": 6000,
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000,
    }
}
