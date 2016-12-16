import {Component} from "@angular/core";
import {EmployeeService} from "./employee.service";
import {Employee} from "./employee.model";
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.html',
  styleUrls: ['./employee-list.css'],
  moduleId: module.id,
})
export class EmployeeListComponent {

  employees: Employee[];
  searchId: string;

  constructor(private employeeService: EmployeeService, private authService: AuthService) {}

  ngOnInit(): void {
    this.employeeService.getEmployees().then(employees => {
      this.employees = employees;
    });
  }
  
  getEmployee(): void {
	if (this.searchId == '') {
	  this.employeeService.getEmployees().then(employees => {this.employees = employees;});
	  return;
	}
	this.employeeService.getEmployeeById(this.searchId).then(employee => this.employees = [employee]);
  }

  get canDelete(): boolean {
    return this.authService.isAdmin;
  }

  delete(employee: Employee): void {
    this.employeeService.deleteEmployee(employee.id)
      .then(() => {        
        this.employees.splice(this.employees.indexOf(employee), 1);
      });
  }
}
