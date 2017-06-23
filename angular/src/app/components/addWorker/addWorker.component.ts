import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/users.service";
import {RestaurantService} from "../../services/restaurants.service";
import {LoggedUtils} from "../../utils/logged.utils";
import {Guard} from "../../utils/Guard";

@Component({
  selector: 'app-add-worker',
  templateUrl: './addWorker.component.html',
  styleUrls: ['./addWorker.component.css'],
  providers: [ UserService, RestaurantService, Guard ]
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
  private dishType: any;
  private dishTypes: any;


  constructor(private userService : UserService, private restaurantService : RestaurantService)
  {
    this.role = "Select Role";
    this.restaurantService.getDishTypes(LoggedUtils.getId()).subscribe(
      data => {this.dishTypes = data; this.dishType = this.dishTypes[0].id;}
    )

  }

  ngOnInit(){  }

  setRole(role: string)
  {
    this.role = role;
  }

  addWorker()
  {
    this.userService.addWorker(this.firstName, this.lastName, this.email, this.password, this.clothesNumber, this.footwearNumber, this.role, this.dishType)
      .subscribe(
        data => console.log(data)
      )
  }

  setDishType(dt : number)
  {
    this.dishType = dt;
  }

}

