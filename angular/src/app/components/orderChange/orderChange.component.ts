import { Component, OnInit, OnDestroy } from '@angular/core';
import { OrderService } from "../../services/order.service";
import { RestaurantService } from "../../services/restaurants.service";
import { UserService } from "../../services/users.service";
import { ActivatedRoute, Router } from '@angular/router';
import {Observable, Subscription} from "rxjs";

@Component({
  moduleId: module.id,
  selector: 'orderChange',
  templateUrl: './orderChange.component.html',
  styleUrls: ['./orderChange.component.css'],
  providers: [OrderService,RestaurantService,UserService]
})

export class OrderChange implements OnInit, OnDestroy
{

  order: Order;

  orderItems:OrderItem[]=[];

  orderDishes:Dish[]=[];
  orderDrinks:Drink[]=[];


  barman: Barman;
  finished: boolean;
  price: number;



  dishes:Dish[]=[];
  drinks:Drink[]=[];

  table1:RestaurantTable={id:null, top:100, left:100, angle:50};

  restaurantOrders:RestaurantOrders={id:null, dishes:[],drinks:[],orders:[],restaurantId:3};

  restaurant: Restaurant;

  countDrinks:number[]=[];
  countDishes:number[]=[];
  selectedTableId:number;

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  orderId:number;

  orderForChanging:Order;

  constructor(private orderService: OrderService, private userService: UserService,
              private restaurantService: RestaurantService, private route: ActivatedRoute, private _router: Router )
  {
    /*
     this.userService.getRestaurant().subscribe(
     data => this.restaurant = data,
     error => alert(error)
     );
     */
    console.log(this.route.snapshot.params['p1']);
    this.selectedTableId=this.route.snapshot.params['p1'];


    this.userService.getRestaurant().subscribe(
      (data:Restaurant) => this.restaurant = data,
      error => alert(error),
    );


  }

  ngOnInit() {
    this.orderService.getOrderForChanging(this.route.snapshot.params['p1']).subscribe(
      (data:Order) => this.orderForChanging = data,
      error => alert(error),
      ()=>this.initOrder(),
    );
  }

  initOrder()
  {
    this.orderItems=this.orderForChanging.orderItems;
    this.orderId=this.orderForChanging.id;
  }

  ngOnDestroy()
  {
    if (this.postsSubscription) {
      this.postsSubscription.unsubscribe();
    }
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }


  changeOrder()
  {
    this.orderService.changeOrder(this.orderItems,false,0,this.restaurant.id,this.selectedTableId, this.orderForChanging.id).subscribe(
      data => this.order = data,
      error => alert(error),
      ()=>this.removeItems()
    );
  }

  removeItems()
  {
    this._router.navigate(['tableDisplay']);
    this.orderItems=[];
  }

  addDish(dish:Dish,index:number)
  {
    console.log(this.countDishes[index]);
    this.orderDishes.push(dish);
    this.orderItems.push({dish:dish,drink:null,isDish:true,number:this.countDishes[index],preparing: false, finished: false});
  }

  addDrink(drink:Drink, index:number)
  {
    console.log(this.countDrinks[index]);
    this.orderDrinks.push(drink);
    this.orderItems.push({dish:null,drink:drink,isDish:false, number:this.countDrinks[index],preparing: false, finished: false});
  }

  removeItem(index:number)
  {
    this.orderItems.splice(index,1);
  }

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

interface Dish
{
  id: number;
  name:string;
  description:string;
  price:number;
}

interface Drink
{
  id: number;
  name: string;
  description: string;
  price: number;
}

interface Restaurant
{
  id : number;
  name : string;
  description : string;
  dishes : Dish[];
  drinks : Drink[];
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

interface Order
{
  id:number;
  orderItems: OrderItem[];
  barmen:Barman;
  finished: Boolean;
  price: number;
}

interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
}

interface Manager
{
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

interface  RestaurantOrders
{
  id:number;
  restaurantId:number;
  dishes: Dish[];
  drinks: Drink[];
  orders: Order[];
}

interface DishType
{
  id:number;
  restaurant:Restaurant;
  name:string;
}

interface Cook
{
  id: number;
  email: string;
  password:string;
  firstName: string;
  lastName: string;
  schedule:WorkSchedule[];
}

interface Bartender
{
  id: number;
  email: string;
  password:string;
  firstName: string;
  lastName: string;
  schedule: WorkSchedule[];
}

interface WorkSchedule
{
  id:number;
  startDate:Date;
  endTime:Date;
  day:number;
}

interface Region
{
  id:number;
  name:string;
  tables:RestaurantTable[];
}


/**
 * Created by djuro on 6/22/2017.
 */
