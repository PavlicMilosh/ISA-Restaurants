import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";

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
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/register/sysManager", param, { headers : headers })
      .map(res => res.json());
  }

  addProvider(email: string, pass: string, firstName: string, lastName: string)
  {
    var provider =
    {
      email: email,
      password: pass,
      firstName: firstName,
      lastName: lastName,
    };
    var param = JSON.stringify(provider);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/register/provider", param, { headers : headers })
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
    headers.append("X-Auth-Token", LoggedUtils.getToken());
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
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/update/barman", param, { headers : headers })
      .map(res => res.json());
  }

  addWorker(firstName: string, lastName: string, email: string, password: string, clothesNumber: number, footwearNumber: number, role: string) {
    let worker =
    {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
      clothesNumber: clothesNumber,
      footwearNumber: footwearNumber
    };

    let path = '';
    let id = LoggedUtils.getId();

    if (role == 'Waiter')
      path = "/addWaiter";
    else if (role == 'Cook')
      path = "/addCook";
    else
      path = "/addBartender";

    let param = JSON.stringify(worker);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/" + id + path, param, {headers: headers})
      .map(res => res.json());
  }


  getUser(email: string, pass: string, firstName: string, lastName: string)
  {
    let user =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    let param = JSON.stringify(user);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/register/barman", param, { headers : headers })
      .map(res => res.json());
  }

  addSchedule(oneSchedule: Schedule, userId: number)
  {
    let schedule = [];
    if(oneSchedule.day == 0)
    {
      for(var i = 0; i < 8; i++)
      {
        let s =
        {
          id : oneSchedule.id,
          startTime: oneSchedule.startTime,
          endTime: oneSchedule.endTime,
          day: i
        };
        schedule.push(s);
      }
    }
    else
    {
      schedule.push(oneSchedule);
    }
    console.log(schedule);
    let param = JSON.stringify(schedule);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/" + userId + "/addSchedule", param, { headers : headers })
      .map(res => res.json());
  }
}

interface Schedule
{
  id: number;
  startTime: string;
  endTime: string;
  day: number;
}

