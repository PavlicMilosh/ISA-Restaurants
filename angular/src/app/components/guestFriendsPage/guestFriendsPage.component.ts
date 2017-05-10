import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestFriendsPage',
  templateUrl: 'guestFriendsPage.component.html',
  styleUrls: ['guestFriendsPage.component.css'],
  providers: [GuestService]
})


export class GuestFriendsPageComponent implements OnInit
{
  private friends: Guest[];

  constructor(private guestService: GuestService)
  {
    this.friends = [];
    this.guestService.getFriends(1).subscribe(
      data => { this.friends = data; console.log(data) },
      error => alert(error)
    );
  }

  ngOnInit()
  {

  }

  sendInvitation(toWhomId: number)
  {
    this.guestService.sendInvitation(toWhomId);
  }

  unfriend(whoId: number)
  {
    this.guestService.unfriend(1, whoId).subscribe(
      data =>
      {
        for (let i = 0; i < this.friends.length; i++)
          if (this.friends[i].id == whoId)
            this.friends.splice(i, 1);
        console.log(data)
      },
      error => alert(error)
    );
  }

}

interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}
