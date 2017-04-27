import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";

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

  }

  change()
  {
    this.userService.changePassword(this.id,this.email, this.password, this.firstName, this.lastName).subscribe(
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
