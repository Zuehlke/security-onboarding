import {Employee} from "./employee.model";
import {Injectable} from "@angular/core";
import "rxjs/add/operator/toPromise";
import {HttpClient} from "../http/http-client";
import {ApiEndpoints} from "../api-endpoints";

@Injectable()
export class EmployeeService {
  constructor(private http: HttpClient, private urlConfig: ApiEndpoints) {
  }

  /**
   * Gets the list of employees from rest endpoint
   * @returns {Promise<Employee[]>}
   */
  getEmployees(): Promise<Employee[]> {
    return this.http.get(this.urlConfig.employees).toPromise()
      .then(response => response.json() as Employee[])
      .catch(console.error);
  }

  /**
   * Gets the employee with specific id
   * @returns {Promise<Employee>}
   */
  getEmployeeById(id: string): Promise<Employee> {
    return this.http.get(this.urlConfig.employeeSearch(id)).toPromise()
      .then(response => response.json() as Employee)
      .catch(console.error);
  }

  deleteEmployee(id: number): Promise<void> {
    return this.http.delete(this.urlConfig.employees + "/" + id)
      .toPromise()
      .then(() => {});
  }

  addEmployee(employee: Employee): Promise<void> {
    return this.http.post(this.urlConfig.employees, employee)
      .toPromise()
      .then(()=>{});
  }

  addEmployee(employee: Employee): Promise<Employee> {
    return this.http.post(this.urlConfig.employees, employee)
      .toPromise()
      .then((r) => r.json());
  }
}
