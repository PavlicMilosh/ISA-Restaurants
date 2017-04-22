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
}
