import { Component, OnInit } from '@angular/core';
import { ShoppingList } from "../ShoppingList";
import { ShoppingItem } from "../ShoppingItem";
import { ShoppingService } from "../../../services/shopping.service";
import { DatePipe } from "@angular/common";
import {Guard} from "../../../utils/Guard";

@Component({
  selector: 'app-add-shopping-list',
  templateUrl: './addShoppingList.component.html',
  styleUrls: ['./addShoppingList.component.css'],
  providers: [ShoppingService, DatePipe, Guard]
})
export class AddShoppingListComponent implements OnInit {

  private shoppingList: ShoppingList;

  constructor(private shoppingService: ShoppingService, private datePipe : DatePipe) { }

  ngOnInit()
  {
    this.shoppingList = new ShoppingList();
  }

  addItem()
  {
    this.shoppingList.addItem(new ShoppingItem("", ""));
  }

  addList()
  {
    this.shoppingList.deadline = this.datePipe.transform(this.shoppingList.deadlineDate, "yyyy-MM-dd HH:mm");
    this.shoppingService.addList(this.shoppingList).subscribe(
      data => console.log(data)
    )
  }
}
