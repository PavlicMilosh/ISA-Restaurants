import {ShoppingItem} from "./ShoppingItem";

export class ShoppingList
{
  id: number;
  deadlineDate: Date;
  deadline: string;
  items: ShoppingItem[];

  constructor()
  {
    this.deadlineDate = new Date();
    this.items = [];
  }

  addItem(item: ShoppingItem)
  {
    this.items.push(item);
  }
}
