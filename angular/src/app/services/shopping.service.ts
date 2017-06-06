
import { Http, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { ShoppingList } from "../components/shopping/ShoppingList";
import { LoggedUtils } from "../utils/logged.utils";

@Injectable()
export class ShoppingService
{
  constructor(private http: Http)
  {

  }

  addList(shoppingList : ShoppingList)
  {
    let param = JSON.stringify(shoppingList);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    let userId = LoggedUtils.getId();
    return this.http.post("http://localhost:8080/shopping/" + userId + "/addShoppingList", param, { headers : headers });
  }
}
