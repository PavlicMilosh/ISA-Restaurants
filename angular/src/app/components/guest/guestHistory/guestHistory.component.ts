import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {RestaurantService} from "../../../services/restaurants.service";
import {Guard} from "../../../utils/Guard";
declare let jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestHistory',
  templateUrl: 'guestHistory.component.html',
  styleUrls: ['guestHistory.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService, Guard]
})


export class GuestHistoryComponent implements OnInit
{
  private historyData: History[];
  private markedHistory: History;

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

  markOrder(history:History)
  {
    history.isMark=true;
    this.guestService.makeMark(history).subscribe(
      data => { this.markedHistory = data; console.log(data); },
      error => alert(error)
    );
  }

  setMealMark(history:History,mark:number)
  {
    history.mealMyMark=mark;
  }

  setRestaurantMark(history:History,mark:number)
  {
    history.restaurantMyMark=mark;
  }

  setWaiterMark(history:History,mark:number)
  {
    history.waiterMark=mark;
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
  isMark: boolean;

  waiterMark: number;
  waiterFirstName: string;
  waiterLastName: string;
  waiterId: number;

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
