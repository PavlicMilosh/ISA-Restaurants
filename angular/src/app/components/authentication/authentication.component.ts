import { Component } from '@angular/core';
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  moduleId: module.id,
  selector: 'registerRM',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css', '../style/formStyle.css'],
  providers: [AuthenticationService]
})
export class AuthenticationComponent
{
  private email: string;
  private password: string;

  constructor(private autheticationService: AuthenticationService)
  {
    this.email = "";
    this.password = "";
  }

  authenticate()
  {
    this.autheticationService.authenticateUser(this.email, this.password).subscribe(
      data => localStorage.setItem("loggedUser", JSON.stringify(data)),
      error => alert("Incorrect username and/or password"),
      () => console.log(JSON.parse(localStorage.getItem("loggedUser")))
    );
  }


}
