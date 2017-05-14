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
    this.userService.getUser("test123@live.com", "12345", "TestName", "TestLastName").subscribe
    (
      (data:UserDTO) => this.userDTO = data,
      error => alert(error),
      () => this.init()
    );
  }

  changeBarman()
  {
    this.userService.updateBarman(this.id,this.email, this.password, this.firstName, this.lastName).subscribe
    (
      data => this.userDTO = data,
      error => alert(error)
    );
  }

  init()
  {
    this.firstName=this.userDTO.firstName;
    this.lastName=this.userDTO.lastName;
    this.email=this.userDTO.email;
    this.id=this.userDTO.id;
    this.password=this.userDTO.password;
    console.log(this.userDTO.id);
    console.log(this.userDTO.password);
  }

}

interface UserDTO
{
  id: number;
  email: string;
  password:string;
  firstName: string;
  lastName: string;
}
