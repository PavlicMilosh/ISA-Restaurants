import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {LoggedUtils} from "../../../utils/logged.utils";

@Component
({
  moduleId: module.id,
  selector: 'guestPeople',
  templateUrl: 'guestPeople.component.html',
  styleUrls: ['guestPeople.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestPeopleComponent implements OnInit {

  private people: GuestAndRelation[];
  private searchParams: string;
  private myId: number;

  constructor(private guestService: GuestService) {
    this.people = [];

    if (LoggedUtils.isEmpty())
      this.myId = -10;
    else
      this.myId = LoggedUtils.getId();

    this.guestService.getAllGuests().subscribe
    (
      data => {
        this.people = data;

      },
      //error => alert(error)
    );

  }


  ngOnInit()
  {
    this.myId = LoggedUtils.getId();
  }


  sendFriendRequest(toWhomId: number) {
    this.guestService.sendFriendRequest(toWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == toWhomId) {
            this.people[i].friendshipStatus = 'PENDING';
            this.people[i].lastActionUserId = 1; // generalizovati id
          }
      },
     // error => alert(error)
    );
  }


  declineRequest(fromWhomId: number) {
    this.guestService.declineFriendRequest(fromWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == fromWhomId) {
            this.people[i].friendshipStatus = 'DECLINED';
            this.people[i].lastActionUserId = 1; // generalizovati id
          }

        console.log(data)
      },
      //error => alert(error)
    );
  }


  acceptRequest(fromWhomId: number) {
    this.guestService.acceptFriendRequest(fromWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == fromWhomId) {
            this.people[i].friendshipStatus = 'ACCEPTED';
            this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
          }
        console.log(data)
      },
      //error => alert(error)
    );
  }


  unfriend(whoId: number) {
    this.guestService.unfriend(whoId).subscribe(
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == whoId) {
            this.people[i].friendshipStatus = 'UNFRIENDED';
            this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
          }
        console.log(data)
      },
      //error => alert(error)
    );
  }


  searchAllGuests()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.guestService.getAllGuests().subscribe
      (
        data => this.people = data,
        //error => alert(error)
      );
    }

    this.guestService.searchAllGuests(this.searchParams).subscribe(
      data => this.people = data,
      //error => alert(error)
    );
  }




}


interface GuestAndRelation
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  enabled: boolean;
  friendshipStatus: string;
  lastActionUserId: number;
}
