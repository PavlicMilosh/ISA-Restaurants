import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";


@Component({
  moduleId: module.id,
  selector: 'registerRM',
  templateUrl: './registerRM.component.html',
  styleUrls: ['./registerRM.component.css'],
  providers: [UserService]
})
export class RegisterRMComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  restaurants: Restaurant;

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

interface Restaurant
{

}
