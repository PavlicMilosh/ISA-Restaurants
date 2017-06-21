import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";

@Injectable()
export class BillService
{
  constructor(private http: Http)
  {
  }

  getBill(tableId:number)
  {
    console.log(tableId);
    let workerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put("http://localhost:8080//bill/" + tableId + "/getBill/" + workerId, { headers: headers })
      .map(res => res.json());
  }
}

