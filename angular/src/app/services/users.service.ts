import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService
{
  constructor(private http: Http)
  {

  }

  addSM(email: string, pass: string, firstName: string, lastName: string)
  {
    var systemManager =
    {
      email: email,
      password: pass,
      firstName: firstName,
      lastName: lastName,
    };
    var param = JSON.stringify(systemManager);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/register/sysManager", param, { headers : headers })
      .map(res => res.json());
  }
}

interface Restaurant
{

}
