/**
 * Created by djuro on 5/15/2017.
 */
import { Component, OnInit } from '@angular/core';
import { UserService } from './../../services/users.service';
import 'fabric';

declare let fabric;

@Component({
  selector: 'table-display',
  templateUrl: './tableDisplay.component.html',
  styleUrls: ['./tableDisplay.component.css'],
  providers: [UserService]
})


export class TableDisplay implements OnInit
{

  private canvas;
  table1:RestaurantTable={id:1, topC:100, leftC:100, angle:50};
  table2:RestaurantTable={id:2, topC:100, leftC:170, angle:50};
  table3:RestaurantTable={id:3, topC:170, leftC:100, angle:50};
  userTables:RestaurantTable[]=[this.table1,this.table2, this.table3];

  constructor(private userService: UserService)
  {

  }

  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:500, height:600});
    //this.canvas.enab

    for(var i=0; i<this.userTables.length; i++)
    {
      var rect = new fabric.Rect(
        {
          left: this.userTables[i].leftC,
          top: this.userTables[i].topC,
          fill: 'blue',
          width: 50,
          height: 50
        }
      );
      this.canvas.add(rect);
    }
  }


}


interface RestaurantTable
{
  id: number;
  topC: number;
  leftC: number;
  angle: number;
}

