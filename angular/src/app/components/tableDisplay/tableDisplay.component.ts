/**
 * Created by djuro on 5/15/2017.
 */
import {Component, OnDestroy, EventEmitter, Output, Input} from '@angular/core';
import { UserService } from './../../services/users.service';
import { RestaurantService } from './../../services/restaurants.service';
import { ActivatedRoute, Router }       from '@angular/router';
import 'fabric';
import {Subject} from "rxjs";

declare let fabric;

@Component({
  selector: 'table-display',
  templateUrl: './tableDisplay.component.html',
  styleUrls: ['./tableDisplay.component.css'],
  providers: [UserService,RestaurantService]
})


export class TableDisplay
{

  private canvas;
  regionId:number;
  private selectedTableId:number=-1;
  path:string="makeOrder/";
  //table1:RestaurantTable={id:1, topC:100, leftC:100, angle:50};
  //table2:RestaurantTable={id:2, topC:100, leftC:170, angle:50};
  //table3:RestaurantTable={id:3, topC:170, leftC:100, angle:50};
  userTables:RestaurantTable[];

  constructor(private userService: UserService, private restaurantService: RestaurantService,
              private _router: Router)
  {

    this.restaurantService.getRestaurantTables().subscribe
    (
      (data:RestaurantTable[]) => this.userTables = data,
      error => alert(error),
      () => this.getWaiterRegion()

    );

  }

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

