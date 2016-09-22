import {Http} from "@angular/http";
import {Employee} from "./employee.model";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class EmployeeService {
  private restEndpoint = 'http://localhost:8080/';  // URL to web api

  constructor(private http: Http) { }

  getEmployees(): Promise<Employee[]> {
    return this.http.get(this.restEndpoint).toPromise()
      .then(response => response.json().data as Employee[])
      .catch(console.error);
  }
}
