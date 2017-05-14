import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

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


  updateGuest(guestId: number, email: string, pass: string, firstName: string, lastName: string)
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
    return this.http.put("http://localhost:8080/guest/" + guestId + "/update", param, { headers : headers })
      .map(res => res.json());
  }


  getAllGuests(guestId: number)
  {
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getAllGuests", { headers: headers })
      .map(res => res.json());
  }


  getFriends(guestId: number)
  {
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriends", { headers: headers })
      .map(res => res.json());
  }


  getFriendRequests(guestId: number)
  {
    let headers = new Headers();
    return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriendRequests", { headers: headers })
      .map(res => res.json());
  }


  sendFriendRequest(guestId: number, toWhomId: number)
  {
    let headers = new Headers();
    return this.http.post("http://localhost:8080/guest/" + guestId + "/sendFriendRequest/" + toWhomId, { headers : headers })
      .map(res => res.json());
  }


  acceptFriendRequest(guestId: number, fromWhomId: number)
  {
    let headers = new Headers();
    return this.http.put("http://localhost:8080//guest/" + guestId + "/acceptFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  declineFriendRequest(guestId: number, fromWhomId: number)
  {
    let headers = new Headers();
    return this.http.put("http://localhost:8080//guest/" + guestId + "/declineFriendRequest/" + fromWhomId, { headers : headers })
      .map(res => res.json());
  }


  unfriend(guestId: number, friendId: number)
  {
    console.log(guestId + " UNFRIENDED " +friendId);
    let headers = new Headers();
    return this.http.put("http://localhost:8080/guest/" + guestId + "/unfriendUser/" + friendId, {headers: headers})
      .map(res => res.json());
  }


  sendInvitation(toWhom: number)
  {
    console.log("INVITATION NOT YET IMPLEMENTED!");
  }


  searchAllGuests(guestId: number, searchParams: string)
  {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/" + guestId + "/searchForFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }

  searchFriends(guestId: number, searchParams: string)
  {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/" + guestId + "/searchMyFriends", searchParams, {headers: headers})
      .map(res => res.json());
  }

}
