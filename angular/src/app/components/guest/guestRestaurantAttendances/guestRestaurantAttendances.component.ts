import { Component, OnInit } from '@angular/core';
import { GuestService } from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurantAttendances',
  templateUrl: 'guestRestaurantAttendances.component.html',
  styleUrls: ['guestRestaurantAttendances.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestRestaurantAttendancesComponent implements OnInit {

  constructor(){}
  ngOnInit(){}
}
