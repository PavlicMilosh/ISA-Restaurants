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
require("fabric");
var rxjs_1 = require("rxjs");
var GuestReservationWizardComponent = (function () {
    function GuestReservationWizardComponent(guestService, restaurantService) {
        this.guestService = guestService;
        this.restaurantService = restaurantService;
        this.parentSubject = new rxjs_1.Subject();
        this.reservation = { startTime: "", startDate: "", duration: 0,
            tables: [], invites: [], dishOrders: [], drinkOrders: [],
            restaurant: {
                id: null, name: null, description: null, dishes: [], drinks: []
            } };
    }
    GuestReservationWizardComponent.prototype.notifyChildren = function () {
        this.parentSubject.next(this.reservation);
    };
    GuestReservationWizardComponent.prototype.ngOnInit = function () {
        this.restaurantChoosen = false;
        this.tablesChoosen = false;
    };
    GuestReservationWizardComponent.prototype.selectedRestaurantNotify = function (restaurant) {
        if (restaurant != null) {
            this.reservation.restaurant = restaurant;
            this.reservation.startTime = null;
            this.reservation.startDate = null;
            this.reservation.duration = null;
            this.reservation.tables = [];
            this.reservation.drinkOrders = [];
            this.reservation.dishOrders = [];
            this.reservation.invites = [];
            this.restaurantChoosen = true;
        }
        else
            this.restaurantChoosen = false;
        this.tablesChoosen = false;
        this.notifyChildren();
    };
    GuestReservationWizardComponent.prototype.tablesNotify = function (tablesAndTime) {
        this.reservation.tables = tablesAndTime.tables;
        this.reservation.startDate = tablesAndTime.startDate;
        this.reservation.startTime = tablesAndTime.startTime;
        this.reservation.duration = tablesAndTime.duration;
        this.tablesChoosen = this.reservation.tables.length > 0;
        this.notifyChildren();
    };
    GuestReservationWizardComponent.prototype.invitesNotify = function (invites) {
        this.reservation.invites = invites;
        this.notifyChildren();
    };
    GuestReservationWizardComponent.prototype.drinkOrdersNotify = function (drinks) {
        this.reservation.drinkOrders = drinks;
    };
    GuestReservationWizardComponent.prototype.dishOrdersNotify = function (dishes) {
        this.reservation.dishOrders = dishes;
        this.notifyChildren();
    };
    GuestReservationWizardComponent.prototype.sendReservation = function () {
        console.log(this.reservation);
        this.guestService.sendReservation(this.reservation).subscribe(function (data) { return console.log(data); }, function (error) { return alert(error); });
    };
    return GuestReservationWizardComponent;
}());
GuestReservationWizardComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestReservationWizard',
        templateUrl: 'guestReservationWizard.component.html',
        styleUrls: ['guestReservationWizard.component.css', '../../style/formStyle.css'],
        providers: [guest_service_1.GuestService, restaurants_service_1.RestaurantService]
    })
], GuestReservationWizardComponent);
exports.GuestReservationWizardComponent = GuestReservationWizardComponent;
