/**
 * Created by djuro on 4/27/2017.
 */

import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";

@Component({
  moduleId: module.id,
  selector: 'updateUser',
  templateUrl: './updateUser.component.html',
  styleUrls: ['./updateUser.component.css'],
  providers: [UserService]
})
export class UpdateUser
{
  userDTO: UserDTO;
  id:number;
  password: string;
  firstName: string;
  lastName: string;
  email: string;


  constructor(private userService: UserService)
  {

  }

  changeBarman()
  {
    this.userService.updateBarman(1,this.email, this.password, this.firstName, this.lastName).subscribe(
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
