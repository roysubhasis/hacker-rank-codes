# Hackerrank : REST API : Highest International Students

Use HTTP GET method to retrieve information from a database of universities.

**Query:** 

https://jsonmock.hackerrank.com/api/universities to find university records.
The query result is paginated. The access additional pages, append &page=< num > to the URL where num is the page number.

Sample response JSON:   
```
{
    "page": 1,   
    "per_page": 10,   
    "total": 200,   
    "total_pages": 20,   
    "data": [   
                {   
                "university": "Massachusetts Institute of Technology (MIT)",   
                "rank_display": "1",   
                "score": 100,   
                "type": "Private",   
                "student_faculty_ratio": 4,   
                "international_students": "3,730",   
                "faculty_count": "3,065",   
                    "location": {   
                        "city": "Cambridge",
                        "country": "United States",
                        "region": "North America"
                    }
                }
            ]
}
```


Fields in the JSON are given below,
* page: Current page of the result (number)
* per_page: Max number of result per page
* total: Total number of result
* total_pages: Total number of pages with results.
* data: An array with multiple objects, where a single object that contains universities records. 

Complete the highestInternationalStudents function where given the name of two cities as parameters, return the name of the university with the highest number of international students on the first city.
If the first city does not have a university within the data, return the university with the highest number of international students in the second city.

_Function Description:_

Complete the functions highestInternationalStudents.  

highestInternationalStudents() has 2 parameters,  
   String firstCity : name of the firstCity  
   String secondCity : name of the secondCity

_Returns:_   
string: the university name with highest number of international students

_Constrains:_   
There is always a university in one of the two cities.

_Sample Case 0:_
 - Input:  
   Singapore  
   New York City
   

 - Output:  
   National University of Singapore (NUS)
