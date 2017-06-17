import {Component, OnInit, HostListener} from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import { RestaurantService } from "../../../services/restaurants.service"

import 'fabric';
import {GuestReservationSummaryComponent} from "../guestReservationSummary/guestReservationSummary.component";
import {Subject} from "rxjs";
import {LoggedUtils} from "../../../utils/logged.utils";

declare let fabric;
declare let jQuery: any;

@Component({
  moduleId: module.id,
  selector: 'guestReservationWizard',
  templateUrl: 'guestReservationWizard.component.html',
  styleUrls: ['guestReservationWizard.component.css', '../../style/formStyle.css', '../../style/tableStyle.css'],
  providers: [GuestService, RestaurantService]
})


export class GuestReservationWizardComponent implements OnInit {

  private reservation: Reservation;
  private restaurantChoosen: boolean;
  private tablesChoosen: boolean;
  private logged: boolean;
  private accountEnabled: boolean;

  parentSubject: Subject<any> = new Subject();
  notifyChildren()
  {
    this.parentSubject.next(this.reservation);
  }


  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.reservation = {startTime : "", startDate: "", duration: 0,
                        tables: [], invites: [], dishOrders: [], drinkOrders:[],
                        restaurant: {
                          id: null, name: null, description: null, dishes:[], drinks: [], tables: []}};
  }


  ngOnInit()
  {
    this.restaurantChoosen = false;
    this.tablesChoosen = false;
  }


  selectedRestaurantNotify(restaurant: Restaurant)
  {
    if (restaurant != null)
    {
      this.reservation.restaurant = restaurant;

      this.reservation.startTime = null;
      this.reservation.startDate = null;
      this.reservation.duration = null;
      this.reservation.tables = [];
      this.reservation.drinkOrders = [];
      this.reservation.dishOrders = [];
      this.reservation.invites = [];

      this.restaurantChoosen = true;
    }
    else
      this.restaurantChoosen = false;

    this.tablesChoosen = false;

    this.notifyChildren();

  }


  tablesNotify(tablesAndTime: Reservation)
  {
    this.reservation.tables = tablesAndTime.tables;
    this.reservation.startDate = tablesAndTime.startDate;
    this.reservation.startTime = tablesAndTime.startTime;
    this.reservation.duration = tablesAndTime.duration;
    this.tablesChoosen = this.reservation.tables.length > 0;

    this.notifyChildren();
  }


  invitesNotify(invites: Guest[])
  {
    this.reservation.invites = invites;
    this.notifyChildren();
  }


  drinkOrdersNotify(drinks: Drink[])
  {
    this.reservation.drinkOrders = drinks;
  }


  dishOrdersNotify(dishes: Dish[])
  {
    this.reservation.dishOrders = dishes;
    this.notifyChildren();
  }


  sendReservation()
  {
    console.log(this.reservation);
    this.guestService.sendReservation(this.reservation).subscribe(
      data => console.log(data),
      error => alert(error)
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
  tables: RestaurantTable[];
}


interface Reservation
{
  startTime: string;
  duration: number;
  startDate: string;
  restaurant: Restaurant;
  invites: Guest[];
  tables: RestaurantTable[];
  drinkOrders: Drink[];
  dishOrders: Dish[];
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


interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
  occupied: boolean;
  regionId: number;
  regionColor: string;
  seats: number;
}


interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  enabled: boolean;
}
