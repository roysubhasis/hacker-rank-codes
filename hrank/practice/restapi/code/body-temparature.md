# Hackerrank : REST API : Body Temperature

**Query:**   
Use https://jsonmock.hackerrank.com/api/medical_records to retrieve information from a database of patient medical records to find all the records. 
The Query result is paginated and can be further accessed by appending the query string _?page=num_ where num is the page number.


Fields in the JSON are given below,
* page: Current page of the result (number)
* per_page: Max number of result per page
* total: Total number of result
* total_pages: Total number of pages with results.
* data: An array with multiple objects, where a single object that contains food outlet records.

Sample response JSON:
```
{
    "page": 1,
    "per_page": 10,
    "total": 99,
    "total_pages": 10,
    "data": [
        {
            "id": 1,
            "timestamp": 1565637002408,
            "diagnosis": {
                "id": 3,
                "name": "Pulmonary embolism",
                "severity": 4
            },
            "vitals": {
                "bloodPressureDiastole": 154,
                "bloodPressureSystole": 91,
                "pulse": 125,
                "breathingRate": 32,
                "bodyTemperature": 100
            },
            "doctor": {
                "id": 2,
                "name": "Dr Arnold Bullock"
            },
            "userId": 2,
            "userName": "Bob Martin",
            "userDob": "14-09-1989",
            "meta": {
                "height": 174,
                "weight": 172
            }
        }
    ]
}
```

_Problem Stmt:_
Given the doctor's name and diagnosis id, filter the records over all the pages, 
Return an array with two integers, the integer parts of minimum and maximum body temperature.


_Function Description:_   
Complete the function bodyTemperature.

bodyTemperature has the following parameters,   
 String doctorName : the name of the doctor.
 integer diagnosisId: the id of the diagnosis

_Returns:_   
int[2] : the minimum and maximum body temperatures

_Sample Case 0:_

Input:   
Dr Arnold Bullock
2

Expected Output:   
99.2
103.8

Explanation for case 0:  

Below is the list of body temperatures for doctor "Dr Arnold Bullock" and Diagnosis ID 2 from page 1 to page 10 in API. 
You can see the lowest temperature is 99.2 and highest is 103.8
1. "bodyTemperature": 101.9   
2. "bodyTemperature": 99.2   
3. "bodyTemperature": 100.4   
4. "bodyTemperature": 101   
5. "bodyTemperature": 102.6   
6. "bodyTemperature": 100.8   
7. "bodyTemperature": 103.8   
8. "bodyTemperature": 103.7   
9. "bodyTemperature": 103.5   
10. "bodyTemperature": 100.3  
11. "bodyTemperature": 99.5  

