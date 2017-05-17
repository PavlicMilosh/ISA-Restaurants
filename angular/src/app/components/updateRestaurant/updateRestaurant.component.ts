import {Component, OnInit} from '@angular/core';
import { UserService } from "../../services/users.service";
import { RestaurantService } from "../../services/restaurants.service";
import 'fabric';

declare let fabric;

@Component({
  moduleId: module.id,
  selector: 'updateRestaurant',
  templateUrl: './updateRestaurant.component.html',
  styleUrls: ['./updateRestaurant.component.css'],
  providers: [UserService, RestaurantService]
})
export class UpdateRestaurantComponent implements OnInit
{
  private restaurant: Restaurant;
  private canvas;
  private editingDrink: Drink;
  private editingDish: Dish;
  private rnameEditing: string;
  private rdescEditing: string;
  private regionIndex: number;
  private editingRegion: RestaurantRegion;
  private currentRegion: RestaurantRegion;

  constructor(private userService: UserService, private restaurantService: RestaurantService)
  {
    this.restaurant =
    {
      id: null,
      name: "",
      description: "",
      dishes: [],
      drinks: [],
      tables: [],
      managers: [],
      regions: []
    };
    this.editingRegion =
    {
      id: null,
      name: "",
      tables: [],
      color: "blue"
    }
    this.currentRegion =
    {
      id: null,
      name: "",
      tables: [],
      color: "blue"
    }
    this.newDish();
    this.newDrink();
    this.restaurantService.getByManager(0).subscribe(
      data =>  {this.restaurant = data; console.log(this.restaurant);}
    );
  }

  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:500, height:600});
  }

  addTable()
  {
    console.log(this.regionIndex);
    var rect = new fabric.Rect(
      {
        left: 100,
        top: 100,
        fill: this.currentRegion.color,
        width: 50,
        height: 50,
        region: this.currentRegion
      }
    );
    this.canvas.add(rect);
  }

  removeTable()
  {
    this.canvas.getActiveObject().remove();
  }

  updateRestaurant()
  {
    for(let rectangle of this.canvas.getObjects())
    {
      this.restaurant.tables.push(
        {
          id: null,
          topC: rectangle.getTop(),
          leftC: rectangle.getLeft(),
          angle: rectangle.getAngle(),
          region: rectangle.region
        });
    }
    this.restaurantService.updateRestaurant(this.restaurant).subscribe(
      data => this.restaurant = data
    );
  }

  selectRegion(region: RestaurantRegion)
  {
    this.currentRegion = region;
  }

  selectDrink(drink: Drink)
  {
    this.editingDrink = drink;
  }

  selectDish(dish: Dish)
  {
    this.editingDish = dish;
  }

  newDish()
  {
    this.editingDish =
    {
      id: null,
      name: "",
      description: "",
      price: 0,
    }
  }

  newDrink()
  {
    this.editingDrink =
    {
      id: null,
      name: "",
      description: "",
      price: 0,
    }
  }

  manageRestaurant()
  {
    this.restaurant.name = this.rnameEditing;
    this.restaurant.description = this.rdescEditing;
  }

  manageDrink()
  {
    if(this.editingDrink.id != null)
    {
      for(let drink of this.restaurant.drinks)
      {
        if(drink.id == this.editingDrink.id)
        {
          drink.name = this.editingDrink.name;
          drink.description = this.editingDrink.description;
          drink.price = this.editingDrink.price;
        }
      }
    }
    else
    {
      this.restaurant.drinks.push(this.editingDrink);
      this.editingDrink =
      {
        id: null,
        name: "",
        description: "",
        price: 0
      }
    }
  }

  manageDish()
  {
    if(this.editingDish.id != null)
    {
      for(let dish of this.restaurant.dishes)
      {
        if(dish.id == this.editingDish.id)
        {
          dish.name = this.editingDish.name;
          dish.description = this.editingDish.description;
          dish.price = this.editingDish.price;
        }
      }
    }
    else
    {
      this.restaurant.dishes.push(this.editingDish);

    }
    this.editingDish =
    {
      id: null,
      name: "",
      description: "",
      price: 0
    }
  }

  addRegion()
  {
    this.restaurant.regions.push(this.editingRegion);
    this.editingRegion = {id:null, name:"", color: "blue", tables:[]};
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
  regions: RestaurantRegion[];
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
  region: RestaurantRegion;
}

interface RestaurantRegion
{
  id: number;
  name: string;
  tables: RestaurantTable[];
  color: string;
}
