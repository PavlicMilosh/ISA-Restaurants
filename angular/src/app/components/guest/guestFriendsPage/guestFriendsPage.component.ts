import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestFriendsPage',
  templateUrl: 'guestFriendsPage.component.html',
  styleUrls: ['guestFriendsPage.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestFriendsPageComponent implements OnInit
{
  private friends: Guest[];
  private searchParams: string;


  constructor(private guestService: GuestService)
  {
    this.friends = [];
    this.guestService.getFriends().subscribe(
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
    this.guestService.unfriend(whoId).subscribe(
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

  searchFriends()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.guestService.getFriends().subscribe
      (
        data => this.friends = data,
        error => alert(error)
      );
    }

    this.guestService.searchFriends(this.searchParams).subscribe(
      data => this.friends = data,
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
