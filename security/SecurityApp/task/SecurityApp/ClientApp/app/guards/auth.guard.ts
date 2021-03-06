﻿import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { isPlatformServer, isPlatformBrowser } from '@angular/common';
 
@Injectable()
export class AuthGuard implements CanActivate {
 
    constructor( @Inject(PLATFORM_ID) private platformId: Object, private router: Router) { }
 
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (isPlatformBrowser(this.platformId)) {
            if (localStorage.getItem('currentUser')) {
                // logged in so return true
                return true;
            }
        }
 
        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}