import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import {Guard} from "../../utils/Guard";


@Component({
  moduleId: module.id,
  selector: 'registerSM',
  templateUrl: './registerSM.component.html',
  styleUrls: ['./registerSM.component.css'],
  providers: [UserService, Guard]
})
export class RegisterSMComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;


  constructor(private userService: UserService)
  {}

  addSM()
  {
    this.userService.addSM(this.email, this.password, this.firstName, this.lastName).subscribe(
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
