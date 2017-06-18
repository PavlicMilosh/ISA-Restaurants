import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";

@Injectable()
export class OrderService
{
  constructor(private http: Http)
  {

  }

  makeOrder(orderItems:OrderItem[], finished: boolean, price: number,id:number, tableId: number)
  {
    var date=Date.now();
    var order =
      {
        orderItems: orderItems,
        finished: finished,
        price: price,
        orderTime: date
      }
    var param = JSON.stringify(order);
    let waiterId=LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/order/"+waiterId+"/add/"+id+"/"+tableId, param, { headers : headers })
      .map(res => res.json());
  }

  finishedOrder(id:number)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put("http://localhost:8080/order/"+id+"/finish", { headers : headers });
  }

  getAllOrders()
  {
    let userId=LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/users/"+userId+"/getRestaurantOrders", { headers : headers })
      .map(res => res.json());
  }

  preparingOrderItem(itemId:number)
  {
    return this.http.get("http://localhost:8080/order/"+itemId+"/preparing")
      .map(res => res.json());
  }

  finishedOrderItem(itemId:number)
  {
    return this.http.get("http://localhost:8080/order/"+itemId+"/finished")
      .map(res => res.json());
  }

  addDishType(dishType:DishType)
  {
    var param = JSON.stringify(dishType);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/restaurants/addDishType", param, { headers : headers })
      .map(res => res.json());
  }

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
