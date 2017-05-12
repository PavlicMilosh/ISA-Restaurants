import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestFriendsPage',
  templateUrl: 'guestPeoplePage.component.html',
  styleUrls: ['guestPeoplePage.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestPeoplePageComponent implements OnInit {

  private people: GuestAndRelation[];
  private searchParams: string;

  constructor(private guestService: GuestService) {
    this.people = [];
    this.guestService.getAllGuests(1).subscribe
    (
      data => {
        this.people = data;

      },
      error => alert(error)
    );

  }


  ngOnInit() {
  }


  sendFriendRequest(toWhomId: number) {
    this.guestService.sendFriendRequest(1, toWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == toWhomId) {
            this.people[i].friendshipStatus = 'PENDING';
            this.people[i].lastActionUserId = 1; // generalizovati id
          }
      },
      error => alert(error)
    );
  }


  declineRequest(fromWhomId: number) {
    this.guestService.declineFriendRequest(1, fromWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == fromWhomId) {
            this.people[i].friendshipStatus = 'DECLINED';
            this.people[i].lastActionUserId = 1; // generalizovati id
          }

        console.log(data)
      },
      error => alert(error)
    );
  }


  acceptRequest(fromWhomId: number) {
    this.guestService.acceptFriendRequest(1, fromWhomId).subscribe
    (
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == fromWhomId) {
            this.people[i].friendshipStatus = 'ACCEPTED';
            this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
          }
        console.log(data)
      },
      error => alert(error)
    );
  }


  sendInvitation(toWhomId: number) {
    this.guestService.sendInvitation(toWhomId);
  }


  unfriend(whoId: number) {
    this.guestService.unfriend(1, whoId).subscribe(
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == whoId) {
            this.people[i].friendshipStatus = 'UNFRIENDED';
            this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
          }
        console.log(data)
      },
      error => alert(error)
    );
  }


  searchAllGuests()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.guestService.getAllGuests(1).subscribe
      (
        data => this.people = data,
        error => alert(error)
      );
    }

    this.guestService.searchAllGuests(1, this.searchParams).subscribe(
      data => this.people = data,
      error => alert(error)
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
