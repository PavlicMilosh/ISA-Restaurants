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
var GuestRestaurantsComponent = (function () {
    function GuestRestaurantsComponent(guestService, restaurantService) {
        var _this = this;
        this.guestService = guestService;
        this.restaurantService = restaurantService;
        this.notify = new core_1.EventEmitter();
        this.restaurants = [];
        this.selectedRestaurant = { id: 0, name: "", description: "", friendsMark: null, meanMark: null };
        this.guestService.getRestaurants().subscribe(function (data) { return _this.restaurants = data; }, function (error) { return console.log(error); });
    }
    GuestRestaurantsComponent.prototype.ngOnInit = function () { };
    GuestRestaurantsComponent.prototype.selectRestaurant = function (selectedId) {
        for (var i = 0; i < this.restaurants.length; i++) {
            if (this.restaurants[i].id == selectedId) {
                this.selectedRestaurant = this.restaurants[i];
                break;
            }
        }
        this.notify.emit(this.selectedRestaurant);
    };
    GuestRestaurantsComponent.prototype.searchRestaurants = function () {
        var _this = this;
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            this.restaurantService.getRestaurants().subscribe(function (data) { return _this.restaurants = data; }, function (error) { return alert(error); });
        }
        this.restaurantService.searchRestaurants(this.searchParams).subscribe(function (data) { return _this.restaurants = data; }, function (error) { return alert(error); });
    };
    return GuestRestaurantsComponent;
}());
__decorate([
    core_1.Output()
], GuestRestaurantsComponent.prototype, "notify", void 0);
GuestRestaurantsComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestRestaurants',
        templateUrl: 'guestRestaurants.component.html',
        styleUrls: ['guestRestaurants.component.css', '../../style/tableStyle.css'],
        providers: [restaurants_service_1.RestaurantService, guest_service_1.GuestService]
    })
], GuestRestaurantsComponent);
exports.GuestRestaurantsComponent = GuestRestaurantsComponent;
