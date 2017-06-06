import { Component } from '@angular/core';
import {GuestService} from "../../../services/guest.service";


@Component({
  moduleId: module.id,
  selector: 'guestRegister',
  templateUrl: 'guestRegister.component.html',
  styleUrls: ['guestRegister.component.css', '../../style/formStyle.css'],
  providers: [GuestService]
})


export class GuestRegisterComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;


  constructor(private guestService: GuestService){}


  registerGuest()
  {
    this.guestService.registerGuest(this.email, this.password, this.firstName, this.lastName).subscribe
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
