import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class GuestService
{
  constructor(private http: Http) {}

  registerGuest(email: string, pass: string, firstName: string, lastName: string)
  {
    var guest =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    var param = JSON.stringify(guest);
    var headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/guest/register/", param, { headers : headers })
      .map(res => res.json());
  }


  updateGuest(email: string, pass: string, firstName: string, lastName: string)
  {
    var guest =
      {
        email: email,
        password: pass,
        firstName: firstName,
        lastName: lastName,
      };
    var param = JSON.stringify(guest);
    var headers = new Headers();
    var s = "1L";

    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/guest/${s}/update", param, { headers : headers })
      .map(res => res.json());
  }


  sendFriendRequest()
  {
    var headers = new Headers();
    return this.http.post("http://localhost:8080/guest/1/sendFriendRequest/2", { headers : headers })
      .map(res => res.json());
  }


  acceptFriendRequest()
  {
    var headers = new Headers();
    return this.http.put("http://localhost:8080/guest/2/sendFriendRequest/1", { headers : headers })
      .map(res => res.json());
  }


  declineFriendRequest()
  {
    var headers = new Headers();
    return this.http.put("http://localhost:8080/guest/2/sendFriendRequest/2", { headers : headers })
      .map(res => res.json());
  }
}
