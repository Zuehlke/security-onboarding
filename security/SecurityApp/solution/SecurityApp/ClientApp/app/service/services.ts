import { Injectable } from '@angular/core';  
import { Http } from '@angular/http';
import { RequestOptions } from '@angular/http';  
import { Headers } from '@angular/http';
  
@Injectable()  
export class EmployeeServices {  
    constructor(private http: Http) {  
         
    }  
    getEmployeeList() {  
        return this.http.get('http://localhost:53348/api/employee');  
    }  

    postData(empObj: any) {
        let headers = new Headers({
            'Content-Type':
            'application/json; charset=utf-8'
        });
        let options = new RequestOptions({ headers: headers });
        return this.http.post('http://localhost:53348/api/employee', JSON.stringify(empObj), options);
    }  

    getEmployeeDetails(empId: any) {
        return this.http.get('http://localhost:53348/api/employee/' + empId);
    }  

    getEmployeeSearch(empId: any) {
        return this.http.get('http://localhost:53348/api/employee/search/' + empId);
    }  


    removeEmployeeDetails(empId: any) {
        let headers = new Headers({
            'Content-Type':
            'application/json; charset=utf-8'
        });
        return this.http.delete('http://localhost:53348/api/employee', new RequestOptions({
            headers: headers,
            body: empId
        }));
    }  

    editEmployeeData(empObj: any) {
        let headers = new Headers({
            'Content-Type':
            'application/json; charset=utf-8'
        });
        let options = new RequestOptions({ headers: headers });
        return this.http.put('http://localhost:53348/api/employee', JSON.stringify(empObj), options);
    }  
}   