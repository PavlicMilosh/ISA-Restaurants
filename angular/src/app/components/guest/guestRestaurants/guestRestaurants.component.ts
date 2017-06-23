import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import {RestaurantService } from "../../../services/restaurants.service";
import { GoogleService } from "../../../services/google.services";
import {Guard} from "../../../utils/Guard";

declare var google: any;
declare let jQuery:any;

@Component
({
  moduleId: module.id,
  selector: 'guestRestaurants',
  templateUrl: 'guestRestaurants.component.html',
  styleUrls: ['guestRestaurants.component.css', '../../style/tableStyle.css'],
  providers: [RestaurantService, GuestService, GoogleService, Guard]
})


export class GuestRestaurantsComponent implements OnInit
{
  @Output() notify: EventEmitter<Restaurant> = new EventEmitter<Restaurant>();
  private restaurants: Restaurant[];
  private selectedRestaurant: Restaurant;
  private searchParams: string;

  private nameSortAsc: boolean;
  private kitchenSortAsc: boolean;
  private visitsSortAsc: boolean;
  private distanceSortAsc: boolean;
  private meanMarkSortAsc: boolean;
  private friendsMarkSortAsc: boolean;

  private currentCoords: Coords;


  constructor(private guestService: GuestService,
              private restaurantService: RestaurantService,
              private googleService: GoogleService)
  {
    this.restaurants = [];

    this.currentCoords = {lat: null, lng: null};

    this.nameSortAsc = false;
    this.kitchenSortAsc = false;
    this.visitsSortAsc = false;
    this.distanceSortAsc = false;
    this.meanMarkSortAsc = false;
    this.friendsMarkSortAsc = false;


    this.getCurrentLocation();

    this.guestService.getRestaurants().subscribe
    (
      data =>
      {
        this.restaurants = data;
        for (let r of this.restaurants)
        {
          if (r.address != null)
          {
            this.googleService.geocode(r.address.street + ',+' + r.address.city + ',+' + r.address.country).subscribe
            (
              data =>
              {
                if (data.results.length > 0)
                {
                  r.canCalculateDistance = true;
                  r.lat = data.results[0].geometry.location.lat;
                  r.lng = data.results[0].geometry.location.lng;
                  console.log(r);
                  let origin = new google.maps.LatLng(this.currentCoords.lat, this.currentCoords.lng);
                  let destination = new google.maps.LatLng(r.lat, r.lng);
                  let service = new google.maps.DistanceMatrixService();
                  service.getDistanceMatrix
                  (
                    {
                      origins: [origin],
                      destinations: [destination],
                      travelMode: 'DRIVING'
                    },
                    function (response, status)
                    {
                      if (response.rows[0].elements[0].distance != null)
                      {
                        console.log(response);
                        r.distanceText = response.rows[0].elements[0].distance.text;
                        r.distance = response.rows[0].elements[0].distance.value;
                      }
                      else
                      {
                        r.distanceText = null;
                        r.distance = null;
                      }
                    }
                  );
                }
                else
                {
                  r.canCalculateDistance = false;
                  r.lat = null;
                  r.lng = null;
                }
              },
              error => alert(error)
            );
          }
        }
      },
      error => alert(error)

    );


  }


  ngOnInit()
  {

  }


  selectRestaurant(selectedId: number)
  {
    for (let i = 0; i < this.restaurants.length; i++)
    {
      if (this.restaurants[i].id == selectedId)
      {
        this.selectedRestaurant = this.restaurants[i];
        break;
      }
    }
    console.log(this.selectedRestaurant);
    this.notify.emit(this.selectedRestaurant);
  }


  showOnMap(id: number)
  {
    for (let r of this.restaurants)
    {
      if (r.id == id)
      {
        let uluru = {lat: r.lat, lng: r.lng};
        console.log(uluru);
        let map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        let marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    }
  }


  searchRestaurants()
  {
    if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length)
    {
      this.guestService.getRestaurants().subscribe
      (
        data => { this.restaurants = data; console.log(data)},
        error => alert(error)
      );
    }

    this.restaurantService.searchRestaurants(this.searchParams).subscribe
    (
      data => this.restaurants = data,
      error => alert(error)
    );
  }


  sortData(attribute: string)
  {
    let asc = true;

    if (attribute == 'name')
    {
      asc = this.nameSortAsc;
      this.nameSortAsc = !this.nameSortAsc;
    }
    else if (attribute == 'kitchen')
    {
      asc = this.kitchenSortAsc;
      this.kitchenSortAsc = !this.kitchenSortAsc;
    }
    else if (attribute == 'visits')
    {
      asc = this.visitsSortAsc;
      this.visitsSortAsc = !this.visitsSortAsc;
    }
    else if (attribute == 'distance')
    {
      asc = this.distanceSortAsc;
      this.distanceSortAsc = !this.distanceSortAsc;
    }
    else if (attribute == 'meanMark')
    {
      asc = this.meanMarkSortAsc;
      this.meanMarkSortAsc = !this.meanMarkSortAsc;
    }
    else if (attribute == 'friendsMark')
    {
      asc = this.meanMarkSortAsc;
      this.meanMarkSortAsc = !this.meanMarkSortAsc;
    }


    this.restaurants.sort(function(a, b)
    {
      let first = a[attribute];
      let second = b[attribute];
      // Compare the 2 dates

      if (asc)
      {
        if (first < second) return -1;
        if (first > second) return 1;
      }
      else
      {
        if (first > second) return -1;
        if (first < second) return 1;
      }
      return 0;
    });
  }


  getCurrentLocation()
  {
    if (navigator.geolocation)
    {
      let self = this;
      navigator.geolocation.getCurrentPosition
      (
        function(position)
        {
              self.currentCoords.lat = position.coords.latitude;
              self.currentCoords.lng = position.coords.longitude;
        },
        function()
        {
          alert("Something went wrong, couldn't get location :(")
        }
      );
    }
    else
    {
    // Browser doesn't support Geolocation
    alert("Your browser doesn't supprt Geolocation");
    }
  }
}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
  friendsMark: number;
  visits: number;
  meanMark: number;
  tables: RestaurantTable[];

  lat: number;
  lng: number;

  address: Address;
  distance: number;
  distanceText: string;
  canCalculateDistance: boolean;
}


interface Address
{
  city: string;
  number: number;
  street: string;
  postalCode: string;
  country: string;
}


interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
  occupied: boolean;
  regionId: number;
  regionColor: string;
  seats: number;
}


interface Coords
{
  lat: number;
  lng: number;
}
