import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {HttpModule} from "@angular/http";
import {EmployeeService} from "./employee-list/employee.service";
import {CommonModule} from "@angular/common";

@NgModule({
  imports: [ BrowserModule, HttpModule, CommonModule ],
  declarations: [ AppComponent, EmployeeListComponent ],
  providers: [EmployeeService],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
