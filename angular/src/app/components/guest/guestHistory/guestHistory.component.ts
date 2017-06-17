import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {RestaurantService} from "../../../services/restaurants.service";
declare let jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurants',
  templateUrl: 'guestHistory.component.html',
  styleUrls: ['guestHistory.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService]
})


export class GuestHistoryComponent implements OnInit
{

  constructor(private guestService: GuestService)
  {

  }

  ngOnInit()
  {
  }


  searchRestaurants()
  {

  }

}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
  friendsMark: number;
  meanMark: number;
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

