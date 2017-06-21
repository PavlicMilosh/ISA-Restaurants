/**
 * Created by djuro on 5/30/2017.
 */
import { Component } from '@angular/core';
import { BillService } from "../../services/bill.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'createBill',
  templateUrl: './createBill.component.html',
  styleUrls: ['./createBill.component.css'],
  providers: [BillService]
})

export class CreateBill {

  //bill:BillDTO;

  dish1:Dish={id: 1, name: "dish1", description: "my dish1", price: 0};
  dish2:Dish={id: 2, name: "dish2", description: "my dish2", price: 0};
  dish3:Dish={id: 3, name: "dish3", description: "my dish3", price: 0};
  drink1:Drink={id: 1, name: "drink1", description: "my drink1", price: 0};
  drink2:Drink={id: 2, name: "drink2", description: "my drink2", price: 0};
  drink3:Drink={id: 3, name: "drink3", description: "my drink3", price: 0};

  orderItem1:OrderItem={dish:this.dish1, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem2:OrderItem={dish:null, drink:this.drink1, isDish:false, number:2, preparing:false, finished: false};
  orderItem3:OrderItem={dish:this.dish2, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem4:OrderItem={dish:this.dish3, drink:null, isDish:true, number:2, preparing: false, finished: false};
  orderItem5:OrderItem={dish:null, drink:this.drink2, isDish:false, number:2, preparing: false, finished: false};
  orderItem6:OrderItem={dish:null, drink:this.dish3, isDish:false, number:2, preparing: false, finished: false};

  orderItems1:OrderItem[]=[this.orderItem1,this.orderItem2,this.orderItem3];
  orderItems2:OrderItem[]=[this.orderItem4,this.orderItem5,this.orderItem6];

  order1:Order={orderItems:this.orderItems1,finished:true,price:0};
  order2:Order={orderItems:this.orderItems2,finished:true,price:0};

  bill:BillDTO={id:null,orders:[this.order1,this.order2],price:0};

  constructor(private billService: BillService, private route: ActivatedRoute)
  {

    this.billService.getBill(this.route.snapshot.params['p1']).subscribe
    (
      (data:BillDTO) => this.bill = data,
      error => alert(error)
    );

  }


}

interface BillDTO
{
  id:number;
  orders: Order[];
  price: number;
}

interface Order
{
  orderItems: OrderItem[];
  finished: Boolean;
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


