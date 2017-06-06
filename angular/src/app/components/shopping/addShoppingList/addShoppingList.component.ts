import { Component, OnInit } from '@angular/core';
import { ShoppingList } from "../ShoppingList";
import { ShoppingItem } from "../ShoppingItem";
import { ShoppingService } from "../../../services/shopping.service";
import { DatePipe } from "@angular/common";

@Component({
  selector: 'app-add-shopping-list',
  templateUrl: './addShoppingList.component.html',
  styleUrls: ['./addShoppingList.component.css'],
  providers: [ShoppingService, DatePipe]
})
export class AddShoppingListComponent implements OnInit {

  private shoppingList: ShoppingList;

  constructor(private shoppingService: ShoppingService, private datePipe : DatePipe) { }

  ngOnInit()
  {
    this.shoppingList = new ShoppingList();
    this.shoppingList.addItem(new ShoppingItem("fadfsdfds", "fdssdfdsf"));
    this.shoppingList.addItem(new ShoppingItem("lkj", "jkl"));
  }

  addItem()
  {
    this.shoppingList.addItem(new ShoppingItem("", ""));
  }

  addList()
  {
    this.shoppingList.deadline = this.datePipe.transform(this.shoppingList.deadlineDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
    this.shoppingService.addList(this.shoppingList).subscribe(
      data => console.log(data)
    )
  }
}
