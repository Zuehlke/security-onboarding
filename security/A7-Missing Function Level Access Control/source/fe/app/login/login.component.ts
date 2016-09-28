import { Component } from '@angular/core';

@Component({
  selector: 'login',
  templateUrl: './login.html',
  moduleId: module.id,
  styleUrls: ['./login.css']
})
export class LoginComponent {

  user: {name: string, password: string} = {name: '', password: ''};

}
