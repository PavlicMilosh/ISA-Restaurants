import {Component, OnInit} from '@angular/core';
import {GuestService} from "../../../services/guest.service";


@Component({
  moduleId: module.id,
  selector: 'registerGuest',
  templateUrl: 'updateGuest.component.html',
  styleUrls: ['updateGuest.component.css', '../../style/formStyle.css'],
  providers: [GuestService]
})


export class UpdateGuestComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;

  constructor(private guestService: GuestService){}


  updateGuest()
  {
    this.guestService.updateGuest(this.email, this.password, this.firstName, this.lastName).subscribe
    (
      data => this.userDTO = data,
      error => alert(error)
    );
  }
}


interface UserDTO
{
  id: number;
  email: string;
  firstName: string;
  lastName: string;
}
