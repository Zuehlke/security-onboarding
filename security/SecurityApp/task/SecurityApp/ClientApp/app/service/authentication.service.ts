import { Injectable, PLATFORM_ID, Inject} from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { isPlatformServer, isPlatformBrowser } from '@angular/common';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    private _isAdmin: boolean;
    private _isLoggedIn: boolean = false;

    constructor( @Inject(PLATFORM_ID) private platformId: Object,private http: Http) {
        if (isPlatformBrowser(this.platformId)) {
            this._isAdmin = localStorage.getItem("IS_ADMIN") === "true";
        }
    }

    login(username: string, password: string) {

        return this.http.post('http://localhost:53348/api/account', { username: username, password: password })
            .map((response: Response) => {
                let user = response.json();
                if (user) {

                    // store user details and in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this._isLoggedIn = true;

                    if (user.isAdmin) {
                        // Chechk if admin
                        localStorage.setItem("IS_ADMIN", "true");
                        this._isAdmin = true;
                    }
                    else {
                        localStorage.setItem("IS_ADMIN", "false");
                        this._isAdmin = false;
                    }
                } else this._isLoggedIn = false;
            });
    }

    logout() {
        if (isPlatformBrowser(this.platformId)) {
            // remove user from local storage to log user out
            localStorage.removeItem('currentUser');
            localStorage.removeItem('IS_ADMIN');
        }
        this._isLoggedIn = false;
    }

    get isLoggedIn(): boolean {
        return this._isLoggedIn;
    }


    get isAdmin(): boolean {       
        return this._isAdmin;
    }
}