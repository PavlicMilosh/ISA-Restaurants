import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class OrderService
{
  constructor(private http: Http)
  {

  }

  makeOrder(orderItems:OrderItem[], barman: Barman, finished: boolean, price: number, table: RestaurantTable)
  {
    var date=Date.now();
    var order =
      {
        orderItems: orderItems,
        barman: barman,
        finished: finished,
        price: price,
        orderTable: table,
        orderTime: date
      }
    var param = JSON.stringify(order);
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/order/add/1", param, { headers : headers })
      .map(res => res.json());
  }

  finishedOrder(id:number)
  {
    return this.http.put("http://localhost:8080/order/"+id+"/finish","");
  }

  getAllOrders(id:number)
  {
    return this.http.get("http://localhost:8080/order/"+id+"/getOrders")
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
