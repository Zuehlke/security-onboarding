import {Component} from '@angular/core';
import {ActivatedRoute, Router, NavigationEnd, Event, NavigationStart, RoutesRecognized} from "@angular/router";
import * as _ from 'lodash';

@Component({
  selector: 'my-app',
  templateUrl: './app.html',
  moduleId: module.id
})
export class AppComponent {
  title: string;

  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.router.events.subscribe((ev: Event) => {
      if (ev instanceof NavigationEnd) {
        this.getTitleOfTheRoute();
      }
    });

  }

  private getTitleOfTheRoute() {
    _.flatten([this.route, this.route.children])
      .map(r => r.data)
      .forEach(dataObservable => {
        dataObservable.subscribe((data: any) => {
          this.title = data.title;
        });
      });
  }

}
