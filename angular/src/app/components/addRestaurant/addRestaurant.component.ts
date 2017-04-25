import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "../../services/restaurants.service";
import 'fabric';

declare let fabric;

@Component({
  moduleId: module.id,
  selector: 'addRestaurant',
  templateUrl: './addRestaurant.component.html',
  styleUrls: ['./addRestaurant.component.css'],
  providers: [RestaurantService]
})
export class AddRestaurantComponent implements OnInit
{
  private restaurant: Restaurant;
  private restaurantName: string;
  private restaurantDescription: string;
  private canvas;

  constructor(private restaurantService: RestaurantService)
  {}

  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
  }

  addTable()
  {
    var rect = new fabric.Rect(
      {
        left: 100,
        top: 100,
        fill: 'blue',
        width: 50,
        height: 50
      }
    );
    this.canvas.add(rect);
  }

  addRestaurant()
  {
    for(let rectangle of this.canvas.getObjects())
    {
      console.log(rectangle);
      /*this.restaurant.tables.push(
        {
        topC: rectangle.getTop(),
        leftC: rectangle.getLeft(),
        angle: rectangle.getAngle()
        });*/
    }
    this.restaurantService.postRestaurant(this.restaurantName, this.restaurantDescription).subscribe(
      data => this.restaurant = data,
      error => alert(error)
    );
    console.log(this.restaurant);
  }
}

interface Restaurant
{
  id : number;
  name : string;
  description : string;
  tables : Table[];
}

interface Table
{
  topC : number;
  leftC : number;
  angle : number;
}
