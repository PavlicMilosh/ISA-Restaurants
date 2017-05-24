"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var NavbarComponent = (function () {
    function NavbarComponent() {
        this.links = [];
        this.dropdowns = [];
        console.log(this.dropdowns);
        //this.presetSM();
        this.presetG();
        if (this.links.length == 0) {
            this.nempty = false;
        }
    }
    NavbarComponent.prototype.ngOnInit = function () {
    };
    NavbarComponent.prototype.addLink = function (link) {
        this.links.push(link);
    };
    NavbarComponent.prototype.addDropdown = function (dropdown) {
        this.dropdowns.push(dropdown);
    };
    NavbarComponent.prototype.presetSM = function () {
        var dropdownRestaurant = {
            text: "Restaurants",
            links: [
                { text: "Add restaurant", routerLink: "/addRestaurant" },
                { text: "Update restaurant", routerLink: "/updateRestaurant" }
            ]
        };
        var dropdownUsers = {
            text: "Users",
            links: [
                { text: "System manager", routerLink: "/registerSM" },
                { text: "Restaurant manager", routerLink: "/registerRM" }
            ]
        };
        this.addDropdown(dropdownRestaurant);
        this.addDropdown(dropdownUsers);
    };
    NavbarComponent.prototype.presetRM = function () {
    };
    NavbarComponent.prototype.presetG = function () {
        var dropdownMyProfile = {
            text: "Profile",
            links: [
                { text: "Main Page", routerLink: "/mainPageGuest" },
                { text: "Register", routerLink: "/registerGuest" },
                { text: "Update", routerLink: "/updateGuest" },
            ]
        };
        var dropdownFriendships = {
            text: "Friendships",
            links: [
                { text: "Friends", routerLink: "/guestFriendsPage" },
                { text: "Requests", routerLink: "/guestRequestsPage" },
                { text: "People", routerLink: "/guestPeoplePage" },
            ]
        };
        var dropdownRestaurants = {
            text: "Restaurants",
            links: [
                { text: "Send reservation", routerLink: "/guestRestaurantsPage" }
            ]
        };
        this.addDropdown(dropdownMyProfile);
        this.addDropdown(dropdownFriendships);
        this.addDropdown(dropdownRestaurants);
    };
    return NavbarComponent;
}());
NavbarComponent = __decorate([
    core_1.Component({
        selector: 'navbar',
        templateUrl: './navbar.component.html',
        styleUrls: ['./navbar.component.css']
    })
], NavbarComponent);
exports.NavbarComponent = NavbarComponent;
