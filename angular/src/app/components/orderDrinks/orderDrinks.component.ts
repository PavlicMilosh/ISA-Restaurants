import {Observable, Subscription} from "rxjs";
import { Component, OnDestroy, OnInit } from '@angular/core';
import { OrderService } from "../../services/order.service";

@Component({
  moduleId: module.id,
  selector: 'orderDrinks',
  templateUrl: './orderDrinks.component.html',
  styleUrls: ['./orderDrinks.component.css'],
  providers: [OrderService]
})

export class OrderDrinks implements OnDestroy, OnInit
{
  userDTO: UserDTO;

  dish1:Dish={id: 1, name: "dish1", description: "my dish1", price: 0};
  dish2:Dish={id: 2, name: "dish2", description: "my dish2", price: 0};
  dish3:Dish={id: 3, name: "dish3", description: "my dish3", price: 0};
  drink1:Drink={id: 1, name: "drink1", description: "my drink1", price: 0};
  drink2:Drink={id: 2, name: "drink2", description: "my drink2", price: 0};
  drink3:Drink={id: 3, name: "drink3", description: "my drink3", price: 0};


  orderItems1:OrderItem[]=[];
  orderItems2:OrderItem[]=[];

  dishes:Dish[]=[this.dish1,this.dish2,this.dish3];
  drinks:Drink[]=[this.drink1, this.drink2, this.drink3];
  //order1:Order={id: 1,orderItems:this.orderItems1, user: this.userDTO, finished: false, price:100};
  //order2:Order={id: 2,orderItems:this.orderItems2, user: this.userDTO, finished: false, price:100};
  orders:Order[];

  selectOrders:Order[]=[];

  selectItems:OrderItem[]=[];
  selectOrderId:number[]=[];

  canPreparing:Boolean;

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  finishedOrderItem:OrderItem;


  constructor(private orderService: OrderService) {

    /*
     this.orderService.getAllOrders().subscribe
     (
     (data: Order[]) => this.orders = data,
     error => alert(error)
     );
     */
    this.refreshData();

  }

  ngOnInit() {
    this.orderService.getPreparingOrderItems().subscribe
    (
      (data: OrderItem[]) => {this.selectItems= data; console.log(data); },
      error => alert(error),
      () => this.getPreparedOrdersId()
    );
  }

  getPreparedOrdersId()
  {
    this.orderService.getPreparedOrderId().subscribe
    (
      (data:number[]) => this.selectOrderId=data,
      error => alert(error)
    );
  }

  private refreshData(): void {
    this.postsSubscription=this.orderService.getAllOrders().subscribe(
      data => {
        this.orders = data;
        this.subscribeToData();
      });
  }

  private subscribeToData(): void {
    this.timerSubscription=Observable.timer(5000).first().subscribe(() => this.refreshData());
  }

  selectOrder(order:number, item:number)
  {
    var idx1=0;

    for(var i=0; i<this.orders.length;i++)
    {
      if(this.orders[i].id==order)
      {
        idx1=i;
        break
      }
    }
    this.orders[idx1].orderItems[item].preparing=true;
    this.orderService.preparingOrderItem(this.orders[idx1].orderItems[item].id,this.orders[idx1].orderItems[item].orderId,this.orders[idx1].orderItems[item].orderVersion).subscribe
    (
      (data: Boolean) => this.canPreparing = data,
      error => alert(error),
      ()=>this.preparing(idx1,item)
    );
    //this.selectItems.push(this.orders[idx1].orderItems[item]);
    //this.orders[idx1].orderItems.splice(item,1);

  }

  preparing(idx1:number,item:number)
  {
    if(this.canPreparing)
    {
      this.selectItems.push(this.orders[idx1].orderItems[item]);
      this.selectOrderId.push(this.orders[idx1].id)
      this.orders[idx1].orderItems.splice(item,1);
    }
  }



  finishedItem( index:number )
  {
    //this.finishedItem(this.selectItems[index].id);
    console.log(this.selectItems[index].id);
    console.log(this.selectOrderId[index]);
    this.orderService.finishedOrderItem(this.selectItems[index].id,this.selectOrderId[index]).subscribe
    (
      (data:OrderItem) => this.finishedOrderItem = data,
      error => alert(error),
    );
    this.selectItems.splice(index,1);
    this.selectOrderId.splice(index,1);
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
  id: number;
  name: string;
  description: string;
  price: number;
}

interface OrderItem
{
  id:number;
  dish:Dish;
  drink:Drink;
  isDish:Boolean;
  number:number;
  preparing:Boolean;
  finished:Boolean;
  orderId:number;
  orderVersion:number;
}

interface Order
{
  id:number,
  orderItems:OrderItem[];
  barmen:Barman,
  finished:Boolean,
  price:number
}

interface RestaurantOrders
{
  id:number;
  restaurantId:number;
  dishes: Dish[];
  drinks: Drink[];
  orders: Order[];
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




