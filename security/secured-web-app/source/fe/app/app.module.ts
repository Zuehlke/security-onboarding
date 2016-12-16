import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {HttpModule} from "@angular/http";
import {EmployeeService} from "./employee-list/employee.service";
import {CommonModule} from "@angular/common";
import {routing, appRoutingProviders} from "./app.routing";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth/auth-guard.service";
import {AuthService} from "./auth/auth.service";
import {FormsModule} from "@angular/forms";
import {HttpClient} from "./http/http-client";
import {CredentialsRepository} from "./auth/credentials-repository";
import {ApiEndpoints} from "./api-endpoints";

@NgModule({
  imports: [BrowserModule, HttpModule, CommonModule, routing, FormsModule],
  declarations: [AppComponent, EmployeeListComponent, LoginComponent],
  providers: [EmployeeService, appRoutingProviders, AuthGuard, AuthService, HttpClient, CredentialsRepository, ApiEndpoints],
  bootstrap: [AppComponent]
})
export class AppModule {
}
