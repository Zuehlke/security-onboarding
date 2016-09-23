import {Http} from "@angular/http";
import {Employee} from "./employee.model";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class EmployeeService {
  private restEndpoint = 'http://localhost:8080/employees';

  constructor(private http: Http) { }

  /**
   * Gets the list of employees from rest endpoint
   * @returns {Promise<Employee[]>}
   */
  getEmployees(): Promise<Employee[]> {
    return this.http.get(this.restEndpoint).toPromise()
      .then(response => response.json() as Employee[])
      .catch(console.error);
  }
}
