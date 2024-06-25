# Hackerrank : REST API : Asteroid Monitoring Solution
This HTTP GET https://jsonmock.hackerrank.com/api/asteroids REST API contains information from a database of asteroid. 
To use the search feature, one can add _/search_ followed by the parameter and keyword which is case in-sensitive. 
If the keyword exists in the parameter's value, it is included in the response. 
For example https://jsonmock.hackerrank.com/api/asteroids/search?parameter={keyword}. 

The query result is paginated and can be further accessed by appending _?page=num_ to the query where _num_ is the page number.

The response is a JSON with the following 5 fields:
* page: the current page of the results
* per_page: the maximum number of results returned per page
* total: the total number of results
* total_pages: the total number of pages with results
* data: Either an empty array or an array of medical records as JSON objects. Each medical record object has multiple properties but below are needed for this question:

In data, each asteroid object has the following schema:
* designation: The name of the asteroid(String)
* discovery_date: The date of the discovery(String)
* period_yr: The rotation period of an asteroid in years(String)
* pha: Is the asteroid potentially hazardous? Its values is "Y", "N" or "n/a" (String)

Given the year of discovery and the value of _pha_, filter the results based on the given parameters and sort ascending on period_yr. In case of a tie, sort on designation, also ascending.
Return the list of designations.If period_yr does not exist for an asteroid, assume its value as 0.

Below is an example JSON :
```
{
    "page": 1,
    "per_page": 10,
    "total": 202,
    "total_pages": 21,
    "data": [
        {
            "designation": "419880 (2011 AH37)",
            "discovery_date": "2011-01-07",
            "h_mag": "19.7",
            "moid_au": "0.035",
            "q_au_1": "0.84",
            "q_au_2": "4.26",
            "period_yr": "4.06",
            "i_deg": "9.65",
            "pha": "Y",
            "orbit_class": "Apollo",
            "discovery_timestamp": 1294358400
        },
        {
            "designation": "414772 (2010 OC103)",
            "discovery_date": "2010-07-28",
            "h_mag": "19",
            "moid_au": "0.333",
            "q_au_1": "0.39",
            "q_au_2": "2",
            "period_yr": "1.31",
            "i_deg": "23.11",
            "pha": "N",
            "orbit_class": "Apollo",
            "discovery_timestamp": 1280275200
        }
    ]
}
```

_Function Description:_   
Complete the function **asteroidMonitor** in the editor below,

**asteroidMonitor** has the following parameter(s):   
 integer year: year of discovery   
 string pha: either "Y" or "N" denoting if the asteroid is potentially hazardous.

_Returns:_   
 string[]: a list of planet names.

_**Sample Case 0:**_   

- Sample Input For Custom Testing    
  2011
  Y


- Sample Output  
  419880 (2011 AH37)

Explantion:
Here we are hitting the API with below parameters,  
https://jsonmock.hackerrank.com/api/asteroids/search?discovery_date=2011&pha=Y&page={num}  
where we {num} is page number

_**Sample Case 1:**_   

- Sample Input For Custom Testing  
  2011
  N


- Sample Output  
  (2011 BY24)   
  (2011 BN59)
  
Explantion:
  Here we are hitting the API with below parameters,  
  https://jsonmock.hackerrank.com/api/asteroids/search?discovery_date=2011&pha=N&page=1  
  where we {num} is page number.

#### NOTE:
* AsteroidMonitoring - It's is a recursive solution  
* AsteroidResult - It's a non-recursive solution  
Refer code for more details.
