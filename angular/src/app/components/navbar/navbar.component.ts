import {Component, OnInit, ViewChild, NgModule} from '@angular/core';
import {LoggedUtils} from "../../utils/logged.utils";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [NavbarComponent],
  imports: [NgbModule]
})

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
      else if (LoggedUtils.hasRole("WAITER"))
        this.presetWaiter();
      else if (LoggedUtils.hasRole("COOK"))
        this.presetCook();
      else if (LoggedUtils.hasRole("BARTENDER"))
        this.presetBartender();
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
    this.addLink({text: "Register", routerLink: "/guest/register"});
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
          {text: "Restaurant manager", routerLink: "/registerRM"},
          {text: "Add Provider", routerLink: "/addProvider"}
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
          {text: "Update restaurant", routerLink: "/updateRestaurant"},
          {text: "Add Shopping List", routerLink: "/addShoppingList"}
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
            {text: "Main Page", routerLink: "/main"},
            {text: "Register", routerLink: "/guest/register"},
            {text: "Update", routerLink: "/guest/update"},
          ]
      };

    var dropdownFriendships =
      {
        text: "Friendships",
        links:
          [
            {text: "Friends", routerLink: "/guest/friends"},
            {text: "Requests", routerLink: "/guest/requests"},
            {text: "People", routerLink: "/guest/people"},
          ]
      };

    var dropdownRestaurants =
      {
        text: "Restaurants",
        links:
          [
            {text: "Send reservation", routerLink: "/guest/reservations"}
          ]
      };

    this.addDropdown(dropdownMyProfile);
    this.addDropdown(dropdownFriendships);
    this.addDropdown(dropdownRestaurants);

    this.addLink({text: "Logout", routerLink:"/auth" });

  }

  presetWaiter()
  {
    var dropdownOrder =
      {
        text: "Orders",
        links:
          [
            {text: "Make order", routerLink: "/makeOrder"}
          ]
      };

    this.addLink({text: "Logout", routerLink:"/auth" });
    this.addDropdown(dropdownOrder);
    this.addLink({text: "Schedule", routerLink:"/employeeWorkShedule" });
  }

  presetCook()
  {
    var dropdownOrder =
      {
        text: "Orders",
        links:
          [
            {text: "Confirm order", routerLink: "/orderDishes"}
          ]
      };

    this.addLink({text: "Logout", routerLink:"/auth" });
    this.addDropdown(dropdownOrder);
    this.addLink({text: "Schedule", routerLink:"/employeeWorkShedule" });
  }

  presetBartender()
  {
    var dropdownOrder =
      {
        text: "Orders",
        links:
          [
            {text: "Confirm order", routerLink: "/orderDrinks"}
          ]
      };

    this.addLink({text: "Logout", routerLink:"/auth" });
    this.addDropdown(dropdownOrder);
    this.addLink({text: "Schedule", routerLink:"/employeeWorkShedule" });
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
