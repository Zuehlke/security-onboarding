import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {HttpModule} from "@angular/http";
import {EmployeeService} from "./employee-list/employee.service";
import {CommonModule} from "@angular/common";
import {routing, appRoutingProviders} from "./app.routing";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth/auth-guard.service";
import {AuthService} from "./auth/auth.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [ BrowserModule, HttpModule, CommonModule, routing, FormsModule],
  declarations: [ AppComponent, EmployeeListComponent , LoginComponent],
  providers: [EmployeeService, appRoutingProviders, AuthGuard, AuthService],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
