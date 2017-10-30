import { Component } from '@angular/core';
import { EmployeeServices } from '../../service/services';
import { Response } from '@angular/http';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'edit-employee',
    templateUrl: './editEmployee.component.html'
})
export class editEmployeeComponent {
    private EmpId: number;
    public EmployeeDetails = {};
    public employeeName: string;
    public formData: FormGroup;

    public constructor(private empService: EmployeeServices, private activatedRoute: ActivatedRoute) {
        this.activatedRoute.params.subscribe((params: Params) => {
            this.EmpId = params['id'];
        });

 
        this.formData = new FormGroup({
            'EmployeeId': new FormControl('', [Validators.required]),
            'FirstName': new FormControl('', [Validators.required]),
            'LastName': new FormControl('', Validators.required),
            'Title': new FormControl('', Validators.required),
            'JMBG': new FormControl('', Validators.required),
            'disabled': new FormControl('', Validators.required)

        });

        this.empService.getEmployeeDetails(this.EmpId)
            .subscribe((data: Response) => (
                this.formData.patchValue({ EmployeeId: data.json().employeeId }),
                this.formData.patchValue({ FirstName: data.json().firstName }),
                this.formData.patchValue({ LastName: data.json().lastName }),
                this.formData.patchValue({ Title: data.json().title }),
                this.formData.patchValue({ JMBG: data.json().jmbg }),
                this.formData.patchValue({ disabled: data.json().disabled })
            ));

    }

      submitData() {
        if (this.formData.valid) {
            var Obj = {
                EmployeeId: this.formData.value.EmployeeId,
                FirstName: this.formData.value.FirstName,
                LastName: this.formData.value.LastName,
                Title: this.formData.value.Title,
                JMBG: this.formData.value.JMBG,
                disabled: this.formData.value.disabled
            };
            this.empService.editEmployeeData(Obj)
                .subscribe((data: Response) => (alert("Employee Updated Successfully")));;

        }

    }
}  