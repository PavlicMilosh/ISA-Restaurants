import { Component, OnInit } from '@angular/core';
import {ShoppingService} from "../../../services/shopping.service";
import {ShoppingList} from "../ShoppingList";
import {Offer} from "../Offer";

@Component({
  selector: 'app-shopping-lists-rm',
  templateUrl: './shoppingListsRM.component.html',
  styleUrls: ['./shoppingListsRM.component.css'],
  providers: [ShoppingService]
})
export class ShoppingListsRMComponent implements OnInit
{

  private shoppingLists: ShoppingList[];
  private currentList: ShoppingList;

  constructor(private shoppingService : ShoppingService)
  {
    this.shoppingService.getListsByRM().subscribe(
      data => this.shoppingLists = data
    );
    this.shoppingLists = [];
    this.currentList = new ShoppingList();
  }

  ngOnInit()
  {
  }

  setCurrentList(list : ShoppingList)
  {
    this.currentList = list;
  }

  acceptOffer(offer : Offer)
  {
    this.shoppingService.acceptOffer(offer, this.currentList).subscribe(
      data => alert("Offer successfully accepted"),
      error => alert("Accepting offer unsuccessful")
    );
  }

}
