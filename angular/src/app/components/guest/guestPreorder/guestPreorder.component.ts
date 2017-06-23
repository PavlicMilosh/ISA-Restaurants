import {Component, Input, Output, EventEmitter, OnChanges, SimpleChanges} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {Guard} from "../../../utils/Guard";


@Component({
  moduleId: module.id,
  selector: 'guestPreorder',
  templateUrl: 'guestPreorder.component.html',
  styleUrls: ['guestPreorder.component.css', '../../style/tableStyle.css'],
  providers: [GuestService, Guard]
})


export class GuestPreorderComponent implements OnChanges
{

  @Input() restaurant: Restaurant;
  @Output() notifyDrinks: EventEmitter<Drink[]> = new EventEmitter<Drink[]>();
  @Output() notifyDishes: EventEmitter<Dish[]> = new EventEmitter<Dish[]>();

  private drinkOrders: Drink[];
  private dishOrders: Dish[];

  constructor()
  {
    this.drinkOrders = [];
    this.dishOrders = [];
  }


  ngOnChanges(changes: SimpleChanges)
  {
    if (changes['restaurant'] != null)
      this.restaurant = changes['restaurant'].currentValue;
    this.drinkOrders = [];
    this.dishOrders = [];
  }


  addDrink(id: number)
  {
    for (let i = 0; i < this.restaurant.drinks.length; i++)
    {

      if (this.restaurant.drinks[i].id == id)
      {
        let inTheList = false;

        for (let j = 0; j < this.drinkOrders.length; j++)
        {
          if (this.drinkOrders[j].id == id)
          {
            this.drinkOrders[j].quantity++;
            inTheList = true;
            break;
          }
        }

        if (!inTheList)
        {
          let d = this.restaurant.drinks[i];
          d.quantity = 1;
          this.drinkOrders.push(d);
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
        let inTheList = false;

        for (let j = 0; j < this.dishOrders.length; j++)
        {
          if (this.dishOrders[j].id == id)
          {
            this.dishOrders[j].quantity++;
            inTheList = true;
            break;
          }
        }

        if (!inTheList)
        {
          let d = this.restaurant.dishes[i];
          d.quantity = 1;
          this.dishOrders.push(d);
        }

        break;
      }

    }
    this.notifyDishes.emit(this.dishOrders);
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
