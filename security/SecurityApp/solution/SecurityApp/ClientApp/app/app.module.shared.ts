import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './components/app/app.component';
import { HomeComponent } from './components/home/home.component';
import { DetailsComponent } from './components/details/details.component';
import { newEmployeeComponent } from './components/newEmployee/newEmployee.component';
import { editEmployeeComponent } from './components/editEmployee/editEmployee.component'; 

import { EmployeeServices } from './service/services';
import { AuthGuard } from './guards/auth.guard';
import { AuthenticationService } from './service/authentication.service';
import { LoginComponent } from './login/login.component';
import { ZPrint } from "./directives/print.directive";

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        DetailsComponent,  
        newEmployeeComponent,  
        editEmployeeComponent,
        LoginComponent,
        ZPrint
    ],
    providers: [EmployeeServices, AuthGuard, AuthenticationService],
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            { path: 'login', component: LoginComponent },
            { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
            { path: 'details/:id', component: DetailsComponent },
            { path: 'new', component: newEmployeeComponent  },
            { path: 'edit/:id', component: editEmployeeComponent },  
            { path: '**', redirectTo: 'home' }
        ])
    ]
})
export class AppModuleShared {
}
