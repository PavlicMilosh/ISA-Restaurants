import {Component, OnInit, ViewChild} from '@angular/core';
import {NgFor} from "@angular/common";

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
    console.log(this.dropdowns);
    this.presetSM();

    if (this.links.length == 0)
    {
      this.nempty = false;
    }
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


  presetSM()
  {
    var dropdownRestaurant =
    {
      text: "Restaurants",
      links:
      [
        {text: "Add restaurant", routerLink: "/addRestaurant"},
        {text: "Update restaurant", routerLink: "/updateRestaurant"}
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
  }

  presetRM()
  {

  }


  presetG()
  {

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
