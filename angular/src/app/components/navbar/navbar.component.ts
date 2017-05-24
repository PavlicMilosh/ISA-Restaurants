import {Component, OnInit, ViewChild} from '@angular/core';
import {NgFor} from "@angular/common";
import {LoggedUtils} from "../../utils/logged.utils";

@Component
({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {

  private links: Link[];
  private dropdowns: Dropdown[];
  private nempty: boolean;

  constructor()
  {
    this.links = [];
    this.dropdowns = [];

    if (LoggedUtils.isEmpty())
      this.defaultElements();
    else
    {
      if (LoggedUtils.hasRole("GUEST"))
        this.presetG();
      else if (LoggedUtils.hasRole("SYSTEM_MANAGER"))
        this.presetSM();
      else if (LoggedUtils.hasRole("RESTAURANT_MANAGER"))
        this.presetRM();
    }

    if (this.links.length == 0)
      this.nempty = false;
  }

  ngOnInit()
  {

  }

  addLink(link: Link)
  {
    this.links.push(link);
  }


  addDropdown(dropdown: Dropdown)
  {
    this.dropdowns.push(dropdown);
  }


  defaultElements()
  {
    this.addLink({text: "Login", routerLink: "/auth"});
    this.addLink({text: "Register", routerLink: "/registerGuest"});
  }


  presetSM()
  {
    var dropdownRestaurant =
    {
      text: "Restaurants",
      links:
      [
        {text: "Add restaurant", routerLink: "/addRestaurant"}
      ]
    };

    var dropdownUsers =
    {
      text: "Users",
      links:
        [
          {text: "System manager", routerLink: "/registerSM"},
          {text: "Restaurant manager", routerLink: "/registerRM"}
        ]
    };

    this.addDropdown(dropdownRestaurant);
    this.addDropdown(dropdownUsers);
    this.addLink({text: "Logout", routerLink:"/auth" });
  }


  presetRM()
  {
    var dropdownRestaurant =
    {
      text: "Restaurants",
      links:
        [
          {text: "Update restaurant", routerLink: "/updateRestaurant"}
        ]
    };

    var dropdownUsers =
    {
      text: "Workers",
      links:
        [
          {text: "Add worker", routerLink: "/addWorker"},
          {text: "Add work schedule", routerLink: "/addWorkSchedule"}
        ]
    };

    this.addLink({text: "Add provider", routerLink: "/addProvider"});

    this.addDropdown(dropdownRestaurant);
    this.addDropdown(dropdownUsers);

    this.addLink({text: "Logout", routerLink:"/auth" });
  }


  presetG()
  {
    var dropdownMyProfile =
      {
        text: "Profile",
        links:
          [
            {text: "Register", routerLink: "/registerGuest"},
            {text: "Update", routerLink: "/updateGuest"},
          ]
      };

    var dropdownFriendships =
      {
        text: "Friendships",
        links:
          [
            {text: "Friends", routerLink: "/guestFriendsPage"},
            {text: "Requests", routerLink: "/guestRequestsPage"},
            {text: "People", routerLink: "/guestPeoplePage"},
          ]
      };

    var dropdownRestaurants =
      {
        text: "Restaurants",
        links:
          [
            {text: "Send reservation", routerLink: "/guestRestaurantsPage"}
          ]
      };

    this.addDropdown(dropdownMyProfile);
    this.addDropdown(dropdownFriendships);
    this.addDropdown(dropdownRestaurants);

    this.addLink({text: "Logout", routerLink:"/auth" });

  }


  logout()
  {
    LoggedUtils.clearLocalStorage();
  }
}


interface Link
{
  text: string;
  routerLink: string;
}


interface Dropdown
{
  text: string;
  links: Link[];
}
