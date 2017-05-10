import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/users.service";

@Component({
  selector: 'app-add-worker',
  templateUrl: './addWorker.component.html',
  styleUrls: ['./addWorker.component.css'],
  providers: [ UserService ]
})
export class AddWorkerComponent implements OnInit
{
  private firstName: string;
  private lastName: string;
  private email: string;
  private password: string;
  private clothesNumber: number;
  private footwearNumber: number;
  private role: string;


  constructor(private userService : UserService)
  {
    this.role = "Select Role";
  }

  ngOnInit()
  {
  }

  setRole(role: string)
  {
    this.role = role;
  }

  addWorker()
  {
    this.userService.addWorker(this.firstName, this.lastName, this.email, this.password, this.clothesNumber, this.footwearNumber, this.role)
      .subscribe(
        data => console.log(data)
      )
  }

}

