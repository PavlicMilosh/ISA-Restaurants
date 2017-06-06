import {Component, OnInit, HostListener} from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import { RestaurantService } from "../../../services/restaurants.service"

import 'fabric';

declare let fabric;
declare let jQuery: any;

@Component({
  moduleId: module.id,
  selector: 'guestReservation',
  templateUrl: 'guestReservation.component.html',
  styleUrls: ['guestReservation.component.css', '../../style/formStyle.css'],
  providers: [GuestService, RestaurantService]
})


export class GuestReservationComponent implements OnInit {

  private selectedRestaurant: Restaurant;
  private reservation: Reservation;
  private tables: RestaurantTable[];
  private invites: Guest[];
  private selectedTables: RestaurantTable[];
  private regions: Region[];
  private canvas;

  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.selectedRestaurant = {id: 0, name: "", description: ""};
    this.reservation = {startTime : "", startDate: "", duration: 0, restaurant: this.selectedRestaurant, tables: [], invites: []};
    this.tables = [];
    this.regions = [];
    this.invites = [];
    this.selectedTables = [];
  }


  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:900, height:900});
    this.createWizard();
  }


  createWizard()
  {
    jQuery(document).ready(function()
    {
      jQuery('#smartwizard').smartWizard();
    });
  }


  initDatePicker()
  {
    //console.log("Date picker init");
    jQuery(document).ready(function()
    {
      let date_input=jQuery('#date'); //our date input has the name "date"
      let container=jQuery('.bootstrap-iso form').length>0 ? jQuery('.bootstrap-iso form').parent() : "body";
      let options=
        {
          format: 'dd.mm.yyyy.',
          container: container,
          todayHighlight: true,
          autoclose: true,
        };
      date_input.datepicker(options);
    });
    jQuery('#time').clockpicker();
  }


  selectedRestaurantNotify(restaurant: Restaurant)
  {
    this.selectedRestaurant = restaurant;
    this.reservation.restaurant = restaurant;
  }

  invitesNotify(invites: Guest[])
  {
    this.invites = invites;
    this.reservation.invites = this.invites;
  }


  initTables()
  {
    this.getTables();
    this.canvas.clear();

    for (let i = 0; i < this.tables.length; i++)
    {
      let rect = new fabric.Rect(
        {
          id: this.tables[i].id,
          width: 50,
          height: 50,
          left: this.tables[i].left,
          top: this.tables[i].top,
          fill: this.tables[i].regionColor,
          stroke: '#ff0017',
          strokeWidth: 0,
          lockMovementX: true,
          lockMovementY: true,
          lockUniScaling: true,
          lockRotation: true,
          hasControls: false,
          selected: false
        }
      );

      if (this.tables[i].occupied)
      {
        rect.stroke = 'black';
        rect.fill = 'gray';
        rect.strokeWidth = 5;
      }

      let text = new fabric.Text(String(this.tables[i].seats),
        {
          fontFamily: 'Comic Sans',
          fontSize: 18,
          left: this.tables[i].left,
          top: this.tables[i].top,
          lockMovementX: true,
          lockMovementY: true,
          lockUniScaling: true,
          lockRotation: true,
          hasControls: false
        }
      );

      /*let group = new fabric.Group([rect, text],
        {
          id: this.tables[i].id,
          left: this.tables[i].left,
          top: this.tables[i].top,
          dirty: true,
          lockMovementX: true,
          lockMovementY: true,
          lockUniScaling: true,
          lockRotation: true,
          hasControls: false
        }
      );*/

      if (!this.tables[i].occupied)
      {
        let self = this;
        rect.on('mousedown', function(e)
        {
          e.target.strokeWidth = 5 - e.target.strokeWidth;

          if (e.target.strokeWidth == 5)
          {
            self.selectedTables.push(self.tables[i]);
          }
          else
          {
            for (let j = 0; j < self.selectedTables.length; j++) {
              if (self.selectedTables[j].id = self.tables[i].id) {
                self.selectedTables.splice(j, 1);
                break;
              }
            }
          }
        });
      }
      this.canvas.add(rect);
      this.canvas.add(text);


    }
  }


  getTables()
  {
    this.guestService.getTables(this.reservation).subscribe
    (
      data => this.tables = data,
      error => alert(error)
    );
  }


  getRegions()
  {
    this.restaurantService.getRegions(this.selectedRestaurant.id).subscribe
    (
      data => this.regions = data,
      error => alert(error)
    );
  }


  sendReservation()
  {
    /*this.reservation.restaurant = this.selectedRestaurant;
    this.guestService.sendReservation(this.reservation).subscribe
    (
      data => console.log(data),
      error => alert(error)
    );*/
    this.reservation.restaurant = this.selectedRestaurant;
    this.reservation.tables = this.selectedTables;
    console.log(this.reservation);
  }




}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
}


interface Reservation
{
  startTime: string;
  duration: number;
  startDate: string;
  restaurant: Restaurant;
  invites: Guest[];
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


interface Region
{
  name: string;
  color: string;
  id: number;
}


interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  enabled: boolean;
}
