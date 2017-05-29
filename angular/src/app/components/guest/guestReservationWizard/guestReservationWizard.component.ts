import { Component, OnInit } from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import { RestaurantService } from "../../../services/restaurants.service"
import {GuestRestaurantsPageComponent } from "../guestRestaurantsPage/guestRestaurantsPage.component";
import 'fabric';

declare let fabric;
declare let jQuery: any;

@Component({
  moduleId: module.id,
  selector: 'registerGuest',
  templateUrl: 'guestReservationWizard.component.html',
  styleUrls: ['guestReservationWizard.component.css', '../../style/formStyle.css'],
  providers: [GuestService, RestaurantService, GuestRestaurantsPageComponent]
})


export class GuestReservationWizardComponent implements OnInit {

  private selectedRestaurant: Restaurant;
  private reservation: Reservation;
  private tables: RestaurantTable[];
  private regions: Region[];
  private canvas;

  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.selectedRestaurant = {id: 0, name: "", description: ""};
    this.reservation = {startTime : "", startDate: "", duration: 0, restaurant: this.selectedRestaurant};
    this.tables = [];
    this.regions = [];
    this.getRegions();
    this.createWizard();
  }


  ngOnInit()
  {
    this.canvas = new fabric.Canvas('canvas');
    this.canvas.setDimensions({width:900, height:900});
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
    console.log("Date picker init");
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





  initTables()
  {

    this.getTables();

    let ctx = this.canvas.getContext('2d');
    ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

    for (let i = 0; i < this.tables.length; i++)
    {
      console.log(this.tables[i]);
      let rect = new fabric.Rect(
        {
          left: this.tables[i].left,
          top: this.tables[i].top,
          fill: 'blue',
          width: 50,
          height: 50,
        }
      );
      this.canvas.add(rect);
      this.canvas.item(i).hasControls = false;
      this.canvas.item(i).hasBorders = false;
    }
  }


  getTables()
  {
    console.log(this.reservation);
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
    this.reservation.restaurant = this.selectedRestaurant;
    this.guestService.sendReservation(this.reservation).subscribe
    (
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
}


interface Reservation
{
  startTime: string;
  duration: number;
  startDate: string;
  restaurant: Restaurant;
}


interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
  occupied: boolean;
  regionId: number;
}


interface Region
{
  name: string;
  color: string;
  id: number;
}
