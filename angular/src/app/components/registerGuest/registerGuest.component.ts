import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import {GuestService} from "../../services/guest.service";


@Component({
  moduleId: module.id,
  selector: 'registerGuest',
  templateUrl: './registerGuest.component.html',
  styleUrls: ['./registerGuest.component.css'],
  providers: [GuestService]
})


export class RegisterGuestComponent
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
