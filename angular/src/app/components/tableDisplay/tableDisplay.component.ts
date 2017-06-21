/**
 * Created by djuro on 5/15/2017.
 */
import {Component, OnInit, OnDestroy, EventEmitter, Output, Input} from '@angular/core';
import { UserService } from './../../services/users.service';
import { RestaurantService } from './../../services/restaurants.service';
import { OrderService } from './../../services/order.service';
import { ActivatedRoute, Router }       from '@angular/router';
import {Observable, Subscription} from "rxjs";
import 'fabric';
import {Subject} from "rxjs";

declare let fabric;

@Component({
  selector: 'table-display',
  templateUrl: './tableDisplay.component.html',
  styleUrls: ['./tableDisplay.component.css'],
  providers: [UserService,RestaurantService,OrderService]
})


export class TableDisplay implements OnInit, OnDestroy
{

  private canvas;
  regionId:number;
  private selectedTableId:number=-1;
  userTables:RestaurantTable[];
  orderForDeliver:Order[]=[];
  orderDelivered:Order;
  tablesId:number[]=[];

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  postsSubscription1:Subscription;
  timerSubscription1:Subscription;


  constructor(private userService: UserService, private restaurantService: RestaurantService,
              private _router: Router, private orderService:OrderService)
  {

    this.restaurantService.getRestaurantTables().subscribe
    (
      (data:RestaurantTable[]) => this.userTables = data,
      error => alert(error),
      () => this.getWaiterRegion()
    );

  }

  ngOnInit() {
    this.refreshData();
  }

  private refreshData(): void {
    this.postsSubscription=this.orderService.getOrdersForDeliver().subscribe(
      data => {
        this.orderForDeliver = data;
        this.subscribeToData();
      });
  }

  private subscribeToData(): void {
    console.log(this.orderForDeliver.length);
    this.timerSubscription=Observable.timer(5000).first().subscribe(() => this.refreshData());
  }

  /*
  private refreshTables(): void {
    this.postsSubscription1=this.orderService.getTablesForCreatingBills().subscribe(
      data => {
        this.tablesId = data;
        this.subscribeToData1();
      });
  }

  private subscribeToData1(): void {
    for(var i=0; i<this.tablesId.length;i++)
    {
      this.canvas.
    }
    this.timerSubscription=Observable.timer(5000).first().subscribe(() => this.refreshData());
  }
  */

  getWaiterRegion()
  {
    this.userService.getRegionId().subscribe
    (
      (data:number) => this.regionId = data,
      error => alert(error),
      () => this.init()
    );
  }

  init()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:900, height:900});
    //this.canvas.on('object:selected', this.onObjectSelected, false);

    for(var i=0; i<this.userTables.length; i++)
    {
      var color='blue';
      if(this.userTables[i].regionId==this.regionId)
      {
        color='red';
      }
      var rect = new fabric.Rect(
        {
          left: this.userTables[i].left,
          top: this.userTables[i].top,
          fill: color,
          width: 50,
          height: 50,
          id: this.userTables[i].id,
          fillText: this.userTables[i].id
        }
      );
      this.canvas.add(rect);
    }
  }

  makeOrder()
  {
    let g = this.canvas.getActiveObject();
    this.selectedTableId=g.get('id');
    this._router.navigate(['makeOrder/'+ this.selectedTableId]);
  }

  createBill()
  {
    let g = this.canvas.getActiveObject();
    this.selectedTableId=g.get('id');
    this._router.navigate(['createBill/'+ this.selectedTableId]);
  }

  deliver(index:number)
  {
    this.orderService.orderDelivered(this.orderForDeliver[index].id).subscribe
    (
      (data:Order) => this.orderDelivered = data,
      error => alert(error),
    );
    this.orderForDeliver.splice(index,1);
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


interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
  regionId: number;
  seats: number;
  occupied: Boolean;
  regionColor: string
  version: number;
  restaurantDTO: any;
}

interface Order
{
  id:number,
  orderItems:OrderItem[];
  finished:Boolean,
  price:number
  tableId:number;
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
