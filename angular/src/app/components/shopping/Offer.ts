import { ShoppingList } from "./ShoppingList";
import { Provider } from "./Provider";
export class Offer
{
  shoppingList: ShoppingList;
  amount: number;
  provider: Provider;
  id: number;
  version: number;

  constructor(shoppingList : ShoppingList, amount : number, version : number)
  {
    this.shoppingList = shoppingList;
    this.amount = amount;
    this.id = null;
    this.version = version;
  }
}
