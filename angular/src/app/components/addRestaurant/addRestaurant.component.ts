import { Component } from '@angular/core';


@Component({
  moduleId: module.id,
  selector: 'addRestaurant',
  templateUrl: 'addRestaurant.component.html'
})
export class addRestaurantComponent
{
  restaurant: Restaurant

  constructor(private restaurantService: RestaurantService)
  {}

  addRestaurant(restaurant: Restaurant)
  {

  }
}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
}
