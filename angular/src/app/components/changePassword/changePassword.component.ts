
import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import {LoggedUtils } from "../../utils/logged.utils";

@Component({
  moduleId: module.id,
  selector: 'changePassword',
  templateUrl: './changePassword.component.html',
  styleUrls: ['./changePassword.component.css'],
  providers: [UserService]
})

export class ChangePassword
{
  userDTO: UserDTO;
  id:number;
  firstName: string;
  lastName: string;
  email: string;

  password: string;
  oldPassword : string;
  newPassword1 : string;
  newPassword2 : string;


  constructor(private userService: UserService)
  {
    this.userService.getCurrentUser().subscribe
    (
      (data:UserDTO) => this.userDTO = data,
      error => alert(error)
    );
  }

  change()
  {
    if(this.oldPassword==this.userDTO.password)
    {
      console.log(this.oldPassword);
      if(this.newPassword1==this.newPassword2){
        this.userService.changePassword(this.userDTO.id,this.userDTO.email,
          this.newPassword1, this.userDTO.firstName, this.userDTO.lastName).subscribe(
          data => this.userDTO = data,
          error => alert(error),
          ()=>alert("Password changed successfully!")
        );
      }else
      {
        alert("Different passwords!");
      }
    }else
    {
      alert("Incorrect password!");
    }
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
