import {Component, OnInit} from '@angular/core';
import { UserService } from "../../services/users.service";
import { RestaurantService } from "../../services/restaurants.service";
import 'fabric';
import {Guard} from "../../utils/Guard";

declare let fabric;

@Component({
  moduleId: module.id,
  selector: 'updateRestaurant',
  templateUrl: './updateRestaurant.component.html',
  styleUrls: ['./updateRestaurant.component.css', '../style/formStyle.css'],
  providers: [UserService, RestaurantService, Guard]
})
export class UpdateRestaurantComponent implements OnInit
{
  private restaurant: Restaurant;
  private canvas;
  private editingDrink: Drink;
  private editingDish: Dish;
  private editingDishType: DishType;

  private rnameEditing: string;
  private rdescEditing: string;
  private rcountryEditing: string;
  private rcityEditing: string;
  private rstreetEditing: string;
  private rnumberEditing: string;
  private editingRegion: RestaurantRegion;
  private currentRegion: RestaurantRegion;
  private seats: number;
  private dishTypes: any;

  constructor(private restaurantService: RestaurantService)
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
      regions: [],
      dishTypes: [],
      address:
        {
          country: "",
          city: "",
          street: "",
          number: ""
        }
    };
    this.editingRegion =
    {
      id: null,
      name: "",
      tables: [],
      color: "#000000"
    };
    this.currentRegion =
    {
      id: null,
      name: "",
      tables: [],
      color: "#5b61ff"
    };

    this.editingDish =
      {
        id: null,
        name: "",
        description: "",
        price: 0,
        dishType: {id: null, name: ""}
      };

    this.editingDrink =
      {
        id: null,
        name: "",
        description: "",
        price: 0,
      };

    this.editingDishType =
      {
        id: null,
        name: ""
      };
  }

  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:900, height:900});
    this.restaurantService.getByManager().subscribe(
      data =>
      {
        this.restaurant = data;
        console.log(this.restaurant);
        for(let region of this.restaurant.regions)
        {
          for(let table of region.tables)
          {
            let text = new fabric.Text(String(table.seats), {
              fontFamily: 'Comic Sans',
              fontSize: 18,
              lockRotation: true
            });
            let rect = new fabric.Rect(
              {
                width: 50,
                height: 50,
                fill: region.color,
                id: table.id
              });
            let group = new fabric.Group([rect, text],
              {
                id: table.id,
                left: table.left,
                top: table.top,
                region: region,
                lockRotation: true
              });
            this.canvas.add(group);

          }
        }
        this.rnameEditing = this.restaurant.name;
        this.rdescEditing = this.restaurant.description;

      }
    );
  }

  addTable()
  {
    let group = {rect:null, text:null};
    let text = new fabric.Text(String(this.seats), {
      fontFamily: 'Comic Sans',
      fontSize: 18,
      lockRotation: true
    });
    let rect = new fabric.Rect(
      {
        fill: this.currentRegion.color,
        width: 50,
        height: 50,
        id: null
      }
    );
    group = new fabric.Group([rect, text],
      {
        left: 100,
        top: 100,
        region: this.currentRegion,
        lockRotation: true
      }
    );
    this.canvas.add(group);
  }

  removeTable()
  {
    let g = this.canvas.getActiveObject();
    this.canvas.remove(g);
  }

  updateRestaurant()
  {
    this.restaurant.tables = [];
    for(let region of this.restaurant.regions)
    {
      region.tables = [];
    }
    for(let group of this.canvas.getObjects())
    {
      console.log(group);
      let t = {
        id: group.id,
        top: group.getTop(),
        left: group.getLeft(),
        angle: 0.0,
        seats: Number(group.item(1).text)
      };
      let region = group.region;
      region.tables.push(t);
      console.log(region);
    }
    console.log(this.restaurant);
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

  selectDishType(dishType: DishType)
  {
    this.editingDishType = dishType;
  }

  newDish()
  {
    this.editingDish =
    {
      id: null,
      name: "",
      description: "",
      price: 0,
      dishType: null
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

  newDishType()
  {
    this.editingDishType =
      {
        id: null,
        name: ""
      }
  }

  manageRestaurant()
  {
    this.restaurant.name = this.rnameEditing;
    this.restaurant.description = this.rdescEditing;
    let address =
      {
        country: this.rcountryEditing,
        city: this.rcityEditing,
        street: this.rstreetEditing,
        number: this.rnumberEditing
      };
    this.restaurant.address = address;

    console.log(this.restaurant);
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
      price: 0,
      dishType: null
    }
  }

  manageDishType()
  {
    if(this.editingDishType.id != null)
    {
      for(let type of this.restaurant.dishTypes)
      {
        if(type.id == this.editingDishType.id)
        {
          type.name = this.editingDishType.name;
        }
      }
    }
    else
    {
      this.restaurant.dishTypes.push(this.editingDishType);
    }
    this.editingDishType =
      {
        id: null,
        name: ""
      }
  }


  setDishType(dt : any)
  {
    this.editingDish.dishType = dt;
  }

  addRegion()
  {
    this.restaurant.regions.push(this.editingRegion);
    this.editingRegion = {id:null, name:"", color: "#0000ff", tables:[]};
  }
}

interface Restaurant
{
  id: number;
  name: string;
  description: string;
  address: Address;
  dishes: Dish[];
  drinks: Drink[];
  dishTypes: DishType[];
  managers: Manager[];
  tables: RestaurantTable[];
  regions: RestaurantRegion[];
}

interface Address
{
  country: string;
  city: string;
  street: string;
  number: string;
}

interface Dish
{
  id: number;
  name: string;
  description: string;
  price: number;
  dishType: DishType;
}

interface DishType
{
  id: number;
  name: string;
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
  top: number;
  left: number;
  angle: number;
  seats: number;
}

interface RestaurantRegion
{
  id: number;
  name: string;
  tables: RestaurantTable[];
  color: string;
}
