import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class OrderService
{
  constructor(private http: Http)
  {

  }

  makeOrder(dishes: any[], drinks: any[], user: UserDTO, finished: boolean, price: number )
  {
    var order =
      {
        dishes: dishes,
        drinks: drinks,
        user: user,
        finished: finished,
        price: price
      }
    var param = JSON.stringify(order);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/order/add", param, { headers : headers })
      .map(res => res.json());
  }

  finishedOrder(id:number)
  {
    return this.http.put("http://localhost:8080/order/"+id+"/finish","");
  }

}

interface UserDTO
{
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
}
