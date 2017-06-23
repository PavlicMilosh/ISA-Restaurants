///<reference path="../../../utils/Guard.ts"/>
import { Component, OnInit } from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import { Guard } from "../../../utils/Guard";

@Component
({
  moduleId: module.id,
  selector: 'guestFriends',
  templateUrl: 'guestFriends.component.html',
  styleUrls: ['guestFriends.component.css', '../../style/tableStyle.css'],
  providers: [GuestService, Guard]
})


export class GuestFriendsComponent implements OnInit
{

  private people: Guest[];
  private searchParams: string;
  private firstNameSortAsc: boolean;
  private lastNameSortAsc: boolean;

  constructor(private guestService: GuestService)
  {
    this.people = [];
    this.searchParams = "";
    this.guestService.getFriends().subscribe
    (
      data => this.people = data,
      error => alert(error)
    );
    this.firstNameSortAsc = false;
    this.lastNameSortAsc = false;
  }


  ngOnInit() {}


  unfriend(whoId: number) {
    this.guestService.unfriend(whoId).subscribe(
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
      this.guestService.getFriends().subscribe
      (
        data => this.people = data,
        //error => alert(error)
      );
    }

    this.guestService.searchFriends(this.searchParams).subscribe
    (
      data => this.people = data,
      error => alert(error)
    );
  }


  sortData(attribute: string)
  {
    let asc = true;

    if (attribute == 'firstName')
    {
      asc = this.firstNameSortAsc;
      this.firstNameSortAsc = !this.firstNameSortAsc;
    }
    if (attribute == 'lastName')
    {
      asc = this.lastNameSortAsc;
      this.lastNameSortAsc = !this.lastNameSortAsc;
    }

    this.people.sort(function(a, b)
    {

      let first = a[attribute];
      let second = b[attribute];
      // Compare the 2 dates


      if (asc)
      {
        if (first < second) return -1;
        if (first > second) return 1;
      }
      else
      {
        if (first > second) return -1;
        if (first < second) return 1;
      }
      return 0;
    });
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
