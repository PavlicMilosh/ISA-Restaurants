import { Component } from '@angular/core';
import { OrderService } from "../../services/order.service";

@Component({
  moduleId: module.id,
  selector: 'makeOrder',
  templateUrl: './makeOrder.component.html',
  styleUrls: ['./makeOrder.component.css'],
  providers: [MakeOrder]
})

export class MakeOrder
{
  dishes:any[];
  drinks:any[];
  userDTO: UserDTO;
  finished: boolean;
  price: number;

  constructor(private orderService: OrderService)
  {

  }

  makeOrder()
  {
    this.orderService.makeOrder(this.dishes,this.drinks,this.userDTO,this.finished,this.price).subscribe(
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



