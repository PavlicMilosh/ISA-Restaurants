import { Component, OnInit } from '@angular/core';
import { ShoppingService } from '../../../services/shopping.service'
import { ShoppingList } from "../ShoppingList";
import { Offer } from "../Offer";
import {LoggedUtils} from "../../../utils/logged.utils";
import {Guard} from "../../../utils/Guard";

@Component({
  selector: 'app-shopping-list-provider',
  templateUrl: './shoppingListProvider.component.html',
  styleUrls: ['./shoppingListProvider.component.css'],
  providers: [ ShoppingService, Guard ]
})
export class ShoppingListProviderComponent implements OnInit
{

  private shoppingLists : ShoppingList[];
  private currentList : ShoppingList;
  private offers : Offer[];
  private currentOffer : Offer;
  private loggedId : number;
  private index : number;

  constructor(private shoppingService : ShoppingService)
  {
    this.shoppingLists = [];
    this.currentList = new ShoppingList();
    this.offers = [];
    this.currentOffer = new Offer(null, -1, 0);
  }

  ngOnInit()
  {
    this.loggedId = LoggedUtils.getId();
    this.shoppingService.getLists().subscribe(
      data =>
      {
        this.shoppingLists = data;
        for(let l of this.shoppingLists)
        {
          console.log(l);
          let offerFound = false;
          for(let oo of l.offers)
          {
            if(oo.provider.id == LoggedUtils.getId())
            {
              offerFound = true;
              this.offers.push(oo);
            }
          }
          if(!offerFound)
          {
            this.offers.push(new Offer(l, -1, 0));
          }
        }
      }
    );
  }

  sendOffer()
  {
    if(this.currentOffer.amount <= 0)
      alert("Offer can't be less than or equal to 0");
    else
      this.shoppingService.sendOffer(this.currentOffer, this.currentList.id).subscribe(
        data =>
        {
          alert("Changing offer successful");
          this.currentOffer = data
        },
        error => alert("Changing offer unsuccessful")
      );
  }

  setCurrentList(shoppingList : ShoppingList)
  {
    let i = 0;
    for(let list of this.shoppingLists)
    {
      console.log(this.offers[i]);
      if(list == this.currentList)
      {
        break;
      }
      i++;
    }
    this.currentOffer = this.offers[i];
    this.currentList = shoppingList;
  }
}
