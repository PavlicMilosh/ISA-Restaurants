import {Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, OnInit} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import 'fabric';
import {RestaurantService} from "../../../services/restaurants.service";
import {Subject} from "rxjs";

declare let fabric;

@Component({
  moduleId: module.id,
  selector: 'guestReservationSummary',
  templateUrl: 'guestReservationSummary.component.html',
  styleUrls: ['guestReservationSummary.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestReservationSummaryComponent implements OnInit//, OnChanges
{
  @Input() parentSubject: Subject<any>;
  //@Input()
  reservation: Reservation;
  private allTables: RestaurantTable[];
  private drinkOrdersTotal: number;
  private dishOrdersTotal: number;
  private canvas;

  constructor(private restaurantService: RestaurantService)
  {
    this.reservation = {startTime : "", startDate: "", duration: 0,
      tables: [], invites: [], dishOrders: [], drinkOrders:[],
      restaurant: {
        id: null, name: null, description: null, dishes:[], drinks: []}};
    this.dishOrdersTotal = 0;
    this.drinkOrdersTotal = 0;
  }


  ngOnInit()
  {
    this.canvas = new fabric.Canvas('guestReservationSummaryCanvas');
    this.canvas.setDimensions({width: 900, height: 900});

    this.parentSubject.subscribe(event =>
    {
      this.reservation = event;

      if (this.canvas != null)
        this.canvas.clear();

      this.getTables();
      this.initTables();
    });
  }


  /*ngOnChanges(c: SimpleChanges)
  {
    this.reservation = c['reservation'].currentValue;

    for (let d of this.reservation.drinkOrders)
      this.drinkOrdersTotal += d.price * d.quantity;

    for (let d of this.reservation.dishOrders)
      this.dishOrdersTotal += d.price * d.quantity;

    if (this.canvas != null)
      this.canvas.clear();

    this.getTables();
    this.initTables();
  }*/


  initTables()
  {
    if (this.allTables != null)
    {
      for (let i = 0; i < this.allTables.length; i++) {
        let rect = new fabric.Rect(
          {
            id: this.allTables[i].id,
            width: 50,
            height: 50,
            left: this.allTables[i].left,
            top: this.allTables[i].top,
            fill: 'gray',
            opacity: 0.5,
            strokeWidth: 0,
            lockMovementX: true,
            lockMovementY: true,
            lockUniScaling: true,
            lockRotation: true,
            hasControls: false,
            selected: false
          }
        );

        for (let j = 0; j < this.reservation.tables.length; j++) {
          if (this.reservation.tables[j].id == this.allTables[i].id) {
            console.log(this.allTables[i]);
            rect.fill = this.allTables[i].regionColor;
            rect.opacity = 1;
            break;
          }
        }

        let text = new fabric.Text(String(this.allTables[i].seats),
          {
            fontFamily: 'Comic Sans',
            fontSize: 18,
            left: this.allTables[i].left,
            top: this.allTables[i].top,
            lockMovementX: true,
            lockMovementY: true,
            lockUniScaling: true,
            lockRotation: true,
            hasControls: false
          }
        );

        this.canvas.add(rect);
        this.canvas.add(text);
      }
    }
  }


  getTables()
  {
    if (this.reservation.restaurant.id != null)
    {
      this.restaurantService.getTables(this.reservation.restaurant.id).subscribe(
        data => { this.allTables = data; console.log(this.allTables)},
        error => alert(error)
      );
    }
  }
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
  confirmed: boolean;
}
