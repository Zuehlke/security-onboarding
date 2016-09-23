import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth/auth-guard.service";

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login'
    }
  },
  {
    path: '',
    component: EmployeeListComponent,
    data: {
      title: 'Employees List'
    },
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {path: '**', redirectTo: ''}
];

export const appRoutingProviders: any[] = [];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
