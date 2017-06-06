import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {RestaurantService} from "../../../services/restaurants.service";
declare let jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurants',
  templateUrl: 'guestRestaurants.component.html',
  styleUrls: ['guestRestaurants.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService]
})


export class GuestRestaurantsComponent implements OnInit
{
  @Output() notify: EventEmitter<Restaurant> = new EventEmitter<Restaurant>();
  private restaurants: Restaurant[];
  private selectedRestaurant: Restaurant;
  private searchParams: string;


  constructor(private guestService: GuestService, private restaurantService: RestaurantService)
  {
    this.restaurants = [];
    this.selectedRestaurant = {id: 0, name: "", description: "", friendsMark: null, meanMark: null};

    this.restaurantService.getRestaurantsForGuest().subscribe
    (
      data => this.restaurants = data,
      error => console.log(error)
    );
  }

  ngOnInit() {}


  selectRestaurant(selectedId: number)
  {
    for (let i = 0; i < this.restaurants.length; i++)
    {
      if (this.restaurants[i].id == selectedId)
      {
        this.selectedRestaurant = this.restaurants[i];
        break;
      }
    }
    this.notify.emit(this.selectedRestaurant);
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

}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
  friendsMark: number;
  meanMark: number;
}
