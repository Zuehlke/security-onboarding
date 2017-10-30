import { Component, OnInit, PLATFORM_ID, Inject } from '@angular/core';
import { EmployeeServices } from '../../service/services';
import { Response } from '@angular/http';  
import { User } from '../../models/user';
import { isPlatformServer, isPlatformBrowser } from '@angular/common';
import { FormGroup, FormControl, Validators } from '@angular/forms';  
import { AuthenticationService } from '../../service/authentication.service';

@Component({
    selector: 'home',
    templateUrl: './home.component.html'
})
export class HomeComponent {
    currentUser: User;
    authService: AuthenticationService;
    public EmployeeList = [];
    public formData: FormGroup;

    public constructor( @Inject(PLATFORM_ID) private platformId: Object, private empService: EmployeeServices, private authenticationService: AuthenticationService) {

        this.authService = authenticationService;

        this.empService.getEmployeeList()
            .subscribe(
            (data: Response) => (this.EmployeeList = data.json())
        );


        if (isPlatformBrowser(this.platformId)) {
            this.currentUser = JSON.parse(localStorage.getItem('currentUser') as string);
        }

        this.formData = new FormGroup({
            'FirstName': new FormControl('', [Validators.required]),
            'LastName': new FormControl('', Validators.required),
            'Title': new FormControl('', Validators.required),
            'JMBG': new FormControl('', [Validators.required])
            //'disabled': new FormControl(0, [Validators.required])
        });

      
    }  

    submitData() {
        if (this.formData.valid) {
            var Obj = {
                Title: this.formData.value.Tite,
                FirstName: this.formData.value.FirstName,
                LastName: this.formData.value.LastName,
                //Disabled: this.formData.value.disabled,
                JMBG: this.formData.value.jmbg
            };
            this.empService.postData(Obj).subscribe();
            alert("Employee Inserted Successfully");

            this.formData.reset();

            this.empService.getEmployeeList()
                .subscribe(
                (data: Response) => (this.EmployeeList = data.json())
                );
        }

    } 

    deleteEmployee(empId: number) {
        var status = confirm("Are You want to delete this employee ?");
        if (status == true) {
            this.empService.removeEmployeeDetails(empId)
                .subscribe((data: Response) => (alert("Employee Deleted Successfully")));

            //Get new list of employee  
            this.empService.getEmployeeList()
                .subscribe(
                (data: Response) => (this.EmployeeList = data.json())
                );
        }
    }

    SearchEmployee(search: string) {

        console.log("Search Employee ID : " + search);

        if (search) {
            this.empService.getEmployeeSearch(search)
                .subscribe(
                (data: Response) => (this.EmployeeList = data.json())
                );
        }
        else {
            this.empService.getEmployeeList()
                .subscribe(
                (data: Response) => (this.EmployeeList = data.json())
                );
        }        
    }

    get canDelete(): boolean {        
        return this.authService.isAdmin;
    }

}
