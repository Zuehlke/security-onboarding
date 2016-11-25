import {Injectable} from "@angular/core";

@Injectable()
export class ApiEndpoints {
  private _baseUrl = "//localhost:9080/";

  get login(): string {
    return this.toAbsoluteUrl('login');
  }

  get employees(): string {
    return this.toAbsoluteUrl('employees');
  }
  
  employeeSearch(id: string): string {
	  return this.toAbsoluteUrl('employees/' + id);
  }

  private toAbsoluteUrl(relativeUrl: string) {
    return `${this._baseUrl}${relativeUrl}`;
  }
}
