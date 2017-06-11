import {Component, Input, Output, EventEmitter} from '@angular/core';
import {GuestService} from "../../../services/guest.service";


@Component({
  moduleId: module.id,
  selector: 'guestPreorder',
  templateUrl: 'guestPreorder.component.html',
  styleUrls: ['guestPreorder.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestPreorderComponent
{

  @Input() restaurant: Restaurant;
  @Output() notifyDrinks: EventEmitter<Drink[]> = new EventEmitter<Drink[]>();
  @Output() notifyDishes: EventEmitter<Dish[]> = new EventEmitter<Dish[]>();

  private drinkOrders: Drink[];
  private dishOrders: Dish[];


  constructor(private guestService: GuestService)
  {
    this.restaurant = {id: null, name: null, description: null, dishes: [], drinks: []};
    this.drinkOrders = [];
    this.dishOrders = [];
  }


  addDrink(id: number)
  {
    for (let i = 0; i < this.restaurant.drinks.length; i++)
    {

      if (this.restaurant.drinks[i].id == id)
      {
        let exists = false;

        for (let j = 0; j < this.drinkOrders.length; j++)
          if (this.drinkOrders[j].id == id) exists = true;

        if (exists)
          this.drinkOrders[i].quantity++;
        else
        {
          this.drinkOrders.push(this.restaurant.drinks[i]);
          this.drinkOrders[this.drinkOrders.length -1].quantity = 1;
        }

        break;
      }

    }
    this.notifyDrinks.emit(this.drinkOrders);
  }

  addDish(id: number)
  {
    for (let i = 0; i < this.restaurant.dishes.length; i++)
    {

      if (this.restaurant.dishes[i].id == id)
      {
        let exists = false;

        for (let j = 0; j < this.dishOrders.length; j++)
          if (this.dishOrders[j].id == id) exists = true;

        if (exists)
          this.dishOrders[i].quantity++;
        else
        {
          this.dishOrders.push(this.restaurant.dishes[i]);
          this.dishOrders[this.dishOrders.length -1].quantity = 1;
        }

        break;
      }

    }
    this.notifyDrinks.emit(this.drinkOrders);
  }


  removeDrink(id: number)
  {
    for (let i = 0; i < this.drinkOrders.length; i++)
    {
      if (this.drinkOrders[i].id == id)
      {
        this.drinkOrders[i].quantity = 0;
        this.drinkOrders.splice(i, 1);
        break;
      }
    }
    this.notifyDrinks.emit(this.drinkOrders);
  }

  removeDish(id: number)
  {
    for (let i = 0; i < this.dishOrders.length; i++)
    {
      if (this.dishOrders[i].id == id)
      {
        this.dishOrders[i].quantity = 0;
        this.dishOrders.splice(i, 1);
        break;
      }
    }
    this.notifyDishes.emit(this.dishOrders);
  }
}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
  dishes: Dish[];
  drinks: Drink[];
}


interface Dish
{
  id: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
}


interface Drink
{
  id: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
}
