import { Component } from '@angular/core';
import {ActivatedRoute, Router, NavigationEnd, Event, NavigationStart, RoutesRecognized} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.html',

  moduleId: module.id,
})
export class LoginComponent {

  title: string;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    // this.route.data.subscribe((data: any) => {
    //   this.title = data.title;
    //   console.log(data);
    // });
    // this.router.events.subscribe( (ev: Event) => {
    //   if(ev instanceof NavigationEnd) {
    //     this.router;
    //     this.route;
    //     this.route.data.subscribe(d => console.log(d));
    //     this.route.children.forEach(r => {
    //       console.log(this.route.snapshot.data);
    //     })
    //   }
    // });

  }
}
