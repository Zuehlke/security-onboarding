import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Http, Headers} from "@angular/http";
import {UserCredentials} from "./userCredentials";

@Injectable()
export class AuthService {
  isLoggedIn: boolean = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(private http: Http) {}

  login(user: UserCredentials): Promise<void> {
    return this.http.get('http://localhost:8080/employees', {
      headers: new Headers({
        Authorization: `Basic ${btoa(user.name + ':' + user.password)}`
      })
    }).toPromise()
      .then(() => {
        this.isLoggedIn = true;
      })
      .then(() => {});
  }

}
