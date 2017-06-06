import { Component, OnInit } from '@angular/core';
import { UserService } from  '../../services/users.service';
import {LoggedUtils} from "../../utils/logged.utils";

@Component({
  selector: 'app-update-provider',
  templateUrl: './updateProvider.component.html',
  styleUrls: ['./updateProvider.component.css'],
  providers: [UserService]
})
export class UpdateProviderComponent implements OnInit {

  private firstName: string;
  private lastName: string;
  private email: string;
  private oldPassword: string;
  private newPassword: string;
  private repeatPassword: string;
  private provider: Provider;

  constructor(private userService: UserService)
  {
    this.provider = {
      id: -100,
      firstName: "",
      lastName: "",
      email: "",
      password: ""
    };
    this.userService.getById(LoggedUtils.getId()).subscribe(
        data => this.provider = data
    );
  }

  ngOnInit()
  {
  }

  updateProvider()
  {
    let canUpdate = true;
    if(this.newPassword != "" && this.repeatPassword != "" && this.email != "" && this.firstName != "" && this.lastName != "")
    {
      if(this.newPassword == this.repeatPassword && this.oldPassword == this.provider.password)
      {
        this.provider.password = this.newPassword;
      }
      else
      {
        canUpdate = false;
      }
    }
    else
    {
      canUpdate = false;
    }
    if(canUpdate)
    {
      console.log(this.provider);
      this.userService.updateProvider(this.provider).subscribe(
        data => console.log(data)
      );
    }
  }

}

interface Provider
{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}
