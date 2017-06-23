import { Component } from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import {Guard} from "../../../utils/Guard";


@Component({
  moduleId: module.id,
  selector: 'guestRegister',
  templateUrl: 'guestRegister.component.html',
  styleUrls: ['guestRegister.component.css', '../../style/formStyle.css'],
  providers: [GuestService]
})


export class GuestRegisterComponent
{
  private userDTO: UserDTO;
  private email: string;
  private password: string;
  private repeatPassword: string;
  private firstName: string;
  private lastName: string;

  constructor(private guestService: GuestService)
  {
  }


  registerGuest()
  {
    if (this.repeatPassword == this.password)
    {
      this.guestService.registerGuest(this.email, this.password, this.firstName, this.lastName).subscribe
      (
        data => this.userDTO = data,
        error => alert(error)
      );
    }
    else
    {
      alert("Passwords don't match");
    }
  }
}


interface UserDTO
{
  id: number;
  email: string;
  firstName: string;
  lastName: string;
}
