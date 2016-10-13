import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Http, Headers} from "@angular/http";
import {UserCredentials} from "./userCredentials";
import {CredentialsRepository} from "./credentials-repository";
import {AuthenticationResponse} from "./authentication-response";
import {Roles} from "./roles";
import {ApiEndpoints} from "../api-endpoints";

@Injectable()
export class AuthService {
  private _isLoggedIn: boolean = false;
  private _isAdmin: boolean;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  private IS_ADMIN_KEY = "IS_ADMIN";

  constructor(private http: Http, private credentialsRepository: CredentialsRepository,
              private urlConfig: ApiEndpoints) {
    this._isLoggedIn = credentialsRepository.get() !== null;
    this._isAdmin =  localStorage.getItem(this.IS_ADMIN_KEY) === "true";

  }

  login(user: UserCredentials): Promise<void> {
    return this.http.get(this.urlConfig.login, {
      headers: new Headers({
        Authorization: `Basic ${this.credentialsRepository.saveAndGet(user)}`
      })
    }).toPromise()
      .then( response => {
        this._isLoggedIn = true;
        this._isAdmin = (response.json() as AuthenticationResponse).roles.includes(Roles.ADMIN);
        localStorage.setItem("IS_ADMIN", this._isAdmin ? "true":"false");
      })
      .then(() => {});
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }


  get isAdmin(): boolean {
    return this._isAdmin;
  }

  logout() {
    this.credentialsRepository.clear();
    this._isLoggedIn = false;
  }
}
