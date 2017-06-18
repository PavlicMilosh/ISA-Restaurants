
import { Http, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { ShoppingList } from "../components/shopping/ShoppingList";
import { LoggedUtils } from "../utils/logged.utils";
import {Offer} from "../components/shopping/Offer";

@Injectable()
export class ShoppingService
{
  constructor(private http: Http)
  {

  }

  addList(shoppingList : ShoppingList)
  {
    console.log(shoppingList);
    let param = JSON.stringify(shoppingList);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    let userId = LoggedUtils.getId();
    return this.http.post("http://localhost:8080/shopping/" + userId + "/addShoppingList", param, { headers : headers });
  }

  getLists()
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/shopping", { headers : headers })
      .map(res => res.json());
  }

  getListsByRM()
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/shopping/RM/" + LoggedUtils.getId(), { headers : headers })
      .map(res => res.json());
  }

  sendOffer(offer : Offer, listId : number)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    let providerId = LoggedUtils.getId();
    console.log(offer);
    let param = JSON.stringify(offer);
    console.log(param);
    return this.http.put("http://localhost:8080/shopping/" + providerId + "/" + listId + "/sendOffer", param, { headers : headers })
      .map(res => res.json());
  }

  acceptOffer(offer : Offer, list : ShoppingList)
  {
    let param = JSON.stringify(offer);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/shopping/" + list.id + "/acceptOffer", param, {headers : headers});
  }
}
