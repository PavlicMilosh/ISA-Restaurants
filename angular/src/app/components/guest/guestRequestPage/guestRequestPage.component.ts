import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestFriendsPage',
  templateUrl: 'guestRequestPage.component.html',
  styleUrls: ['guestRequestPage.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestRequestPageComponent implements OnInit {
  private requests: Guest[];
  private friendship: Friendship;

  constructor(private guestService: GuestService) {
    this.requests = [];
    this.guestService.getFriendRequests(1).subscribe(
      data => {
        this.requests = data;
        console.log(data)
      },
      //error => alert(error)
    );
  }

  ngOnInit()
  {
  }

  declineRequest(fromWhomId: number)
  {
    this.guestService.declineFriendRequest(1, fromWhomId).subscribe
    (
      data =>
      {
        for (let i = 0; i < this.requests.length; i++)
          if (this.requests[i].id == fromWhomId)
            this.requests.splice(i, 1);
        console.log(data)
      },
      //error => alert(error)
    );
  }


  acceptRequest(fromWhomId: number)
  {
    this.guestService.acceptFriendRequest(1, fromWhomId).subscribe
    (
      data =>
      {
        for (let i = 0; i < this.requests.length; i++)
          if (this.requests[i].id == fromWhomId)
            this.requests.splice(i, 1);
        console.log(data)
      },
      //error => alert(error)
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

interface Friendship
{
  id: number;
  firstUserId: number;
  secondUserId: number;
  actionUserId: number;
  firstUserMail: string;
  secondUserMail: string;
  actionUserMail: string;
  status: string;
}
