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
	this.employeeService.getEmployeeById(this.searchId).then(employee => {
    if (!employee) {
      this.employees = [];
    } else {
      this.employees = [employee];
    }    
  });
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

  add(firstName: string, lastName: string, title: string, jmbg: string) {
    const employee = new Employee(firstName, lastName, title, jmbg);
    this.employeeService.addEmployee(employee)
      .then(e => {
        this.employees.push(e);

      });
  }
}
