import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {HttpModule} from "@angular/http";
import {EmployeeService} from "./employee-list/employee.service";
import {CommonModule} from "@angular/common";
import {routing, appRoutingProviders} from "./app.routing";
import {LoginComponent} from "./login/login.component";

@NgModule({
  imports: [ BrowserModule, HttpModule, CommonModule, routing],
  declarations: [ AppComponent, EmployeeListComponent , LoginComponent],
  providers: [EmployeeService, appRoutingProviders],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
