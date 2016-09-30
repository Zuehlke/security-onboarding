import {Component} from '@angular/core';
import {ActivatedRoute, Router, NavigationEnd, Event, NavigationStart, RoutesRecognized} from "@angular/router";
import {AuthService} from "./auth/auth.service";

@Component({
  selector: 'my-app',
  templateUrl: './app.html',
  moduleId: module.id
})
export class AppComponent {
  title: string;

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.router.events.subscribe((ev: Event) => {
      if (ev instanceof NavigationEnd) {
        this.getTitleOfTheRoute();
      }
    });

  }

  logout(): void {
    this.authService.logout();
  }

  get isLoggedIn(): boolean {
    return this.authService.isLoggedIn;
  }

  private getTitleOfTheRoute() {
    this.route.children[0].data.subscribe((data: any) => {
      this.title = data.title;
    });
  }

}
