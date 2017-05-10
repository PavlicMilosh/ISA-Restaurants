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

  updateBarman(id: number, email: string, pass: string, firstName: string, lastName: string)
  {
    var user =
      {
        id:id,
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    var param = JSON.stringify(user);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/update/barman", param, { headers : headers })
      .map(res => res.json());
  }

  changePassword(id: number, email: string, pass: string, firstName: string, lastName: string)
  {
    var user =
      {
        id: id,
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    var param = JSON.stringify(user);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/update/barman", param, { headers : headers })
      .map(res => res.json());
  }

  addWorker(firstName: string, lastName: string, email: string, password: string, clothesNumber: number, footwearNumber: number, role: string)
  {
    var worker =
    {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
      clothesNumber: clothesNumber,
      footwearNumber: footwearNumber
    }
    var path = '';
    if(role == 'Waiter')
      path = "/addWaiter";
    else if(role == 'Cook')
      path = "/addCook";
    else
      path = "/addBartender";
    var param = JSON.stringify(worker);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/" + 1 + path, param, { headers : headers })
      .map(res => res.json());
  }
}

