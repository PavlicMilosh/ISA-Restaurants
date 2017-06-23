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

  updateProvider(provider: Provider)
  {
    console.log(provider);
    var param = JSON.stringify(provider);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/users/" + provider.id + "/updateProvider", param, { headers: headers })
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

  changePassword(id: number, email: string, pass: string, firstName: string, lastName: string, oldPassword)
  {
    var user =
      {
        id: id,
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
        oldPassword: oldPassword,
      };
    var param = JSON.stringify(user);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/changePassword", param, { headers : headers })
      .map(res => res.json());
  }

  addWorker(firstName: string, lastName: string, email: string, password: string, clothesNumber: number, footwearNumber: number, role: string, dishType: any) {
    let worker =
    {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
      clothesNumber: clothesNumber,
      footwearNumber: footwearNumber,
      dishType: null
    };
    if(dishType != null)
      worker.dishType = dishType;

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

  getById(userId: number)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/" + userId, { headers : headers })
      .map(res => res.json());
  }

  getCurrentUser()
  {
    let userId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/" + userId, { headers : headers })
      .map(res => res.json());
  }

  addSchedule(oneSchedule: Schedule, userId: number)
  {
    console.log(oneSchedule);
    let schedule = [];
    if(oneSchedule.day == 0)
    {
      for(var i = 0; i < 7; i++)
      {
        let s =
        {
          id : oneSchedule.id,
          startTime: oneSchedule.startTime,
          endTime: oneSchedule.endTime,
          day: i,
          regionId: oneSchedule.regionId
        };
        schedule.push(s);
      }
    }
    else
    {
      oneSchedule.day--;
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

  getSchedule()
  {
    let userId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/"+userId+"/getSchedule")
      .map(res => res.json());
  }

  getRestaurant()
  {
    let userId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/"+ userId+"/getRestaurant", { headers : headers })
      .map(res => res.json());
  }

  getRegionId()
  {
    let userId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/"+ userId+"/getWaiterRegionId", { headers : headers })
      .map(res => res.json());
  }

  getAllSchedule()
  {
    let userId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/"+userId+"/getAllSchedule")
      .map(res => res.json());
  }
}

interface Schedule
{
  id: number;
  startTime: string;
  endTime: string;
  day: number;
  regionId: number;
}



interface UserDTO
{
  id: number;
  email: string;
  password:string;
  firstName: string;
  lastName: string;
}

interface Dish
{
  id:number;
  name:string;
  description:string;
  price:number;
}

interface Drink
{
  id:number;
  name: string;
  description: string;
  price: number;
}

interface OrderItem
{
  dish: Dish;
  drink: Drink;
  isDish: Boolean;
  number: number;
  preparing:Boolean;
  finished:Boolean;
}

interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
}

interface Barman
{
  id: number;
  email: string;
  password:string;
  firstName: string;
  lastName: string;
  dishType: DishType;
}

interface DishType
{
  id:number;
  restaurant:Restaurant;
  name:string;
}

interface Restaurant
{
  id : number;
  name : string;
  description : string;
  dishes : Dish[];
  drinks : Drink[];
  tables : RestaurantTable[];
  managers: Manager[];
}

interface Manager
{
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

interface Provider
{
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

