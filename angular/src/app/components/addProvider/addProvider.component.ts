import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/users.service";

@Component({
  selector: 'app-add-provider',
  templateUrl: './addProvider.component.html',
  styleUrls: ['./addProvider.component.css'],
  providers: [UserService]
})
export class AddProviderComponent
{
  userDTO: UserDTO;
  email: string;
  password: string;
  firstName: string;
  lastName: string;


  constructor(private userService: UserService)
  {}

  ngOnInit()
  {

  }

  addProvider()
  {
    this.userService.addProvider(this.email, this.password, this.firstName, this.lastName).subscribe(
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
