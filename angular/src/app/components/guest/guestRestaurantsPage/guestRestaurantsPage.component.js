"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var guest_service_1 = require("../../../services/guest.service");
var restaurants_service_1 = require("../../../services/restaurants.service");
var GuestRestaurantsPageComponent = (function () {
    function GuestRestaurantsPageComponent(guestService, restaurantService) {
        var _this = this;
        this.guestService = guestService;
        this.restaurantService = restaurantService;
        this.initDatePicker();
        this.restaurants = [];
        this.selectedRestaurant = { id: 0, name: "", description: "" };
        this.reservation = { time: "", date: "", duration: 0, restaurant: this.selectedRestaurant };
        this.restaurantService.getRestaurants().subscribe(function (data) { return _this.restaurants = data; });
    }
    GuestRestaurantsPageComponent.prototype.ngOnInit = function () { };
    GuestRestaurantsPageComponent.prototype.initDatePicker = function () {
        jQuery(document).ready(function () {
            var date_input = jQuery('input[name="date"]'); //our date input has the name "date"
            var container = jQuery('.bootstrap-iso form').length > 0 ? jQuery('.bootstrap-iso form').parent() : "body";
            var options = {
                format: 'dd.mm.yyyy.',
                container: container,
                todayHighlight: true,
                autoclose: true,
            };
            date_input.datepicker(options);
        });
    };
    GuestRestaurantsPageComponent.prototype.selectRestaurant = function (selectedId) {
        for (var i = 0; i < this.restaurants.length; i++)
            if (this.restaurants[i].id == selectedId)
                this.selectedRestaurant = this.restaurants[i];
        console.log(this.selectedRestaurant);
    };
    GuestRestaurantsPageComponent.prototype.searchRestaurants = function () {
        var _this = this;
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            this.restaurantService.getRestaurants().subscribe(function (data) { return _this.restaurants = data; });
        }
        this.restaurantService.searchRestaurants(this.searchParams).subscribe(function (data) { return _this.restaurants = data; });
    };
    GuestRestaurantsPageComponent.prototype.sendReservation = function () {
        this.reservation.restaurant = this.selectedRestaurant;
        this.guestService.sendReservation(1, this.reservation).subscribe(function (data) { return console.log(data); });
    };
    GuestRestaurantsPageComponent.prototype.sendInvitation = function (toWhomId) {
    };
    return GuestRestaurantsPageComponent;
}());
GuestRestaurantsPageComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestRestaurantsPage',
        templateUrl: 'guestRestaurantsPage.component.html',
        styleUrls: ['guestRestaurantsPage.component.css', '../../style/tableStyle.css'],
        providers: [restaurants_service_1.RestaurantService, guest_service_1.GuestService]
    })
], GuestRestaurantsPageComponent);
exports.GuestRestaurantsPageComponent = GuestRestaurantsPageComponent;
