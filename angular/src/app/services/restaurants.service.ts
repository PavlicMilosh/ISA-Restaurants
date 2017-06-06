import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";

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
    };
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/", param, { headers : headers })
      .map(res => res.json());
  }

  getByManager()
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/restaurants/findByManagerId/" + managerId, { headers : headers })
      .map(res => res.json());
  }

  updateRestaurant(restaurant: Restaurant)
  {
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/restaurants/" + restaurant.id, param, { headers : headers })
      .map(res => res.json());
  }

  getRestaurants()
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/restaurants", { headers : headers })
      .map(res => res.json());
  }


  getRestaurantsForGuest()
  {
    let headers = new Headers();
    let guestId = LoggedUtils.getId();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/restaurants/" + guestId + "/getRestaurants", { headers : headers })
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
    };
    var param = JSON.stringify(manager);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/" + restaurantId + "/addRM", param, { headers : headers })
      .map(res => res.json());
  }

  getWorkersByRMId()
  {
    let managerId = LoggedUtils.getId();
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/restaurants/getWorkersByRMId/" + managerId, { headers : headers })
      .map(res => res.json());
  }

  searchRestaurants(searchParams: string)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/restaurants/searchRestaurants", searchParams, {headers: headers})
      .map(res => res.json());
  }

  getRegions(restaurantId: number)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/restaurants/" + restaurantId + "/getRegions", {headers: headers})
      .map(res => res.json());
  }

}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
}
