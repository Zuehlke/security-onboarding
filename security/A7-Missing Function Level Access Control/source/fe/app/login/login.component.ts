import { Component } from '@angular/core';
import {ActivatedRoute, Router, NavigationEnd, Event, NavigationStart, RoutesRecognized} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.html',

  moduleId: module.id,
})
export class LoginComponent {

  user: {name: string, password: string} = {name: '', password: ''};

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
  }
}
