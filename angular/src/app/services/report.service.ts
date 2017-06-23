import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoggedUtils} from "../utils/logged.utils";
import {AddressUtils} from "../utils/address.utils";

@Injectable()
export class ReportService
{
  constructor(private http: Http)
  {

  }

  getIncomeData(date: string)
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/incomeReport", date,{ headers : headers })
      .map(res => res.json());
  }

  getVisitsData(date: string)
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/visitsReport", date,{ headers : headers })
      .map(res => res.json());
  }

  getWaitersData(date: string, waiterId: number)
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/incomeReport/waiters/" + waiterId, date,{ headers : headers })
      .map(res => res.json());
  }

  getRestaurantMeanMark()
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/markReport/restaurant",{ headers : headers })
      .map(res => res.json());
  }

  getDishesWithMeanMark()
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/markReport/dishes",{ headers : headers })
      .map(res => res.json());
  }

  getWaitersWithMeanMark()
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/markReport/waiters",{ headers : headers })
      .map(res => res.json());
  }

  getCooksWithMeanMark()
  {
    let managerId = LoggedUtils.getId();
    let headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put(AddressUtils.backendAddress() + "/restaurants/" + managerId + "/markReport/cooks",{ headers : headers })
      .map(res => res.json());
  }
}
