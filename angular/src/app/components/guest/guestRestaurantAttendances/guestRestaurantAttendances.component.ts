import { Component, OnInit } from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import {Subject} from "rxjs";
import {GuestReservationSummaryComponent} from "../guestReservationSummary/guestReservationSummary.component";

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurantAttendances',
  templateUrl: 'guestRestaurantAttendances.component.html',
  styleUrls: ['guestRestaurantAttendances.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestRestaurantAttendancesComponent implements OnInit
{
  private reservations: Reservation[];
  private acceptedInvitations: Invitation[];
  private parentSubjectS: Subject<any> = new Subject();
  private parentSubjectU: Subject<any> = new Subject();

  constructor(private guestService: GuestService)
  {
    this.reservations = [];
    this.acceptedInvitations = [];
  }


  ngOnInit()
  {
    this.reservations = [];
    this.acceptedInvitations = [];
    this.getReservations();
    this.getAcceptedInvitations();
  }


  getReservations()
  {
    this.guestService.getReservations().subscribe
    (
      data => { this.reservations = data; console.log(data)},
      error => alert(error)
    );
  }


  getAcceptedInvitations()
  {
    this.guestService.getAcceptedInvitations().subscribe
    (
      data => { this.acceptedInvitations = data; console.log(data)},
      error => alert(error)
    );
  }


  selectReservation(id: number)
  {
    for (let i = 0; i < this.reservations.length; i++)
    {
      if (this.reservations[i].id == id)
      {

        let c = this.convertReservationForSummary(this.reservations[i]);
        console.log("converted @ select reservation: ");
        console.log(c);
        console.log("plain @ select reservation: ");
        console.log(this.reservations[i]);
        this.parentSubjectS.next(c);
        this.parentSubjectU.next(this.reservations[i]);
        break;
      }
    }
  }


  selectReservationFromInvitation(id: number)
  {
    for (let i = 0; i < this.acceptedInvitations.length; i++)
    {
      if (this.acceptedInvitations[i].reservation.id = id)
      {
        let c = this.convertReservationForSummary(this.acceptedInvitations[i].reservation)
        this.parentSubjectS.next(c);
        this.parentSubjectU.next(this.acceptedInvitations[i].reservation);
        break;
      }
    }
  }

  convertReservationForSummary(reservation: Reservation)
  {
    let drinkOrders = [];
    let dishOrders = [];

    for (let o of reservation.orders)
    {
      for (let d of o.drinkOrders)
        drinkOrders.push(d);
      for (let d of o.dishOrders)
        dishOrders.push(d);
    }

    let passing =
      {
        startTime: reservation.startTime,
        duration: reservation.duration,
        startDate: reservation.startDate,
        restaurant: reservation.restaurant,
        invites: reservation.invites,
        tables: reservation.tables,
        drinkOrders: drinkOrders,
        dishOrders: dishOrders
      };

    return passing;
  }
}


interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  status: string;
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
  tables: RestaurantTable[];
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
  tables: RestaurantTable[];
}

interface ReservationForSymmary
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


interface Invitation
{
  id: number;
  reservation: Reservation;
  invited: Guest;
  status: string;
}

