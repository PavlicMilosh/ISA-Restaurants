import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "../../services/restaurants.service";


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

  constructor(private restaurantService: RestaurantService)
  {}

  ngOnInit()
  {
  }

  addRestaurant()
  {
    this.restaurantService.postRestaurant(this.restaurantName, this.restaurantDescription).subscribe(
      data => this.restaurant = data,
      error => alert(error)
    );
  }
}

interface Restaurant
{
  id : number;
  name : string;
  description : string;
}


