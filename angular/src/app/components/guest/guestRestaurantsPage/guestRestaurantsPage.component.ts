import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {RestaurantService} from "../../../services/restaurants.service";
declare var jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurantsPage',
  templateUrl: 'guestRestaurantsPage.component.html',
  styleUrls: ['guestRestaurantsPage.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService]
})


export class GuestRestaurantsPageComponent implements OnInit {

  private restaurants: Restaurant[];
  private selectedRestaurant: Restaurant;
  private searchParams: string;
  private reservation: Reservation;


  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.initDatePicker();

    this.restaurants = [];
    this.selectedRestaurant = {id: 0, name: "", description: ""};
    this.reservation = {time : "", date: "", duration: 0, restaurant: this.selectedRestaurant};

    this.restaurantService.getRestaurants().subscribe
    (
      data => this.restaurants = data,
      error => alert(error)
    );
  }


  ngOnInit() {}


  initDatePicker()
  {
    jQuery(document).ready(function()
    {
      var date_input=jQuery('input[name="date"]'); //our date input has the name "date"
      var container=jQuery('.bootstrap-iso form').length>0 ? jQuery('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'dd.mm.yyyy.',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    });
  }



  selectRestaurant(selectedId: number)
  {
    for (let i = 0; i < this.restaurants.length; i++)
      if (this.restaurants[i].id == selectedId)
        this.selectedRestaurant = this.restaurants[i];

    console.log(this.selectedRestaurant);
  }


  searchRestaurants()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.restaurantService.getRestaurants().subscribe
      (
        data => this.restaurants = data,
        error => alert(error)
      );
    }

    this.restaurantService.searchRestaurants(this.searchParams).subscribe
    (
      data => this.restaurants = data,
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
  time: string;
  duration: number;
  date: string;
  restaurant: Restaurant;
}
