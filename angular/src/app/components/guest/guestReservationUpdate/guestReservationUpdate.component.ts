import { Component, OnInit } from '@angular/core';
import { GuestService } from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestReservationUpdate',
  templateUrl: 'guestReservationUpdate.component.html',
  styleUrls: ['guestReservationUpdate.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestReservationUpdateComponent implements OnInit {

  constructor(){}
  ngOnInit(){}
}
