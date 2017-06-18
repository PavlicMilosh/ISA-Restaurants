"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var logged_utils_1 = require("../../utils/logged.utils");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var NavbarComponent = NavbarComponent_1 = (function () {
    function NavbarComponent() {
        this.links = [];
        this.dropdowns = [];
        if (logged_utils_1.LoggedUtils.isEmpty())
            this.defaultElements();
        else {
            if (logged_utils_1.LoggedUtils.hasRole("GUEST"))
                this.presetG();
            else if (logged_utils_1.LoggedUtils.hasRole("SYSTEM_MANAGER"))
                this.presetSM();
            else if (logged_utils_1.LoggedUtils.hasRole("RESTAURANT_MANAGER"))
                this.presetRM();
            else if (logged_utils_1.LoggedUtils.hasRole("WAITER"))
                this.presetWaiter();
            else if (logged_utils_1.LoggedUtils.hasRole("COOK"))
                this.presetCook();
            else if (logged_utils_1.LoggedUtils.hasRole("BARTENDER"))
                this.presetBartender();
        }
        if (this.links.length == 0)
            this.nempty = false;
    }
    NavbarComponent.prototype.ngOnInit = function () {
    };
    NavbarComponent.prototype.addLink = function (link) {
        this.links.push(link);
    };
    NavbarComponent.prototype.addDropdown = function (dropdown) {
        this.dropdowns.push(dropdown);
    };
    NavbarComponent.prototype.defaultElements = function () {
        this.addLink({ text: "Login", routerLink: "/auth" });
        this.addLink({ text: "Register", routerLink: "/guest/register" });
    };
    NavbarComponent.prototype.presetSM = function () {
        var dropdownRestaurant = {
            text: "Restaurants",
            links: [
                { text: "Add restaurant", routerLink: "/addRestaurant" }
            ]
        };
        var dropdownUsers = {
            text: "Users",
            links: [
                { text: "System manager", routerLink: "/registerSM" },
                { text: "Restaurant manager", routerLink: "/registerRM" },
                { text: "Add Provider", routerLink: "/addProvider" }
            ]
        };
        this.addDropdown(dropdownRestaurant);
        this.addDropdown(dropdownUsers);
        this.addLink({ text: "Logout", routerLink: "/auth" });
    };
    NavbarComponent.prototype.presetRM = function () {
        var dropdownRestaurant = {
            text: "Restaurants",
            links: [
                { text: "Update restaurant", routerLink: "/updateRestaurant" },
                { text: "Add Shopping List", routerLink: "/addShoppingList" }
            ]
        };
        var dropdownUsers = {
            text: "Workers",
            links: [
                { text: "Add worker", routerLink: "/addWorker" },
                { text: "Add work schedule", routerLink: "/addWorkSchedule" }
            ]
        };
        this.addLink({ text: "Add provider", routerLink: "/addProvider" });
        this.addDropdown(dropdownRestaurant);
        this.addDropdown(dropdownUsers);
        this.addLink({ text: "Logout", routerLink: "/auth" });
    };
    NavbarComponent.prototype.presetG = function () {
        var dropdownMyProfile = {
            text: "Profile",
            links: [
                { text: "Main Page", routerLink: "/main" },
                { text: "Register", routerLink: "/guest/register" },
                { text: "Update", routerLink: "/guest/update" },
            ]
        };
        var dropdownFriendships = {
            text: "Friendships",
            links: [
                { text: "Friends", routerLink: "/guest/friends" },
                { text: "Requests", routerLink: "/guest/requests" },
                { text: "People", routerLink: "/guest/people" },
            ]
        };
        var dropdownRestaurants = {
            text: "Restaurants",
            links: [
                { text: "Send reservation", routerLink: "/guest/reservations" }
            ]
        };
        this.addDropdown(dropdownMyProfile);
        this.addDropdown(dropdownFriendships);
        this.addDropdown(dropdownRestaurants);
        this.addLink({ text: "Logout", routerLink: "/auth" });
    };
    NavbarComponent.prototype.presetWaiter = function () {
        var dropdownOrder = {
            text: "Orders",
            links: [
                { text: "Make order", routerLink: "/makeOrder" }
            ]
        };
        this.addLink({ text: "Logout", routerLink: "/auth" });
        this.addDropdown(dropdownOrder);
        this.addLink({ text: "Schedule", routerLink: "/employeeWorkShedule" });
    };
    NavbarComponent.prototype.presetCook = function () {
        var dropdownOrder = {
            text: "Orders",
            links: [
                { text: "Confirm order", routerLink: "/orderDishes" }
            ]
        };
        this.addLink({ text: "Logout", routerLink: "/auth" });
        this.addDropdown(dropdownOrder);
        this.addLink({ text: "Schedule", routerLink: "/employeeWorkShedule" });
    };
    NavbarComponent.prototype.presetBartender = function () {
        var dropdownOrder = {
            text: "Orders",
            links: [
                { text: "Confirm order", routerLink: "/orderDrinks" }
            ]
        };
        this.addLink({ text: "Logout", routerLink: "/auth" });
        this.addDropdown(dropdownOrder);
        this.addLink({ text: "Schedule", routerLink: "/employeeWorkShedule" });
    };
    NavbarComponent.prototype.logout = function () {
        logged_utils_1.LoggedUtils.clearLocalStorage();
    };
    return NavbarComponent;
}());
NavbarComponent = NavbarComponent_1 = __decorate([
    core_1.NgModule({
        declarations: [NavbarComponent_1],
        imports: [ng_bootstrap_1.NgbModule]
    }),
    core_1.Component({
        selector: 'navbar',
        templateUrl: './navbar.component.html',
        styleUrls: ['./navbar.component.css']
    })
], NavbarComponent);
exports.NavbarComponent = NavbarComponent;
var NavbarComponent_1;
