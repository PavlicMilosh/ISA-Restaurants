import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import { RestaurantService } from "../../services/restaurants.service";
import {Guard} from "../../utils/Guard";


@Component({
  moduleId: module.id,
  selector: 'registerRM',
  templateUrl: './registerRM.component.html',
  styleUrls: ['./registerRM.component.css'],
  providers: [UserService, RestaurantService, Guard]
})
export class RegisterRMComponent
{
  private email: string;
  private password: string;
  private firstName: string;
  private lastName: string;
  private restaurants: Restaurant[];
  private restaurant: Restaurant;
  private cbText = "selectRestaurant";

  constructor(private restaurantService: RestaurantService)
  {
    this.restaurantService.getRestaurants().subscribe(
      data => {this.restaurants = data;
      console.log(this.restaurants);}
    );
  }

  selectRestaurant(restaurant: Restaurant)
  {
    this.restaurant = restaurant;
    this.cbText = restaurant.name;
  }

  addRM()
  {
    this.restaurantService.addRM(this.restaurant.id, this.email, this.password, this.firstName, this.lastName).subscribe(
      data => console.log(data)
    );
  }


}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
  dishes: Dish[];
  drinks: Drink[];
  managers: Manager[];
  tables: RestaurantTable[];
}

interface Dish
{
  id: number;
  name: string;
  description: string;
  price: number;
}

interface Drink
{
  id: number;
  name: string;
  description: string;
  price: number;
}

interface Manager
{
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

interface RestaurantTable
{
  id: number;
  topC: number;
  leftC: number;
  angle: number;
}
