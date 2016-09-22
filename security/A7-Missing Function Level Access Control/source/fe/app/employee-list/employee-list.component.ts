import { Component } from '@angular/core';
import {EmployeeService} from "./employee.service";
import {Employee} from "./employee.model";

@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.html',

  moduleId: module.id,
})
export class EmployeeListComponent {

  employees: Employee[];

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.employeeService.getEmployees().then(employees => {
      this.employees = employees;
    });
  }
}
