import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";

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
    return this.http.post("http://localhost:8080/guest/register/", param, { headers : headers })
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
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/" + guestId + "/update", param, { headers : headers })
      .map(res => res.json());
  }


  getAllGuests()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getAllGuests", { headers: headers })
      .map(res => res.json());
  }


  getFriends()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriends", { headers: headers })
      .map(res => res.json());
  }


  getFriendRequests()
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriendRequests", { headers: headers })
      .map(res => res.json());
  }


  sendFriendRequest(toWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.post("http://localhost:8080/guest/" + guestId + "/sendFriendRequest/" + toWhomId, { headers : headers })
      .map(res => res.json());
  }


  acceptFriendRequest(fromWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.put("http://localhost:8080//guest/" + guestId + "/acceptFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  declineFriendRequest(fromWhomId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.put("http://localhost:8080//guest/" + guestId + "/declineFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  unfriend(friendId: number)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    return this.http.put("http://localhost:8080/guest/" + guestId + "/unfriendUser/" + friendId, {headers: headers})
      .map(res => res.json());
  }


  sendInvitation(toWhom: number)
  {
    console.log("INVITATION NOT YET IMPLEMENTED!");
  }


  searchAllGuests(searchParams: string)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/" + guestId + "/searchForFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }

  searchFriends(searchParams: string)
  {
    let guestId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/" + guestId + "/searchMyFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }


  sendReservation(reservation: any)
  {
    let guestId = LoggedUtils.getId();
    let param = JSON.stringify(reservation);
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/guest/" + guestId + "/sendReservation", param, {headers: headers})
      .map(res => res.json());
  }

}
