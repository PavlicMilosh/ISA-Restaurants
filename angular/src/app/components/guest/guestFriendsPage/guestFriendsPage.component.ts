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


export class GuestFriendsPageComponent implements OnInit {

  private people: Guest[];
  private searchParams: string;

  constructor(private guestService: GuestService) {
    this.people = [];
    this.searchParams = "";
    this.guestService.getFriends(1).subscribe
    (
      data => {
        this.people = data;

      },
      //error => alert(error)
    );

  }


  ngOnInit() {}


  unfriend(whoId: number) {
    this.guestService.unfriend(1, whoId).subscribe(
      data => {
        for (let i = 0; i < this.people.length; i++)
          if (this.people[i].id == whoId)
          {
            this.people.splice(i, 1);
          }
        console.log(data)
      },
      //error => alert(error)
    );
  }


  searchFriends()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.guestService.getFriends(1).subscribe
      (
        data => this.people = data,
        //error => alert(error)
      );
    }

    this.guestService.searchFriends(1, this.searchParams).subscribe(
      data => this.people = data,
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
  enabled: boolean;
}
