import { Component } from '@angular/core';
import {EmployeeService} from "./employee.service";
import {Employee} from "./employee.model";

@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.html',
  moduleId: module.id,
})
export class EmployeeListComponent {
  constructor(private employeeService: EmployeeService) {
    this.getEmployees();
  }

  getEmployees(): Promise<Employee[]> {
    return this.employeeService.getEmployees();
  }

}
