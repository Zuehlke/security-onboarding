import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptionsArgs, Response} from "@angular/http";
import {Observable} from "rxjs";
import * as _ from "lodash";
import {CredentialsRepository} from "../auth/credentials-repository";

@Injectable()
export class HttpClient {
  constructor(private http: Http, private credentialsRepository: CredentialsRepository) {
  }

  private createAuthorizationHeader(): Headers {
    return new Headers({
      Authorization: `Basic ${this.credentialsRepository.get()}`
    });
  }

  private createRequestOptionsWithAuthHeaders(): RequestOptionsArgs {
    return {
      headers: this.createAuthorizationHeader()
    };
  }

  get(url: string, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.get(url, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))
  }

  /**
   * Performs a request with `post` http method.
   */
  post(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.post(url, body, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))

  };

  /**
   * Performs a request with `put` http method.
   */
  put(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.put(url, body, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))
  }

  /**
   * Performs a request with `delete` http method.
   */
  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.delete(url, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))
  }

  /**
   * Performs a request with `patch` http method.
   */
  patch(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.patch(url, body, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))
  }

  /**
   * Performs a request with `head` http method.
   */
  head(url: string, options?: RequestOptionsArgs): Observable<Response> {
    return this.http.head(url, _.extend(options || {}, this.createRequestOptionsWithAuthHeaders()))

  }
}
