import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {UserCredentials} from "../auth/userCredentials";

@Component({
  selector: 'login',
  templateUrl: './login.html',
  moduleId: module.id,
  styleUrls: ['./login.css']
})
export class LoginComponent {

  user: UserCredentials = {name: '', password: ''};
  loginError: boolean;
  showLoading: boolean = false;

  constructor(private router: Router, private authService: AuthService) {}

  onSubmit(): void {

    const isLoadingTimeoutId = setTimeout(() => {
      this.showLoading = true;
    }, 1000);

    this.authService.login(this.user).then( () => {
      this.router.navigate(['/employees']);
      this.showLoading = false;
      this.loginError = false;
      clearTimeout(isLoadingTimeoutId);
    }).catch(() => {
      this.loginError = true;
      this.showLoading = false;
      clearTimeout(isLoadingTimeoutId);
    });
  }
}
