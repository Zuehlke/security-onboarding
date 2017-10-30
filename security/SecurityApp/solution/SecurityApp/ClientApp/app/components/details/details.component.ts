import { Component } from '@angular/core';
import { EmployeeServices } from '../../service/services';
import { Response } from '@angular/http';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'employee-detail',
    templateUrl: './details.component.html'
})
export class DetailsComponent {

    private EmpId: number;
    public EmployeeDetails: { [key: string]: any[] } = {};

    public constructor(private empService: EmployeeServices, private activatedRoute: ActivatedRoute) {
        this.activatedRoute.params.subscribe((params: Params) => {
            this.EmpId = params['id'];
        });

        this.empService.getEmployeeDetails(this.EmpId)
            .subscribe((data: Response) => (this.EmployeeDetails["FirstName"] = data.json().firstName,
                this.EmployeeDetails["LastName"] = data.json().lastName,
                this.EmployeeDetails["Title"] = data.json().title,
                this.EmployeeDetails["JMBG"] = data.json().jmbg,
                this.EmployeeDetails["disabled"] = data.json().disabled
            ));

    }

}  