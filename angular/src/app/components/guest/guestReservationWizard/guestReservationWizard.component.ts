import { Component, OnInit } from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import { RestaurantService } from "../../../services/restaurants.service"
import {GuestRestaurantsPageComponent } from "../guestRestaurantsPage/guestRestaurantsPage.component";
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


  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.selectedRestaurant = {id: 0, name: "", description: ""};
    this.reservation = {time : "", date: "", duration: 0, restaurant: this.selectedRestaurant};
    this.createWizard();
    this.initDatePicker();
  }


  ngOnInit() {}


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
    console.log(this.selectedRestaurant);
  }


  sendReservation()
  {
    this.reservation.restaurant = this.selectedRestaurant;
    this.guestService.sendReservation(1, this.reservation).subscribe
    (
      data => console.log(data),
      //error => alert(error)
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
  time: string;
  duration: number;
  date: string;
  restaurant: Restaurant;
}
