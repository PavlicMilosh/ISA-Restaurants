import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class GoogleService
{
  private key = 'AIzaSyDktm4_Qkq5wDW1sX9PY9UKHHGwXmCxnj0';
  constructor(private http: Http)
  {

  }

  //'&key=AIzaSyDktm4_Qkq5wDW1sX9PY9UKHHGwXmCxnj0'

  geocode(address: string)
  {
    let headers = new Headers();
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?' +
      'address=' + address +
      '&key=' + this.key)
      .map(res => res.json());
  }


  calculateDistance(lat1: number, lng1: number, lat2: number, lng2: number): Promise<any>
  {
    let headers = new Headers();
    return this.http.get('https://maps.googleapis.com/maps/api/distancematrix/json?' +
      'units=metric' +
      '&origins=' + lat1 + ',' +lng1 +
      '&destinations=enc:_kjwFjtsbMt%60EgnKcqLcaOzkGari%40naPxhVg%7CJjjb%40cqLcaOzkGari%40naPxhV' +
      '&key=' + this.key)
      .toPromise().then((response) => { return response.json().data as any });
  }

}
