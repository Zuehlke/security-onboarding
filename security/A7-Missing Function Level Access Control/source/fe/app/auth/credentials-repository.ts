import {Injectable} from "@angular/core";
import {UserCredentials} from "./userCredentials";
@Injectable()
export class CredentialsRepository {
  private CREDENTIALS_HASH = "CREDENTIALS_HASH";

  saveAndGet(user: UserCredentials): string {
    const hashedCredentials = this.hashCredentials(user);
    localStorage.setItem(this.CREDENTIALS_HASH, hashedCredentials);

    return hashedCredentials;
  }

  get(): string {
    return localStorage.getItem(this.CREDENTIALS_HASH);
  }

  private hashCredentials(user: UserCredentials): string {
    return btoa(user.name + ':' + user.password);
  }
}
