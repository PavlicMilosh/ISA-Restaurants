import {Component, OnInit} from '@angular/core';
import {GuestService} from "../../../services/guest.service";
import {Guard} from "../../../utils/Guard";


@Component({
  moduleId: module.id,
  selector: 'guestUpdate',
  templateUrl: 'guestUpdate.component.html',
  styleUrls: ['guestUpdate.component.css', '../../style/formStyle.css'],
  providers: [GuestService, Guard]
})


export class GuestUpdateComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;

  constructor(private guestService: GuestService)
  {
    this.getGuestData();
  }


  updateGuest()
  {
    this.guestService.updateGuest(this.email, this.password, this.firstName, this.lastName).subscribe
    (
      data => this.userDTO = data,
      error => alert(error)
    );
  }


  getGuestData()
  {
    this.guestService.getGuestData().subscribe
    (
      data =>
      {
        this.userDTO = data;
        this.email = this.userDTO.email;
        this.firstName = this.userDTO.firstName;
        this.lastName = this.userDTO.lastName;
      },
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
