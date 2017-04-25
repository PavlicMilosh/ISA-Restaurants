import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import { RestaurantService } from "../../services/restaurants.service";


@Component({
  moduleId: module.id,
  selector: 'updateRestaurant',
  templateUrl: './updateRestaurant.component.html',
  styleUrls: ['./updateRestaurant.component.css'],
  providers: [UserService, RestaurantService]
})
export class UpdateRestaurantComponent
{
  restaurants: Restaurant[];
  selectedRestaurant: Restaurant;


  constructor(private userService: UserService, private restaurantService: RestaurantService)
  {
    this.restaurantService.getRestaurants().subscribe(
      data => this.restaurants = data
    );
  }

  updateRestaurant()
  {
    this.restaurantService.updateRestaurant(this.selectedRestaurant).subscribe(
      data => this.selectedRestaurant = data
    );
  }

  changeRestaurant(index: number)
  {
    this.selectedRestaurant = this.restaurants[index];
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
