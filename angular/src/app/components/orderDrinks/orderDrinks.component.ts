
import { Component } from '@angular/core';
import { OrderService } from "../../services/order.service";

@Component({
  moduleId: module.id,
  selector: 'orderDrinks',
  templateUrl: './orderDrinks.component.html',
  styleUrls: ['./orderDrinks.component.css'],
  providers: [OrderService]
})

export class OrderDrinks {
  userDTO: UserDTO;

  dish1:Dish={id: 1, name: "dish1", description: "my dish1", price: 0};
  dish2:Dish={id: 2, name: "dish2", description: "my dish2", price: 0};
  dish3:Dish={id: 3, name: "dish3", description: "my dish3", price: 0};
  drink1:Drink={id: 1, name: "drink1", description: "my drink1", price: 0};
  drink2:Drink={id: 2, name: "drink2", description: "my drink2", price: 0};
  drink3:Drink={id: 3, name: "drink3", description: "my drink3", price: 0};

  orderItem1:OrderItem={id:null, dish:this.dish1, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem2:OrderItem={id:null, dish:null, drink:this.drink1, isDish:false, number:2, preparing: false, finished: false};
  orderItem3:OrderItem={id:null, dish:this.dish2, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem4:OrderItem={id:null, dish:this.dish3, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem5:OrderItem={id:null, dish:null, drink:this.drink2, isDish:false, number:2, preparing: false, finished: false};
  orderItem6:OrderItem={id:null, dish:null, drink:this.dish3, isDish:false, number:2, preparing: false, finished: false};

  orderItems1:OrderItem[]=[this.orderItem1,this.orderItem2,this.orderItem3];
  orderItems2:OrderItem[]=[this.orderItem4,this.orderItem5,this.orderItem6];

  dishes:Dish[]=[this.dish1,this.dish2,this.dish3];
  drinks:Drink[]=[this.drink1, this.drink2, this.drink3];
  //order1:Order={id: 1,orderItems:this.orderItems1, user: this.userDTO, finished: false, price:100};
  //order2:Order={id: 2,orderItems:this.orderItems2, user: this.userDTO, finished: false, price:100};
  orders:Order[];

  selectOrders:Order[]=[];

  selectItems:OrderItem[]=[];


  constructor(private orderService: OrderService) {

    this.orderService.getAllOrders(1).subscribe
    (
      (data: Order[]) => this.orders = data,
      error => alert(error)
    );

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
    //this.orderService.preparingOrderItem(this.orders[idx1].orderItems[item].id);
    this.selectItems.push(this.orders[idx1].orderItems[item]);
    this.orders[idx1].orderItems.splice(item,1);
  }

  finishedItem( index:number )
  {
    //this.finishedItem(this.selectItems[index].id);
    this.selectItems.splice(index,1);
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




