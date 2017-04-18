import { Component } from '@angular/core';
import { RestaurantService } from "../../services/restaurants.service";


@Component({
  moduleId: module.id,
  selector: 'addRestaurant',
  templateUrl: './addRestaurant.component.html',
  styleUrls: ['./addRestaurant.component.css'],
  providers: [RestaurantService]
})
export class AddRestaurantComponent
{
  restaurant: Restaurant;
  restaurantName: string;
  restaurantDescription: string;

  constructor(private restaurantService: RestaurantService)
  {}

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
  id: number;
  name: string;
  description: string;
}
