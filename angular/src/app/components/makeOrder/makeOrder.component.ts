import { Component, OnInit, OnDestroy } from '@angular/core';
import { OrderService } from "../../services/order.service";
import { RestaurantService } from "../../services/restaurants.service";
import { UserService } from "../../services/users.service";
import { ActivatedRoute }    from '@angular/router';
import {Observable, Subscription} from "rxjs";

@Component({
  moduleId: module.id,
  selector: 'makeOrder',
  templateUrl: './makeOrder.component.html',
  styleUrls: ['./makeOrder.component.css'],
  providers: [OrderService,RestaurantService,UserService]
})

export class MakeOrder implements OnInit, OnDestroy
{

  order: Order;

  orderItems:OrderItem[]=[];

  orderDishes:Dish[]=[];
  orderDrinks:Drink[]=[];


  barman: Barman;
  finished: boolean;
  price: number;

  dish1:Dish={id:null, name: "dish1", description: "my dish1", price: 0};
  dish2:Dish={id:null,name: "dish2", description: "my dish2", price: 0};
  dish3:Dish={id:null, name: "dish3", description: "my dish3", price: 0};
  drink1:Drink={id:null, name: "drink1", description: "my drink1", price: 0};
  drink2:Drink={id:null, name: "drink2", description: "my drink2", price: 0};
  drink3:Drink={id:null, name: "drink3", description: "my drink3", price: 0};

  dishes:Dish[]=[this.dish1,this.dish2,this.dish3];
  drinks:Drink[]=[this.drink1,this.drink2,this.drink3];

  table1:RestaurantTable={id:null, top:100, left:100, angle:50};

  restaurantOrders:RestaurantOrders={id:null, dishes:[],drinks:[],orders:[],restaurantId:3};

  restaurant: Restaurant={id:1, name:"stefan", description:"stefan", dishes:this.dishes,
    drinks:this.drinks};

  countDrinks:number[]=[];
  countDishes:number[]=[];

  dishType:DishType={id:null,restaurant:this.restaurant,name:"kuvana jela"};


  selectedTableId:number;

  postsSubscription:Subscription;
  timerSubscription:Subscription;




  constructor(private orderService: OrderService, private userService: UserService,
              private restaurantService: RestaurantService, private route: ActivatedRoute )
  {
    /*
     this.userService.getRestaurant().subscribe(
     data => this.restaurant = data,
     error => alert(error)
     );
     */
    console.log(this.route.snapshot.params['p1']);
    this.selectedTableId=this.route.snapshot.params['p1'];

    this.refreshData();

  }

  private refreshData(): void {
    this.postsSubscription=this.userService.getRestaurant().subscribe(
      data => {
        this.restaurant = data;
        this.subscribeToData();
      });
  }

  private subscribeToData(): void {
    this.timerSubscription=Observable.timer(5000).first().subscribe(() => this.refreshData());
  }



  ngOnInit() {
    /*
     this.subscription = this.dataService.getData().subscribe(
     id => { this.selectedTableId = id; },
     ()=>this.aaa()
     );
     */
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


  makeOrder()
  {

    this.orderService.makeOrder(this.orderItems,false,0,this.restaurant.id,this.selectedTableId).subscribe(
      data => this.order = data,
      error => alert(error),
      ()=>this.removeItems()
    );
  }

  removeItems()
  {
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


