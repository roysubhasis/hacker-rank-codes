# Hackerrank : REST API : FOOD OUTLET Problem

Use HTTP GET method to retrieve information from a database of food outlets.

**Query:**   
https://jsonmock.hackerrank.com/api/food_outlets?city=< city > to find all records for a city.

The query result is paginated. The access additional pages, append &page=< pageNumber > to the URL where num is the page number.

https://jsonmock.hackerrank.com/api/food_outlets?city=< city >&page=< pageNumber >   
where < city > is the city to get the food outlets for, and < pageNumber > is an integer that denotes the page of the results to return.

_Sample API 1:_   
https://jsonmock.hackerrank.com/api/food_outlets?city=Seattle&page=2 
returns the data associated with the second page of results for Seattle city.

Sample response JSON:
```
{
    "page": 1,
    "per_page": 10,
    "total": 400,
    "total_pages": 40,
    "data": [
            {
                "city": "Seattle",
                "name": "Cafe Juanita",
                "estimated_cost": 160,
                "user_rating": {
                    "average_rating": 4.9,
                    "votes": 16203
                },
                "id": 41
            }
        ]
}
```

Fields in the JSON are given below,   
  * page: Current page of the result (number)
  * per_page: Max number of result per page
  * total: Total number of result
  * total_pages: Total number of pages with results.
  * data: An array with multiple objects, where a single object that contains food outlet records. 

_Returns:_   
An Array of strings denoting the food outlet names that are located in this city and have an estimated cost less than or equal to maxCost. The names in the array must be ordered as they appear in the API response.

_Constraints:_   
The given city will always have data returned from the API.


#### NOTE:
* FoodOutlet - It's is a recursive solution  
* FoodOutletResult - It's a non-recursive solution  
Refer code for more details.