import { Component } from '@angular/core';
import { EmployeeServices } from '../../service/services';
import { Response } from '@angular/http';
import { FormGroup, FormControl, Validators } from '@angular/forms';  

@Component({
    selector: 'new-employee',
    templateUrl: './newEmployee.component.html'
})
export class newEmployeeComponent {
    public formData: FormGroup;
    public constructor(private empService: EmployeeServices) {
     
        this.formData = new FormGroup({
            'FirstName': new FormControl('', [Validators.required]),
            'LastName': new FormControl('', Validators.required),
            'Title': new FormControl('', Validators.required),
            'JMBG': new FormControl(0, [Validators.required]),
            'disabled': new FormControl(0, [Validators.required])
        });

    }

    submitData() {
        if (this.formData.valid) {
            var Obj = {
                Title: this.formData.value.Tite,
                FirstName: this.formData.value.FirstName,
                LastName: this.formData.value.LastName,
                Disabled: this.formData.value.disabled,
                JMBG: this.formData.value.JMBG
            };
            this.empService.postData(Obj).subscribe();
            alert("Employee Inserted Successfully");
        }

    }  

}   