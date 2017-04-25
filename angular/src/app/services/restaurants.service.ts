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
      restaurantName: restaurantName,
      restaurantDescription: restaurantDescription
    }
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("/restaurants/add", param, { headers : headers })
      .map(res => res.json());
  }

  updateRestaurant(restaurant: Restaurant)
  {
    var param = JSON.stringify(restaurant);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("/restaurants/${restaurant.id}", param, { headers : headers })
      .map(res => res.json());
  }

  getRestaurants()
  {
    return this.http.get("/restaurants")
      .map(res => res.json());
  }
}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
}
