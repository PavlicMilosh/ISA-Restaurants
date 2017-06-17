import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {RestaurantService} from "../../../services/restaurants.service";
declare let jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestHistory',
  templateUrl: 'guestHistory.component.html',
  styleUrls: ['guestHistory.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService]
})


export class GuestHistoryComponent implements OnInit
{
  private historyData: History[];

  constructor(private guestService: GuestService)
  {
    this.historyData = [];
  }

  ngOnInit()
  {
    this.guestService.getHistoryData().subscribe(
      data => { this.historyData = data; console.log(data); },
      error => alert(error)
    );
  }


  searchRestaurants()
  {

  }

}


interface History
{
  restaurantId: number;
  reservationId: number;
  orderId: number;

  restaurantName: string;
  restaurantDescription: string;
  restaurantMeanMark: number;

  restaurantFriendsMark: number;
  restaurantMyMark: number;

  mealMyMark: number;

  dateOfVisit: string;
  timeOfVisit: string;

}


interface RestaurantMark
{
  userId: number;
  restaurantId: number;
  reservationId: number;
  orderId: number;
  restaurantMark: number;
  orderMark: number;
}
