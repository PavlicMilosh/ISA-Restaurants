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

  addWorker(firstName: string, lastName: string, email: string, password: string, clothesNumber: number, footwearNumber: number, role: string) {
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
    if (role == 'Waiter')
      path = "/addWaiter";
    else if (role == 'Cook')
      path = "/addCook";
    else
      path = "/addBartender";
    var param = JSON.stringify(worker);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/" + 1 + path, param, {headers: headers})
      .map(res => res.json());
  }

  getUser(email: string, pass: string, firstName: string, lastName: string){
    var user =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    var param = JSON.stringify(user);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/register/barman", param, { headers : headers })
      .map(res => res.json());
  }

  addSchedule(oneSchedule: Schedule, userId: number)
  {
    var schedule = [];
    if(oneSchedule.day == 0)
    {
      for(var i = 0; i < 8; i++)
      {
        var s =
        {
          id : oneSchedule.id,
          startTime: oneSchedule.startTime,
          endTime: oneSchedule.endTime,
          day: i
        }
        schedule.push(s);
      }
    }
    else
    {
      schedule.push(oneSchedule);
    }
    var param = JSON.stringify(schedule);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/users/" + userId + "/addSchedule", param, { headers : headers })
      .map(res => res.json());
  }

  getSchedule(userId:number, role: string)
  {
    var path = '';
    if (role == 'Waiter')
      path = "/waiter";
    else if (role == 'Cook')
      path = "/cook";
    else
      path = "/bartender";

    return this.http.get("http://localhost:8080/users/"+path+"/getSchedule")
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

