import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Http, Headers} from "@angular/http";
import {UserCredentials} from "./userCredentials";
import {CredentialsRepository} from "./credentials-repository";

@Injectable()
export class AuthService {
  private _isLoggedIn: boolean = false;
  private _user: UserCredentials;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(private http: Http, private credentialsRepository: CredentialsRepository) {
    this._isLoggedIn = credentialsRepository.get() !== null;
  }

  login(user: UserCredentials): Promise<void> {
    return this.http.get('http://localhost:8080/login', {
      headers: new Headers({
        Authorization: `Basic ${this.credentialsRepository.saveAndGet(user)}`
      })
    }).toPromise()
      .then(() => {
        this._isLoggedIn = true;
        this._user = user;
      })
      .then(() => {});
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  get user():UserCredentials {
    return this._user;
  }

  logout() {
    this.credentialsRepository.clear();
    this._isLoggedIn = false;
  }
}
