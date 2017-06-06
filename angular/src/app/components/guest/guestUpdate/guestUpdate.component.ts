import {Component, OnInit} from '@angular/core';
import {GuestService} from "../../../services/guest.service";


@Component({
  moduleId: module.id,
  selector: 'guestUpdate',
  templateUrl: 'guestUpdate.component.html',
  styleUrls: ['guestUpdate.component.css', '../../style/formStyle.css'],
  providers: [GuestService]
})


export class GuestUpdateComponent
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
