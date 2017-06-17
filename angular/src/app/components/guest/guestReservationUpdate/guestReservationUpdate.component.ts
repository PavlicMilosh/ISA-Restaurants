import {Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, OnInit} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {Subject} from "rxjs";
import {LoggedUtils} from "../../../utils/logged.utils";


@Component({
  moduleId: module.id,
  selector: 'guestReservationUpdate',
  templateUrl: 'guestReservationUpdate.component.html',
  styleUrls: ['guestReservationUpdate.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestReservationUpdateComponent implements OnInit
{
  @Input() parentSubject = new Subject<any>();
  private reservation: Reservation;

  private drinkOrders: Drink[];
  private dishOrders: Dish[];
  private orderId: number;

  constructor(private guestService: GuestService)
  {
    this.drinkOrders = [];
    this.dishOrders = [];
  }


  ngOnInit()
  {
    this.parentSubject.subscribe(event =>
    {
      this.drinkOrders = [];
      this.dishOrders = [];

      this.reservation = event;
      console.log(event);
      for (let o of this.reservation.orders)
      {
        if (o.guest.id == LoggedUtils.getId())
        {
          this.orderId = o.id;

          for (let d of o.dishOrders)
            this.dishOrders.push(d);
          for (let d of o.drinkOrders)
            this.drinkOrders.push(d);

          break;
        }
      }
    });
  }


  addDrink(id: number)
  {
    for (let i = 0; i < this.reservation.restaurant.drinks.length; i++)
    {

      if (this.reservation.restaurant.drinks[i].id == id)
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
          let d = this.reservation.restaurant.drinks[i];
          d.quantity = 1;
          this.drinkOrders.push(d);
        }

        break;
      }

    }
  }


  addDish(id: number)
  {
    for (let i = 0; i < this.reservation.restaurant.dishes.length; i++)
    {

      if (this.reservation.restaurant.dishes[i].id == id)
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
          let d = this.reservation.restaurant.dishes[i];
          d.quantity = 1;
          this.dishOrders.push(d);
        }

        break;
      }

    }
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
  }


  update() {
    let reservationUpdate =
      {
        reservationId: this.reservation.id,
        guestId: LoggedUtils.getId(),
        orderId: this.orderId,
        dishOrders: this.dishOrders,
        drinkOrders: this.drinkOrders
      };
    this.guestService.updateResevation(this.reservation.id, reservationUpdate).subscribe(
      data => {this.reservation = data; console.log(data)},
      error => alert(error)
    );
  }


  updateNotify()
  {

  }

}


interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}


interface Order
{
  id: number;
  drinkOrders: Drink[];
  dishOrders: Dish[];
  guest: Guest;
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


interface Reservation
{
  id: number;
  restaurant: Restaurant;
  startDate: string;
  startTime: string;
  duration: number;
  reserver: Guest;
  invites: Guest[];
  orders: Order[];
}


interface ReservationUpdate
{
  reservationId: number;
  guestId: number;
  orderId: number
  drinkOrders: Drink[];
  dishOrders: Dish[];
}

