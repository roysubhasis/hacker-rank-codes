# Hackerrank : REST API : Patient's Medical Record
This https://jsonmock.hackerrank.com/api/medical_records REST API contains patients' medical records. 
Given a patient id, fetch all the records for the patient and return their average body temperature. The API supports pagination and all pages for a patient must be analyzed.  

To access the medical information, perform an HTTP GET request to:

https://jsonmock.hackerrank.com/api/medical_records?userid=userid&page=num    
where _userid_ is the patient id and _num_ is the page number to fetch.

For example, a GET request to
https://jsonmock.hackerrank.com/api/medical_records?userid=3&page=3   
returns the third page of data for userid 3.

Similarly, a GET request to
https://jsonmock.hackerrank.com/api/medical_records?userid=3&page=1   
returns the first page of data for userid 3.

The response is a JSON with the following 5 fields:
* page: the current page of the results
* per_page: the maximum number of results returned per page
* total: the total number of results
* total_pages: the total number of pages with results
* data: Either an empty array or an array of medical records as JSON objects. Each medical record object has multiple properties but below are needed for this question:
* userId: user id for which we have queried
* vitals.bodyTemperature: user's body temperature for this particular record

Below is an example JSON of a medical record:

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


_Function Description:_
Complete the function **getAverageTemperatureForUser**.   
Use the property 'vitals.bodyTemperature' for each record to calculate the average.

**getAverageTemperatureForUser** has a single parameter:   
    int userld: the patient id to query

_Returns:_
If the data array of the response does not contain any medical records, return the string "0".
If the data array of the response contains medical records, return the patient's average body Temperature as a string with 1 decimal place.

_Sample Case 0:_   

- Sample Input For Custom Testing   
  1


- Sample Output  
  100.7

_Sample Case 1:_   

- Sample Input For Custom Testing  
  10


- Sample Output  
  0