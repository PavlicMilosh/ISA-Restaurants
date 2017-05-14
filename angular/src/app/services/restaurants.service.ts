import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class RestaurantService
{
  constructor(private http: Http)
  {

  }

  postRestaurant(restaurantName: string, restaurantDescription: string)
  {
    var restaurant =
    {
      name: restaurantName,
      description: restaurantDescription
    }
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/", param, { headers : headers })
      .map(res => res.json());
  }

  getByManager(managerId: number)
  {
    return this.http.get("http://localhost:8080/restaurants/findByManagerId/" + managerId)
      .map(res => res.json());
  }

  updateRestaurant(restaurant: Restaurant)
  {
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/restaurants/" + restaurant.id, param, { headers : headers })
      .map(res => res.json());
  }

  getRestaurants()
  {
    return this.http.get("http://localhost:8080/restaurants")
      .map(res => res.json());
  }

  addRM(restaurantId: number, email: string, password: string, firstName: string, lastName: string)
  {
    var manager =
    {
      id: null,
      email: email,
      password: password,
      firstName: firstName,
      lastName: lastName
    }
    var param = JSON.stringify(manager);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/" + restaurantId + "/addRM", param, { headers : headers })
      .map(res => res.json());
  }
}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
}
