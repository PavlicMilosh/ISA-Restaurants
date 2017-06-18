import { ShoppingItem } from "./ShoppingItem";
import { Offer } from "./Offer";

export class ShoppingList
{
  id: number;
  name: string;
  deadlineDate: Date;
  deadline: string;
  items: ShoppingItem[];
  acceptedOffer: Offer;
  offers: Offer[];

  constructor()
  {
    this.deadlineDate = new Date();
    this.items = [];
    this.acceptedOffer = null;
    this.offers = [];
  }

  addItem(item: ShoppingItem)
  {
    this.items.push(item);
  }
}
