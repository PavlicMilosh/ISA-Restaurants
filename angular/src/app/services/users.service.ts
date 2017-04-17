import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService
{
  constructor(private http: Http)
  {

  }

  addSM(username: string, pass: string, firstName: string, lastName: string, email: string)
  {
    var systemManager =
    {
      username: username,
      password: pass,
      firstName: firstName,
      lastName: lastName,
      email: email
    };
    var param = JSON.stringify(systemManager);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("/register/sysManager", param, { headers : headers })
      .map(res => res.json());
  }
}
