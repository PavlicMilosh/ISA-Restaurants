import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";
import {AddressUtils} from "../utils/address.utils";

@Injectable()
export class GuestService
{
  constructor(private http: Http) {}

  registerGuest(email: string, pass: string, firstName: string, lastName: string)
  {
    let guest =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    let param = JSON.stringify(guest);
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return this.http.post(AddressUtils.backendAddress() + "/guest/register", param, { headers : headers })
      .map(res => res.json());
  }


  updateGuest(email: string, pass: string, firstName: string, lastName: string)
  {
    let guest =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    let guestId = LoggedUtils.getId();
    let param = JSON.stringify(guest);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/" + guestId + "/update", param, { headers : headers })
      .map(res => res.json());
  }


  getAllGuests()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get(AddressUtils.backendAddress() + "/guest/" + guestId + "/getAllGuests", { headers: headers })
      .map(res => res.json());
  }


  getFriends()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get(AddressUtils.backendAddress() + "/guest/" + guestId + "/getFriends", { headers: headers })
      .map(res => res.json());
  }


  getFriendRequests()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get(AddressUtils.backendAddress() + "/guest/" + guestId + "/getFriendRequests", { headers: headers })
      .map(res => res.json());
  }


  sendFriendRequest(toWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.post(AddressUtils.backendAddress() + "/guest/" + guestId + "/sendFriendRequest/" + toWhomId, { headers : headers })
      .map(res => res.json());
  }


  acceptFriendRequest(fromWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + guestId + "/acceptFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  declineFriendRequest(fromWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + guestId + "/declineFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  unfriend(friendId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + guestId + "/unfriendUser/" + friendId, {headers: headers})
      .map(res => res.json());
  }


  searchAllGuests(searchParams: string)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + guestId + "/searchForFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }

  searchFriends(searchParams: string)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + guestId + "/searchMyFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }


  sendReservation(reservation: any)
  {
    let guestId = LoggedUtils.getId();
    let param = JSON.stringify(reservation);
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post(AddressUtils.backendAddress() + "/guest/" + guestId + "/sendReservation", param, {headers: headers})
      .map(res => res.json());
  }


  getTables(reservation: any)
  {
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put(AddressUtils.backendAddress() + "/guest/" + LoggedUtils.getId() + "/getTables", reservation, {headers: headers})
      .map(res => res.json());
  }


  getRestaurants()
  {
    let headers = new Headers();
    let guestId = LoggedUtils.getId();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get(AddressUtils.backendAddress() + "/guest/" + guestId + "/getRestaurants", { headers : headers })
      .map(res => res.json());
  }

}
