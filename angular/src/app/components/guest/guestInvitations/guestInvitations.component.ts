import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {GuestService} from "../../../services/guest.service";

@Component
({
  moduleId: module.id,
  selector: 'guestInvitations',
  templateUrl: 'guestInvitations.component.html',
  styleUrls: ['guestInvitations.component.css', '../../style/tableStyle.css'],
  providers: [GuestService]
})


export class GuestInvitationsComponent implements OnInit
{

  @Output() notify: EventEmitter<Guest[]> = new EventEmitter<Guest[]>();
  private data: Guest[];
  private invites: Guest[];
  private friends: Guest[];
  private searchParams: string;

  constructor(private guestService: GuestService)
  {
    this.data = [];
    this.friends = [];
    this.invites = [];
    this.searchParams = "";
    this.guestService.getFriends().subscribe
    (
      data =>
      {
        this.data = data;
        for (let i = 0; i < this.data.length; i++)
          this.data[i].invited=false;
        this.friends = JSON.parse(JSON.stringify(this.data));
      },
      error => alert(error)
    );
  }


  ngOnInit()
  {
    this.friends = this.data;
  }


  remove(whoId: number)
  {
    for (let i = 0; i < this.data.length; i++)
    {
      console.log(this.data[i]);
      if (this.data[i].id == whoId)
      {
        console.log(this.data[i]);
        this.friends.push(this.data[i]);
        this.data[i].invited = true;
        break;
      }
    }

    for (let i = 0; i < this.invites.length; i++)
    {
      if (this.invites[i].id == whoId)
      {
        this.invites.splice(i, 1);
        break;
      }
    }



    this.notify.emit(this.invites);
  }


  invite(whoId: number)
  {
    for (let i = 0; i < this.data.length; i++)
    {
      if (this.data[i].id == whoId)
      {
        this.invites.push(this.data[i]);
        this.data[i].invited = true;
        break;
      }
    }

    for (let i = 0; i < this.friends.length; i++)
    {
      if (this.friends[i].id == whoId)
      {
        this.friends.splice(i, 1);
        break;
      }
    }

    this.notify.emit(this.invites);
  }


  searchFriends()
  {
    this.friends = [];

    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      console.log("EMPTY PARAMS");
      console.log(this.data);
      for (let i = 0; i < this.data.length; i++)
        if (this.data[i].invited == false)
          this.friends.push(this.data[i]);
    }
    else
    {
      for (let i = 0; i < this.data.length; i++)
      {
        console.log("----------------");
        console.log(this.data[i].firstName.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
        console.log(this.data[i].lastName.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
        console.log(this.data[i].email.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
        console.log(!this.data[i].invited);
        console.log("----------------");
        if ((this.data[i].firstName.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1 ||
             this.data[i].lastName.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1 ||
             this.data[i].email.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1) &&
             !this.data[i].invited)
        {
          this.friends.push(this.data[i]);
        }
      }
    }
  }

}


interface Guest
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  enabled: boolean;
  invited: boolean;
}
