import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";


@Component({
  moduleId: module.id,
  selector: 'registerSM',
  templateUrl: './registerSM.component.html',
  styleUrls: ['./registerSM.component.css'],
  providers: [UserService]
})
export class RegisterSMComponent
{
  userDTO: UserDTO;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;


  constructor(private userService: UserService)
  {}

  addSM()
  {
    this.userService.addSM(this.username, this.password, this.firstName, this.lastName, this.email).subscribe(
      data => this.userDTO = data,
      error => alert(error)
    );
  }
}

interface UserDTO
{
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
}
